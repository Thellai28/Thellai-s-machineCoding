package controller;

import model.Payable;
import model.PaymentStatus;
import model.User;
import service.UserInputOutputService;

import java.util.ArrayList;
import java.util.List;

public class PendingExpenseController {
    public static void generatePendingExpenses(){
        User loggedInUser = AuthController.getLoggedInUser();
        handleUserShouldPayExpenses(loggedInUser);
        handleUserShouldReceiveExpenses(loggedInUser);
    }

    private static void handleUserShouldPayExpenses(User user ){
        List<String> shouldPayList = getUserShouldPayExpenses(user);
        if( shouldPayList.size() == 0 ){
            UserInputOutputService.printMessageAndOneLine("✅---User Paid all the pending expenses---✅");
        }else{
            UserInputOutputService
                    .displayList(shouldPayList,
                            "🪙---Displaying Expenses User should Pay to others---🪙");
        }
    }

    private static List<String> getUserShouldPayExpenses(User user){
        List<Payable> payableList = user.getPayablelist();
        List<String> shouldPayList = new ArrayList<>();

        for( Payable payable : payableList ){
            if( payable.getFrom().equals(user.getName()) && payable.getAmount() > 0d
                    && payable.getPaymentStatus().equals(PaymentStatus.PENDING) ){

                shouldPayList.add(
                        String.format("%s should PAY %.2f to %s in %s group",
                                payable.getFrom(), payable.getAmount(), payable.getTo(), payable.getGroupName() )
                );

            }else if( payable.getTo().equals(user.getName()) && payable.getAmount() < 0d
                    && payable.getPaymentStatus().equals(PaymentStatus.PENDING) ){

                shouldPayList.add(
                        String.format("%s should PAY %.2f to %s in %s group",
                                payable.getTo(), payable.getAmount(), payable.getFrom(), payable.getGroupName() )
                );
            }
        }
        return shouldPayList;
    }

    private static void handleUserShouldReceiveExpenses( User user ){
        List<String>shouldRecieveList = generateUserShouldReceiveExpense(user);

        if( shouldRecieveList.size() == 0 ){
            UserInputOutputService.printMessageAndOneLine("✅---User received All pending expenses---✅");
        }else{
            UserInputOutputService
                    .displayList(shouldRecieveList,
                            "🪙---Displaying Expenses User should Receive from others---🪙");

        }


    }

    private static List<String> generateUserShouldReceiveExpense( User user ){
        List<Payable> payableList = user.getPayablelist();
        List<String> shouldReceiveList = new ArrayList<>();

        for(Payable payable : payableList ){
            if( payable.getFrom().equals(user.getName()) && payable.getAmount() < 0d
                    && payable.getPaymentStatus().equals(PaymentStatus.PENDING) ){

                shouldReceiveList.add(
                        String.format("%s should RECEIVE %.2f to %s in %s group",
                                payable.getFrom(), payable.getAmount(), payable.getTo(), payable.getGroupName() )
                );

            }else if( payable.getTo().equals(user.getName()) && payable.getAmount() > 0d
                    && payable.getPaymentStatus().equals(PaymentStatus.PENDING) ){

                shouldReceiveList.add(
                        String.format("%s should RECEIVE %.2f to %s in %s group",
                                payable.getTo(), payable.getAmount(), payable.getFrom(), payable.getGroupName() )
                );
            }
        }
        return shouldReceiveList;
    }
}
