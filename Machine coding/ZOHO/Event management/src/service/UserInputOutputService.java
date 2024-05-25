package service;

import java.util.List;
import java.util.Scanner;

public class UserInputOutputService {

    private static Scanner scanner = new Scanner( System.in );

    public static void closeScanner(){
        printMessageAndOneLine("⚠️---Closing scanner⚠️");
        scanner.close();
    }
    public static void printMessageAndOneLine(String message){
        System.out.println();
        System.out.println(message);
    }

    public static void printMessageAndTwoLines( String message ){
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static int displayMainMenu(){
        printMessageAndOneLine("😃---Welcome to event management application---😃");
        System.out.println("1. Manage Event");
        System.out.println("2. Exit");

        return scanner.nextInt();
    }

    public static int getTotalEventsCount(){
        printMessageAndOneLine("😄Please enter the total number of events");
        int count =  scanner.nextInt();
        return count;
    }

    public static String getEventDetails(){
        printMessageAndOneLine("😄 Please enter the event");
        return scanner.next();
    }

    public static void printScheduledEvents( int day, List<String> output ){
        printMessageAndTwoLines("✅Printing day " + day + " schedule✅");
        for( String s : output ){
            System.out.println(s);
        }
    }
}
