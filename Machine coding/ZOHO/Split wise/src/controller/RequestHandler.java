package controller;

import service.UserInputOutputService;

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
            } case 5 : {
                SettleUpController.settleUp();
                break;
            } case 6 :{
                TransactionsController.printTransactions();
                break;
            } case 7 : {
                GroupExpenseController.showGroupExpense();
                break;
            } case 8 : {
                AuthController.logOut();
                break;
            } case 9 :{
                Main.shutDown();
                break;
            } default:{
                UserInputOutputService.printMessageAndOneLine("❌---Invalid input---❌");
            }
        }
    }
}
