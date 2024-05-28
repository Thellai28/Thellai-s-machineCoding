package controller;

import model.User;
import repository.SplitWiseRepository;
import service.UserInputOutputService;

public class AuthController {
    private static User loggedInUser;
    private static String loggedInUserName;

    public static void logIn(){
        UserInputOutputService.printMessageAndOneLine("💸---Welcome to Split wise application---💸");
        String username = UserInputOutputService.getUserName();
        String password = UserInputOutputService.getPassword();

        if( authenticateUser(username, password) ){
            loggedInUser = SplitWiseRepository.getUserFromUserObjectsMap(username);
            loggedInUserName = username;
            UserInputOutputService.printMessageAndOneLine("🎊🎊---Authentication SuccessFul---🎊🎊"+
                    "\n----------------------------------------------------------------------------------------------\n");
        }else{
            UserInputOutputService.printMessageAndOneLine("❌---Authentication Failed---❌" +
                    "\n----------------------------------------------------------------------------------------------");
        }
    }

    public static void logOut(){
        UserInputOutputService.printMessageAndOneLine("✅---Log out successful---✅");
        loggedInUser = null;
        loggedInUserName = null;
    }

    private static boolean authenticateUser( String username, String password ){
        String passwordFromDatabase = SplitWiseRepository
                .getPasswordFromUserPasswordMap(username);
        return password.equals(passwordFromDatabase);
    }

    public static User getLoggedInUser(){
        return  loggedInUser;
    }
    public static String getLoggedInUserName(){
        return loggedInUserName;
    }
}
