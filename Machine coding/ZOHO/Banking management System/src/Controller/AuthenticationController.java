package Controller;

import model.Customer;
import repository.BankingSystemRepository;
import service.UserInputOutputService;

public class AuthenticationController {
    public static Customer logged_inCustomer;
    private static String authenticatedUserName;

    public static boolean authenticateUser(){
        UserInputOutputService.printMessageAndAddOneBlankLine("Please enter Log_in details");
        String username = UserInputOutputService.getUserName();
        String password = UserInputOutputService.getPassword();
        String encryptedPassword = encryptPassword(password);

        String encryptedPasswordFromRepository = BankingSystemRepository.getEncryptedPassword(username);

        if( encryptedPasswordFromRepository != null ){
            if( encryptedPassword.equals(encryptedPasswordFromRepository) ){
                authenticatedUserName = username;
                return true;
            }
        }
        return false;
    }



    public static String encryptPassword( String password ){
        StringBuilder stringBuilder = new StringBuilder();

        for( char c : password.toCharArray() ) {

            if (Character.isDigit(c)) {
                int digit = (int) c;
                if (digit == 9) {
                    digit = 0;
                } else {
                    digit++;
                }
                stringBuilder.append(digit);
            } else if (Character.isLetter(c)) {
                if (c == 'Z') {
                    c = 'A';
                } else if (c == 'z') {
                    c = 'a';
                } else {
                    c++;
                }
                stringBuilder.append(c);
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder + "";
    }

    public static void setLoggedInCustomer( ){
        Customer customer = BankingSystemRepository.getCustomerFromCustomerMap(authenticatedUserName);

        if( customer != null ){
            logged_inCustomer = customer;
        }
    }


    public static void logOut() {
        logged_inCustomer = null;
        authenticatedUserName = null;
        String message = "👍🏼👍🏼 Successfully Logged out from account ";
        UserInputOutputService.printMessageAndAddOneBlankLine(message);
    }

    public static void login(){

        while( logged_inCustomer == null ){
            // User will not be allowed to access application until he is authenticated
            boolean isValidUser = AuthenticationController.authenticateUser();

            if( isValidUser ){
                AuthenticationController.setLoggedInCustomer();
                UserInputOutputService.printAuthenticationSuccessfulMessage(logged_inCustomer.getUserName());
            }else{
                String message = "❌❌❌ Authentication Failed !";
                UserInputOutputService.printMessageAndAddOneBlankLine(message);
            }
        }
    }
}
