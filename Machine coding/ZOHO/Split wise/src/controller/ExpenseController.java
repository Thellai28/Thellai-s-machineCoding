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
        // I don't want to modify the original List :
        List<String> duplicateParticipantList = new ArrayList<>(group.getParticipantsList());
        List<String> selectedUsersList = new ArrayList<>();
        boolean wantToChooseMore;

        do{
            int idx = UserInputOutputService.displayListAndGetChoice(duplicateParticipantList,
                    "😉- Select one user from the list");
            String currSelectedUser = duplicateParticipantList.get(idx);

            duplicateParticipantList.remove(currSelectedUser);
            selectedUsersList.add( currSelectedUser );

            // I want to store false, if response is '2'
            wantToChooseMore = UserInputOutputService.wantToChooseMore() != 2;

        }while( wantToChooseMore && !duplicateParticipantList.isEmpty() );

        return selectedUsersList;
    }

    private static void createSplits( Expense expense, Group group){
        SplitMethod preferredSplitMethod = getPreferredSplitMethod();

        if( preferredSplitMethod.equals(SplitMethod.EQUAL_SPLIT) ){
            generateEqualSplits( expense, group );
        }else if( preferredSplitMethod.equals(SplitMethod.PERCENTAGE_SPLIT) ){
            generatePercentageSplits( expense, group );
        }else {
            generateExactSplit( expense, group);
        }
    }

    private static SplitMethod getPreferredSplitMethod(){
        List<SplitMethod> splitMethodList = Arrays.asList(SplitMethod.values());
        int idx = UserInputOutputService.displaySplitMethodsAndGetChoice(splitMethodList);
        return  splitMethodList.get(idx);
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

        if(!payableListFromGroup.isEmpty()){
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
            Split split = new Split(amountPerPerson, expense.getPaidBy(),user,
                    SplitMethod.EQUAL_SPLIT, totalAmount);

            // Adding newly created split into the expense :
            expense.getSplitList().add(split);

            // Creating or updating payable
            if( !user.equals(expense.getPaidBy()) ){
                createOrUpdatePayableFromSplit(split, group);
                /*
                    If the creator of the expense is involved in the split, we don't have to create payable for the
                    amount the user paid for himself, because if you try to create payable for himself, the 'From' and
                    'To' will have the same name, to avoid this we use -> !user.equals(expense.getPaidBy())
                */
            }
        }
        group.getExpenseList().add(expense);
    }

    private static void generatePercentageSplits( Expense expense, Group group ){
        List<String>usersInvolvedList = expense.getUsersInvolved();
        double totalAmount = expense.getAmountPaid();
        int balancePercentage = 100;

        int usersRemaining = usersInvolvedList.size();
        
        for( String user : usersInvolvedList ){
            int currUserPercentage = UserInputOutputService.getPercentage( user, balancePercentage, usersRemaining );
            double currentUserPayableAmount = calculatePercentageAmount(currUserPercentage, totalAmount);

            Split split = new Split(currentUserPayableAmount,
                    expense.getPaidBy(), user, SplitMethod.PERCENTAGE_SPLIT, totalAmount);

            // Adding newly created split into the expense :
            expense.getSplitList().add(split);

            // creating or updating payable :
            if( !user.equals(expense.getPaidBy()) ){
                createOrUpdatePayableFromSplit(split, group);
                /*
                    If the creator of the expense is involved in the split, we don't have to create payable for the
                    amount the user paid for himself, because if you try to create payable for himself, the 'From' and
                    'To' will have the same name, to avoid this we use -> !user.equals(expense.getPaidBy())
                */
            }

            balancePercentage -= currUserPercentage;
            usersRemaining--;
        }
        group.getExpenseList().add(expense);
    }

    private static void generateExactSplit(Expense expense, Group group ){
        List<String>usersInvolvedList = expense.getUsersInvolved();
        double totalAmount = expense.getAmountPaid();

        int usersRemaining = usersInvolvedList.size();

        for( String user : usersInvolvedList ){

            double currentUserExactAmount = UserInputOutputService.getExactAmount(user, totalAmount, usersRemaining);
            Split split = new Split(currentUserExactAmount, expense.getPaidBy(),
                    user, SplitMethod.EXACT_AMOUNT, expense.getAmountPaid());

            // Adding newly created split into the expense :
            expense.getSplitList().add(split);

            // creating or updating payable :
            if( !user.equals(expense.getPaidBy()) ){
                createOrUpdatePayableFromSplit(split, group);
                /*
                    If the creator of the expense is involved in the split, we don't have to create payable for the
                    amount the user paid for himself, because if you try to create payable for himself, the 'From' and
                    'To' will have the same name, to avoid this we use -> !user.equals(expense.getPaidBy())
                */
            }

            totalAmount -= currentUserExactAmount;
            usersRemaining--;
        }
        group.getExpenseList().add(expense);
    }

    private static double calculatePercentageAmount( int percentage, double totalAmount ){
       return (totalAmount * percentage) / 100;
    }
}
