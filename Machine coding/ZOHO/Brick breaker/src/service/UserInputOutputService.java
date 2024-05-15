package service;

import java.util.Scanner;

public class UserInputOutputService {
    private static Scanner scanner = new Scanner( System.in );

    public static int displayWelcomeMessage(){
        System.out.println("Welcome to Brick breaker game");
        System.out.println("1. Move straight");
        System.out.println("2. Move left diagonally");
        System.out.println("3. Move right diagonally");
        System.out.println("4. Exit");
        return scanner.nextInt();
    }

    public static void closeScanner(){
        scanner.close();
    }

    public static int getBoardSize(){
        System.out.println("Please enter board size");
        return scanner.nextInt();
    }

    public static int getBallColValue(){
        System.out.println("Please enter initial boll col position");
        return scanner.nextInt();
    }

    public static void displayBrickSmashedMessage( int row, int col ){
        System.out.println("🎊🎊🎊 Smashed a brick at row : " + row + ", col : " + col);
        System.out.println();
    }

    public static void displayBoard( char board[][] ){
        System.out.println("------------------------------------------------------------");
        System.out.println();

        for( int row = 0; row < board.length; row ++ ){
            for( int col = 0; col < board.length; col++ ){
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printMessageAndBlankLine( String message ){
        System.out.println(message);
        System.out.println();
    }

}
