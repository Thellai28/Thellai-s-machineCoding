package controller;

import model.User;
import service.UserInputOutputService;

public class TransactionsController {

    public static void printTransactions(){
        User loggedInUser = AuthController.getLoggedInUser();
        if( loggedInUser.getTransactionHistory().isEmpty()){
            UserInputOutputService
                    .printMessageAndOneLine("⚠️---There is no transactions to display---⚠️");
        }else{
            UserInputOutputService.displayList(loggedInUser.getTransactionHistory(),
                    "✅---Printing Transaction history---✅");
        }
    }
}
