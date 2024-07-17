package service;

import model.Lift;

import java.util.List;
import java.util.Scanner;

public class UserInputOutputService {
    private static Scanner scanner = new Scanner(System.in);

    public static void printMessageAndBlankLine(String message ){
        System.out.println();
        System.out.println(message);
    }

    public static void closeScanner(){
        UserInputOutputService.printMessageAndBlankLine("⚠️---Closing Scanner---⚠️");
        scanner.close();
    }

    public static int displayMainMenu(){
        printMessageAndBlankLine("🛗--- Welcome to Lift management system---🛗");
        System.out.println("1. Travel from one floor to another");
        System.out.println("2. Exit");

        return  scanner.nextInt();
    }

    public static int getStartingPoint(){
        printMessageAndBlankLine("😄 Please enter your STARTING POINT...");
        return  scanner.nextInt();
    }

    public static int getDestinationPoint(){
        printMessageAndBlankLine("😄 Please enter your DESTINATION POINT");
        return scanner.nextInt();
    }

    public static void printLifts( List<Lift> liftList ){
        printMessageAndBlankLine("✅---Printing Lift status---✅");
        System.out.println();
        System.out.printf("%-12s| %-11s| %-8s| %-9s %n", "Lift Number", "Moved From", "Moved To", "Direction");
        System.out.println("---------------------------------------------------");

        for( Lift currLift : liftList ){
            System.out.println(currLift);
        }
    }
}
