package controller;

import model.*;
import repository.SplitWiseRepository;
import service.CommonUtilsService;
import service.UserInputOutputService;

import java.util.*;

public class ExpenseController {
    public static void createNewExpense(){
        Group selectedGroup = CommonUtilsService.getGroupName();
        String expenseDescription = UserInputOutputService.getDescriptionOfExpense();
        List<String> usersInvolvedInExpense = getUsersInvolvedInExpense(selectedGroup);
        double amountPaid = UserInputOutputService.getAmountPaid();

        Expense expense = new Expense(amountPaid, AuthController.getLoggedInUserName(), expenseDescription);
        expense.setUsersInvolved(usersInvolvedInExpense);

        createSplits(expense, selectedGroup);

        UserInputOutputService.printMessageAndOneLine("✅---Expense Created Successfully---✅");
    }


    private static List<String> getUsersInvolvedInExpense( Group group ){
        List<String> participantsList = group.getParticipantsList();
        Set<String> userNameSet = new HashSet<>(); // user might select same name again and again :
        boolean wantToChooseMore;

        do{
            int idx = UserInputOutputService.displayListAndGetChoice(participantsList);
            userNameSet.add( participantsList.get(idx));
            wantToChooseMore = ( UserInputOutputService.wantToChooseMore() == 2 )? false : true;
        }while( wantToChooseMore );

        // Sometime user might not be included in the expense, but he might have paid the bill :
        int isUserInvolved = UserInputOutputService.isUserInvolvedInCurrentExpense();
        if( isUserInvolved == 1 ){
            userNameSet.add( AuthController.getLoggedInUserName() );
        }

        return new ArrayList<>(userNameSet);
    }

    private static void createSplits( Expense expense, Group group){
        SplitMethods preferredSplitMethod = getPreferredSplitMethod();

        if( preferredSplitMethod.equals(SplitMethods.EQUAL_SPLIT) ){
            generateEqualSplits( expense, group );
        }else if( preferredSplitMethod.equals(SplitMethods.PERCENTAGE_SPLIT) ){
            generatePercentageSplits( expense, group );
        }else {
            generateExactSplit( expense, group);
        }
    }

    private static SplitMethods getPreferredSplitMethod(){
        List<SplitMethods> splitMethodsList = Arrays.asList(SplitMethods.values());
        int idx = UserInputOutputService.displaySplitMethodsAndGetChoice(splitMethodsList);
        return  splitMethodsList.get(idx);
    }



    private static void createOrUpdatePayableFromSplit( Split split, Group group ){
        Payable payable = getPayableFromPayableList( split, group.getPayableList() );

        if( payable == null ){
            createPayable(split,group);
        }else{
            updatePayable(payable, split);
        }
    }

    private static Payable getPayableFromPayableList( Split split,List<Payable> payableListFromGroup ){

        if( payableListFromGroup.size() > 0 ){
            for( Payable payable : payableListFromGroup ){
                if( payable.getFrom().equals(split.getPaidBy()) && payable.getTo().equals(split.getRecipient())){
                    return payable;
                }else if( payable.getFrom().equals(split.getRecipient()) && payable.getTo().equals(split.getPaidBy())){
                    return payable;
                }
            }
        }
        return null;
    }

    private static void addPayableInRespectiveUsers( Payable payable, String username ){
        User user = SplitWiseRepository.getUserFromUserObjectsMap(username);
        user.getPayablelist().add(payable);
    }
    private static void addPayableIntoTheGroup( Payable payable, Group group ){
        group.getPayableList().add(payable);
    }

    private static void createPayable( Split split, Group group ){
        Payable newPayable = new Payable(split.getAmountToPay(),
                split.getRecipient(), group.getGroupName(), split.getPaidBy());
            /*
                Recipient will pay amount, so he is in from place, the Paid by user will receive amount from recipient, so
                he is in 'To' place.
            */
        addPayableInRespectiveUsers(newPayable, split.getPaidBy());
        addPayableInRespectiveUsers(newPayable, split.getRecipient());
        addPayableIntoTheGroup(newPayable, group);
    }

    private static void updatePayable( Payable payable, Split split ){
        if( payable.getFrom().equals(split.getPaidBy()) ){
            payable.setAmount( payable.getAmount() - split.getAmountToPay() );
                /*
                    Since the paidBy user is in from position, that means, paid by user should give some amount to
                    split.recipient already. in thiss scenarios, the recipient need to pay back some amount
                    to paid-by-user( user in from position in payable ),
                    so this split amount will be reduced from the total amount should be paid-by
                    from-user(payable) to to-user(payable) will be reduced ,
                    so we do payable.getAmount() - split.getAmountToPay();
                */
        }else if( payable.getTo().equals(split.getPaidBy()) ){
            payable.setAmount( payable.getAmount() + split.getAmountToPay() );
                /*
                    since the paid-by user is already in 'To' position in payable, that means, this use should already
                    receive some amount of money from split.recipient, so the total amount that should be sent by
                    split.getRecipient() will increase, so we do payable.getAmount() + split.getAmountToPay() ;
                */
        }
    }

    private static void generateEqualSplits(Expense expense, Group group){
        double totalAmount = expense.getAmountPaid();
        int membersInvolved = expense.getUsersInvolved().size();
        double amountPerPerson = totalAmount / membersInvolved; // type promotion: double/int = double:

        for( String user : expense.getUsersInvolved() ){
            if( user.equals(expense.getPaidBy()) ) continue; // sometimes the user might be the same person who paid :

            Split split = new Split(amountPerPerson, expense.getPaidBy(),user,
                    SplitMethods.EQUAL_SPLIT, totalAmount);

            createOrUpdatePayableFromSplit(split, group);
        }
    }

    private static void generatePercentageSplits( Expense expense, Group group ){
        List<String>usersInvolvedList = expense.getUsersInvolved();
        double totalAmount = expense.getAmountPaid();

        if( usersInvolvedList.contains(expense.getPaidBy()) ){
            int percentage = UserInputOutputService.getPercentageOfPaidUserPortion();
            totalAmount -= calculatePercentageAmount(percentage, totalAmount);
            /*
                If the list contains paid-by user, that means in the total expense amount, paid-by user
                expense is also involved.
                So we remove the portion or percentage of amount that user paid to himself
            */
        }
        int balancePercentage = 100;
        int usersRemaining = usersInvolvedList.size();


        for( String user : usersInvolvedList ){
            if( user.equals(expense.getPaidBy()) ){
                usersRemaining--;
                continue;
               // Since we have calculated the paid users percentage earlier, we don't consider him in this
            }
            int currUserPercentage = UserInputOutputService.getPercentage( user, balancePercentage, usersRemaining );
            double currentUserPayableAmount = calculatePercentageAmount(currUserPercentage, totalAmount);
            Split split = new Split(currentUserPayableAmount,
                    expense.getPaidBy(), user, SplitMethods.PERCENTAGE_SPLIT, totalAmount);

            createOrUpdatePayableFromSplit(split, group);

            balancePercentage -= currUserPercentage;
            usersRemaining--;
        }
    }

    private static void generateExactSplit(Expense expense, Group group ){
        List<String>usersInvolvedList = expense.getUsersInvolved();
        double totalAmount = expense.getAmountPaid();

        if( usersInvolvedList.contains(expense.getPaidBy()) ){
            double paidUserExactAmount = UserInputOutputService.getExactAmountOfPaidUser();
            totalAmount -= paidUserExactAmount;
        }
        int usersRemaining = usersInvolvedList.size();

        for( String user : usersInvolvedList ){
            if(user.equals(expense.getPaidBy()) ){
                usersRemaining--;
                continue;
            }

            double currentUserExactAmount = UserInputOutputService.getExactAmount(user, totalAmount, usersRemaining);
            Split split = new Split(currentUserExactAmount, expense.getPaidBy(),
                    user, SplitMethods.EXACT_AMOUNT, totalAmount);

            createOrUpdatePayableFromSplit(split, group);
            totalAmount -= currentUserExactAmount;
            usersRemaining--;
        }
    }

    private static double calculatePercentageAmount( int percentage, double totalAmount ){
       return (totalAmount * percentage) / 100;
    }
}
