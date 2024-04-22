package services;

import models.Move;
import models.Player;

import java.util.Scanner;

import static services.BoardService.*;
import static services.BoardService.playerMap;

public class DisplayInConsoleService {
    public static Scanner scanner = new Scanner( System.in);

    public static void getPlayerInfoFromConsole(){
        String message = "\nHi, welcome to tic tac toe game, please choose your opponent" +
                "\n 1.Player \n 2.Bot";
        System.out.println(message);
        int response = scanner.nextInt();

        switch (response){
            case 1: {
                System.out.println("Please provide the player_1 name");
                String player_1_name = scanner.next();
                Player human1 = new Player(player_1_name, "HUMAN", 'X');
                playerMap.put('X', human1);


                System.out.println("Please provide the player_2 name");
                String player_2_name = scanner.next();
                Player human2 = new Player(player_2_name, "HUMAN", 'O');
                playerMap.put('O', human2);
                break;
            } case 2 : {
                System.out.println("Please provide the player_1 name");
                String player_1_name = scanner.next();
                Player player1 = new Player(player_1_name, "HUMAN", 'X');
                playerMap.put('X', player1);

                Player botPlayer = new Player("J.A.R.V.I.S", "BOT", 'O');
                playerMap.put('O', botPlayer);
                break;
            } default:{
                System.out.println("Invalid input, please choose again" );
                printHorizontalLine();
                getPlayerInfoFromConsole();
            }
        }
    }

    public static Move getCellCoOrdinatedFromUser( Player currentPlayer){
        printHorizontalLine();
        System.out.println("\nHi "+ currentPlayer.getName());
        System.out.print("\nPlease enter the row number: ");
        int row = scanner.nextInt();
        System.out.print("\nPlease enter the column number:");
        int col = scanner.nextInt();
        return new Move(row, col, currentPlayer.getSymbol() );
    }

    public static void displayBoard(){
        for( int row = 0; row < 3; row++ ){
            for( int col = 0; col < 3; col++ ){
                String line = board[row][col] +"";
                if( col < 2 ){
                    line += " | ";
                }
                System.out.print(line);
            }
            if( row < 2 ){
                System.out.println("\n--------------------");
            }
        }
    }

    public static void displayGameDrawMessage(){
        printHorizontalLine();
        System.out.println("\nGame is draw");
        System.out.println();
        displayBoard();
    }

    public static void displayWinnerMessage(Player winner){
        printHorizontalLine();
        System.out.println("\nHurrah!! " + winner.getName() + " won the game");
        System.out.println();
        displayBoard();
    }


    public static void closeScanner(){
        scanner.close();
    }

    public static void printHorizontalLine(){
        System.out.println("\n-------------------------------------------------------------------------------");
    }

    public static void printInvalidCoOrdinatesMessage(){
        System.out.print("\n-Invalid coOrdinated, please enter a valid coOrdinates");
    }

}
