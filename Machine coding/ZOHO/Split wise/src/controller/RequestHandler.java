package controller;

public class RequestHandler {
    public static void handleRequest( int request ){
        switch (request){
            case 1 :{
                GroupAndUserController.handleNewGroupCreation();
                break;
            } case 2 : {
                GroupAndUserController.selectGroupAndAddUsers();
                break;
            } case 3 : {
                ExpenseController.createNewExpense();
                break;
            } case 4 : {
                PendingExpenseController.generatePendingExpenses();
                break;
            }

        }
    }
}
