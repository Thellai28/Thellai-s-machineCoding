package service;

import java.util.Scanner;

public class UserIoService {
    private static Scanner scanner = new Scanner(System.in);

    public static int getPlayerCount(){
        System.out.println("😄😄 Welcome to snake and ladder game 😄😄");
        System.out.println("Please enter the number of players ");
        return scanner.nextInt();
    }

    public static String getPlayerName(){
        System.out.println("Please enter player name");
        return scanner.next();
    }

    public static int getSnakesCount(){
        System.out.println("Please enter the number of snakes present in this game");
        return scanner.nextInt();
    }

    public static int getSnakeHeadPosition(){
        System.out.println("Please enter the cell of snakes head position");
        return scanner.nextInt();
    }

    public static int getSnakeTailPosition(){
        System.out.println("Please enter the cell of snakes tail position");
        return scanner.nextInt();
    }

    public static int getLadderCount(){
        System.out.println("Please enter the number of ladders present in game");
        return scanner.nextInt();
    }

    public static int getLadderStartingPosition(){
        System.out.println("Please enter the ladder starting point");
        return scanner.nextInt();
    }

    public static int getLadderEndingPosition() {
        System.out.println("Please enter the ladder Ending point");
        return scanner.nextInt();
    }

    public static void printHorizontalLines(){
        System.out.println("-------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
    }

    public static void printTwoEmptyLines(){
        System.out.println();
        System.out.println();
    }

    public static void closeScanner(){
        scanner.close();
    }
}
