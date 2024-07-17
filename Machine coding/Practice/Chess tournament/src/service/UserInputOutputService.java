package service;

import model.Player;

import java.util.List;
import java.util.Scanner;

public class UserInputOutputService {
    private static Scanner scanner = new Scanner( System.in );

    public static void closeScanner(){
        printMessageAndOneLineAbove("⚠️---Closing Scanner---⚠️");
        scanner.close();
    }

    public static void printMessageAndOneLineAbove( String message ){
        System.out.println();
        System.out.println(message);
    }

    public static void printMessageAndTwoLine( String message){
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static int getPlayersCount(){
        printMessageAndOneLineAbove("😄-Please enter the total number of players in tournament");
        return scanner.nextInt();
    }

    public static int displayMainMenu(){
        printMessageAndOneLineAbove("🏇🏼---Welcome to Chess tournament---🏇🏼");
        System.out.println("1. Start tournament");
        System.out.println("2. exit");

        return scanner.nextInt();
    }

    public static void printCurrentRoundWinner( Player p1, float p1_Score, String p1_GameStatus,
                                                Player p2, float p2_Score, String p2_GameStatus ){
        printMessageAndTwoLine("🥇---Printing current match details---🥇");
        System.out.printf("%-9s| %-6s| %-7s %n", "Player", "Score", "Status");
        System.out.println("--------------------------");
        System.out.printf("%-9s| %-6.2f| %-7s %n", p1.getPlayerName(), p1_Score, p1_GameStatus);
        System.out.printf("%-9s| %-6.2f| %-7s %n", p2.getPlayerName(), p2_Score, p2_GameStatus);
    }

    public static void printAllPlayers( List<Player> playerList ){
        printMessageAndTwoLine("✅---Printing all Players---✅");
        System.out.printf("%-9s| %-6s %n","Player", "Score");
        System.out.println("-----------------");
        for( Player currPlayer : playerList ){
            System.out.println(currPlayer);
        }
    }
}
