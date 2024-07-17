package service;

import java.util.Scanner;

public class UserInputOutputService {
    private static Scanner scanner = new Scanner( System.in);

    public static void closeScanner(){
        printMessageAndOneLine("⚠️...Closing Scanner...⚠️");
        scanner.close();
    }

    public static int getNumberOfRows(){
        System.out.println("Please enter the total number of Rows");
        return scanner.nextInt();
    }

    public static int getNumberOfColumns(){
        System.out.println("Please enter the total number of cols");
        return scanner.nextInt();
    }

    public static void printMessageAndOneLine( String message ){
        System.out.println();
        System.out.println(message);
        System.out.println();
    }


    public static int displayMenu(){
        System.out.println();
        System.out.println("😃---Welcome to balloon game---😃");
        System.out.println("1. Version 1 : No rules, can play until the board is full");
        System.out.println("2. version 2: Game will terminate, when one column is full ");
        System.out.println("3. Version 3 : Balloons will be filled from left to right, " +
                "if that specific col coll is filled");
        System.out.println("4. Version 4 : Balloon fills from left to right and balloons will burst, " +
                "if the column is filled by same color balloons");
        System.out.println("5. Version 5 : Balloons will fill from left to right, and the balloons will burst, " +
                "if the row is filled with same color balloons");
        System.out.println("6. ShutDown game");

        return scanner.nextInt();
    }

    public static int getColumnToDropBalloons(){
        printMessageAndOneLine("Please enter the column to drop balloon");


        return scanner.nextInt();
    }

    public static char getBalloonColor(){
        printMessageAndOneLine("Please enter the balloon color");

        return scanner.next().toUpperCase().charAt(0);
    }

    public static int doPlayerWishToContinueGame(){
        printMessageAndOneLine("Do you want to continue the game ??");
        System.out.println("1. Yes ✅");
        System.out.println("2. No ❌");


        return scanner.nextInt();
    }

    public static void printBoard( char mat[][] ){
        printMessageAndOneLine("✅---Printing board---✅");

        for( int row = 0; row < mat.length; row++ ){
            for( int col = 0; col < mat[0].length; col++ ){
                String message = mat[row][col] + "";

                if( col < mat.length -1 ){
                    message += " | ";
                }
                System.out.print( message );
            }
            System.out.println();
        }
    }
}
