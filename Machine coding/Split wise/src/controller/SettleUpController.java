package controller;

import model.*;
import repository.SplitWiseRepository;
import service.UserInputOutputService;

import java.util.ArrayList;
import java.util.List;

public class SettleUpController {

    public static void settleUp(){
        User loggedInUser = AuthController.getLoggedInUser();
        List<Payable> shouldPayPayables = getUserShouldPayPayables(loggedInUser);

        if (shouldPayPayables.isEmpty()){
            UserInputOutputService.printMessageAndOneLine("🪙---There is no pending expense from User side---🪙");
        }else{
           SettleUpPair selectedSettleUpPair = createSettleUpPairAndChooseOne(shouldPayPayables, loggedInUser);

            int settlementPreference = UserInputOutputService.getFullOrPartialSettleUpChoice();
            if( settlementPreference == 2 ){
                settleFullPayment(selectedSettleUpPair);
            }else{
                settlePartialPayment(selectedSettleUpPair, loggedInUser);
            }
        }
    }



    private static SettleUpPair createSettleUpPairAndChooseOne(List<Payable> shouldPayPayables,
                                                               User loggedInUser){
        List<SettleUpPair> settleUpPairList = generateSettleUpPairFromPayables(shouldPayPayables,
                loggedInUser );

        int idx = UserInputOutputService.displaySettleUpPairList(settleUpPairList);
        return settleUpPairList.get(idx);
    }

    private static List<Payable> getUserShouldPayPayables( User user ){

        List<Payable> shouldPayPayable = new ArrayList<>();
        for( Payable payable : user.getPayablelist() ){
            if(payable.getFrom().equals(user.getName())
                    && payable.getAmount() > 0d
                    && payable.getPaymentStatus().equals(PaymentStatus.PENDING) ){

                shouldPayPayable.add(payable);

            }else if( payable.getTo().equals(user.getName())
                    && payable.getAmount() < 0d
                    && payable.getPaymentStatus().equals(PaymentStatus.PENDING) ){

                shouldPayPayable.add(payable);
            }
        }
        return shouldPayPayable;
    }

    private static  List<SettleUpPair> generateSettleUpPairFromPayables( List<Payable> payableList,
                                                                         User user ){
        List<SettleUpPair> settleUpPairList = new ArrayList<>();

        for( Payable payable : payableList ){

            String description;

            if( payable.getFrom().equals(user.getName()) ){

                description = String.format("%s should pay %s an amount of %.2f in group : %s",
                        user.getName(), payable.getTo(), Math.abs(payable.getAmount()),
                        payable.getGroupName());

            }else{
                description = String.format("%s should pay %s an amount of %.2f in group : %s",
                        user.getName(), payable.getFrom(), Math.abs(payable.getAmount()),
                        payable.getGroupName());
            }

            settleUpPairList.add( new SettleUpPair(description, payable) );
        }
        return  settleUpPairList;
    }

    private static void settleFullPayment( SettleUpPair settleUpPair ){
        Payable payable = settleUpPair.getPayable();
        payable.setPaymentStatus(PaymentStatus.PAID);
        createTransactions(settleUpPair.getPayable(), payable.getAmount() );
    }

    private static void settlePartialPayment( SettleUpPair settleUpPair, User loggedInUser ){
        double settleUpAmount = UserInputOutputService.getSettleUpAmount();
        Payable payable = settleUpPair.getPayable();

        if( settleUpAmount >= Math.abs(payable.getAmount()) ){ // The amount in payable might be negative :
            settleFullPayment(settleUpPair);
            return;
        }

        if( payable.getFrom().equals(loggedInUser.getName())
                && payable.getAmount() > 0d ){
            payable.setAmount( payable.getAmount() - settleUpAmount );

        }else if( payable.getTo().equals(loggedInUser.getName())
                && payable.getAmount() < 0d ){
            payable.setAmount( payable.getAmount() + settleUpAmount );
        }

        createTransactions( payable, settleUpAmount );
    }

    private static void createTransactions( Payable payable, double paidMoney ){

        if( payable.getAmount() > 0d ){

            createMoneySentTransaction(payable.getFrom(), payable.getTo(),
                    payable.getGroupName(), paidMoney);

            createMoneyReceivedTransaction(payable.getFrom(), payable.getTo(),
                    payable.getGroupName(), paidMoney);

        }else{
            createMoneySentTransaction(payable.getTo(), payable.getFrom(),
                    payable.getGroupName(), paidMoney);

            createMoneyReceivedTransaction(payable.getTo(), payable.getFrom(),
                    payable.getGroupName(), paidMoney);
        }
    }

    private static void createMoneySentTransaction( String from,
                                                      String to, String groupName, double amount ){
        String fromUserSideTransaction = String.format("%s paid %s an amount of %.2f in group : %s",
                from,to, amount, groupName);

        addTransactionIntoUser(from, fromUserSideTransaction);
        addTransactionIntoGroup(groupName, fromUserSideTransaction);

        UserInputOutputService.printMessageAndOneLine(fromUserSideTransaction);
    }

    private static void createMoneyReceivedTransaction( String from, String to,
                                                        String groupName, double amount ){
        String toUserSideTransaction = String.format("%s received an amount of %.2f from %s  in group : %s",
                to, amount, from,  groupName);

        addTransactionIntoUser(to, toUserSideTransaction);
        addTransactionIntoGroup(groupName, toUserSideTransaction);

        UserInputOutputService.printMessageAndOneLine(toUserSideTransaction);
    }

    private static void addTransactionIntoGroup( String groupName, String transaction ){
        Group group = SplitWiseRepository.getGroupFromGroupObjectMap(groupName);
        group.getTransactionHistory().add( transaction );
    }

    private static void addTransactionIntoUser( String userName, String transaction ){
        User user = SplitWiseRepository.getUserFromUserObjectsMap(userName);
        user.getTransactionHistory().add(transaction);
    }
}
