package service;


import coltroller.GameRunner;
import model.Player;
import model.SnakeAndLadderGame;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeAndLadderGameService {
    private static SnakeAndLadderGame snakeAndLadderGame;
    private static Queue<Player> playerQueue;

    public static void initializeBoardService(){
        snakeAndLadderGame = new SnakeAndLadderGame();
        playerQueue = new LinkedList<>();
        addPlayersIntoQueue();
    }

    private static void addPlayersIntoQueue(){
        for( Player currPlayer  : snakeAndLadderGame.getPlayerMap().values() ){
            playerQueue.add( currPlayer );
        }
    }

    public static void rollDiceAndCheckNewPosition( ){
        final int DESTINATION = 100;
        int diceNumber = DiceService.rollDice();

        Player currPlayer = playerQueue.poll();
        int newPosition = finalPositionAfterCheckingSnakesAndLadders(diceNumber, currPlayer);

        if( newPosition <= DESTINATION ){
            moveToNewPosition(newPosition, currPlayer);
        }
        playerQueue.add(currPlayer);
    }

    private static int finalPositionAfterCheckingSnakesAndLadders( int diceNumber, Player currPlayer ){
        char playerSymbol = currPlayer.getSymbol();
        int previousPosition = 0;
        if( snakeAndLadderGame.getPlayerPositionMap().containsKey(currPlayer.getSymbol()) ){
            previousPosition = snakeAndLadderGame.getPlayerPositionMap().get(playerSymbol);
        }

        int newPosition = previousPosition + diceNumber;

        newPosition = checkForSnakesAndUpdatePosition(newPosition, currPlayer);
        newPosition = checkForLaddersAndUpdatePosition( newPosition, currPlayer );

        return newPosition;
    }

    private static int checkForSnakesAndUpdatePosition(int newPosition, Player currPlayer ){
        if( snakeAndLadderGame.getSnakeMap().containsKey(newPosition) ){
            System.out.println("‼️ " + currPlayer.getName() + " encountered a 🐍snake🐍 at new position @ " + newPosition);
            newPosition = snakeAndLadderGame.getSnakeMap().get(newPosition);

            System.out.println("❗ moving " + currPlayer.getName() + " to the tail of the snake, @ "+ newPosition);
            UserIoService.printTwoEmptyLines();
        }
        return newPosition;
    }

    private static int checkForLaddersAndUpdatePosition( int newPosition, Player currPlayer ){
        if( snakeAndLadderGame.getLadderMap().containsKey(newPosition) ){
            System.out.println("🤩🤩 " + currPlayer.getName() + " encountered a latter @ "+ newPosition);
            newPosition = snakeAndLadderGame.getLadderMap().get(newPosition);

            System.out.println("🔝Moving " + currPlayer.getName() + " to new position @ " + newPosition);
            UserIoService.printTwoEmptyLines();
        }
        return newPosition;
    }

    private static void moveToNewPosition( int newPosition, Player currPlayer ){
        char board[] = snakeAndLadderGame.getBoard();
        if( snakeAndLadderGame.getPlayerPositionMap().containsKey(currPlayer.getSymbol()) ){
            int previousPosition = snakeAndLadderGame.getPlayerPositionMap().get(currPlayer.getSymbol() );
            board[previousPosition] = '.'; // erasing previous position :
        }

        board[newPosition] = currPlayer.getSymbol();
        snakeAndLadderGame.getPlayerPositionMap().put(currPlayer.getSymbol(), newPosition); //updating new position :
    }

    public static void checkForWinner(){
        final int DESTINATION = 100;
        char board[] = snakeAndLadderGame.getBoard();

        if( board[DESTINATION] != '.'){
            Player winner = snakeAndLadderGame.getPlayerMap().get(board[DESTINATION]);

            if( winner != null ){
                System.out.println("🎉🎉🎉🎉 Hurrah! "+ winner.getName() + " won the game" );
                GameRunner.shutDownGame();
            }
        }
    }

    public static void printBoard(){
        char board[] = snakeAndLadderGame.getBoard();
        final int DESTINATION = 100;
        final int CELLS_IN_ROW = 10;

//        for( int i = DESTINATION; i > 0; i-- ){
//            String content = ""+board[i];
//            if( i % CELLS_IN_ROW > 1 && i % CELLS_IN_ROW < CELLS_IN_ROW){
//                content += " | ";
//            }
//            System.out.println(content);
//
//            if( i % CELLS_IN_ROW == 0 ){
//                System.out.println();
//                System.out.println("-------------------------");
//            }
//        }

        for( int i = 0; i<= DESTINATION; i++ ){
            String content = snakeAndLadderGame.getBoard()[i]+ " | ";
            System.out.print(content);

            if( i % CELLS_IN_ROW == 0 ){
                System.out.println("----------------------------------------------");
            }
        }
    }
}
