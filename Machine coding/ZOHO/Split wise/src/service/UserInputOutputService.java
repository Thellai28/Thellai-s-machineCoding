package service;

import model.SplitMethods;

import java.util.List;
import java.util.Scanner;

public class UserInputOutputService {
    private static Scanner scanner;
    public static void initializeScanner(){
        if( scanner == null ){
            printMessageAndOneLine("✅---Initializing Scanner---✅");
            scanner = new Scanner(System.in);
        }
    }

    public static void closeScanner(){
        if( scanner != null ){
            printMessageAndOneLine("⚠️---Closing scanner---⚠️");
            scanner.close();
        }
    }

    //--------------------------< Utility Methods >-------------------------------

    public static void printMessageAndOneLine( String message ){
        System.out.println();
        System.out.println(message);
    }

    public static int displayMainMenu(){
        System.out.println("1. Create new Group");
        System.out.println("2. Add Users into group");
        System.out.println("3. Add expense into a group");
        System.out.println("4. Show balance & pending expenses");
        System.out.println("5. Settle up Expenses");
        System.out.println("6. Transaction history");
        System.out.println("7. Show group expenses");
        System.out.println("8. Log out");
        System.out.println("9. Exit");

        return scanner.nextInt();
    }

    public static String getUserName(){
        printMessageAndOneLine("😄-Please enter your user name");
        return scanner.next();
    }

    public static String getPassword() {
        printMessageAndOneLine("😄-Please enter your password");
        return scanner.next();
    }

    public static String getGroupName(){
        printMessageAndOneLine("😄-Please enter group name");
        return scanner.next();
    }

    public static int needToAddNewUsers(){
        printMessageAndOneLine("🤔 Do you need to add new users into the group ?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        return scanner.nextInt();
    }

    public static int getUsersCount(){
        printMessageAndOneLine("😄-Please enter the number of users you need to add into group");
        return scanner.nextInt();
    }

    public static int displayListAndGetChoice( List<String> list  ){
        printMessageAndOneLine("😄-Please select an option from below");
        for( int i = 0; i< list.size(); i++ ){
            System.out.println(i+1 +". " + list.get(i) + ". ");
        }
        return scanner.nextInt() -1; // converting 1 based to 0 based :
    }

    public static void displayList( List<String> list, String message){
        printMessageAndOneLine(message);
        for( int i = 0; i< list.size(); i++ ){
            System.out.println(i+1 +". " + list.get(i) + ". ");
        }
    }
    public static int displaySplitMethodsAndGetChoice( List<SplitMethods> list ){
        printMessageAndOneLine("😄-Please select an option from below");
        for( int i = 0; i< list.size(); i++ ){
            System.out.println(i+1 +". " + list.get(i) + ". ");
        }
        return scanner.nextInt() -1;  // converting 1 based to 0 based :
    }

    public static String getDescriptionOfExpense(){
        printMessageAndOneLine("😄-Please enter the description of expense");
        return scanner.next();
    }

    public static int wantToChooseMore(){
        printMessageAndOneLine("🤔-Do you want to select more users ?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        return scanner.nextInt();
    }

    public static double getAmountPaid(){
        printMessageAndOneLine("😄-Please enter the amount paid");
        return scanner.nextDouble();
    }

    public static int isUserInvolvedInCurrentExpense(){
        printMessageAndOneLine("🤔-Are you involved in this current split ?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        return  scanner.nextInt();
    }

    public static int getPercentage( String user, int balancePercentage, int usersRemaining ){
        printMessageAndOneLine("User name : " + user +
                "\n Remaining percentage : " + balancePercentage +", Remaining Users : " + usersRemaining );
        System.out.println("😄-Please enter current user percentage ");
        return scanner.nextInt();
    }

    public static double getExactAmount( String user, double remainingAmount, int usersRemaining ){
        printMessageAndOneLine("User name : " + user +
                "\n Remaining amount : " + remainingAmount +", Remaining Users : " + usersRemaining );
        System.out.println("😄-Please enter current user exact amount ");
        return scanner.nextDouble();

    }

    public static int getPercentageOfPaidUserPortion(){
        printMessageAndOneLine("😄-Please enter what is your portion of percentage in total amount ");
        return scanner.nextInt();
    }

    public static double getExactAmountOfPaidUser(){
        printMessageAndOneLine("😄-Please enter what is your exact amount in total expense");
        return scanner.nextDouble();
    }




}