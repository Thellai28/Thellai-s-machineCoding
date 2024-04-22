package services;

import models.Move;
import models.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static services.BotPlayerService.botMove;
import static services.DisplayInConsoleService.*;
import static controllers.GameController.*;

public class BoardService {
    public static char[][] board = new char[3][3];
    public static Map<Character, Player> playerMap = new HashMap<>();
    public static Stack<Move> undoStack = new Stack<>();
    public static Stack<Move> redoStack = new Stack<>();
    public static Player winner = null;
    public static char currentPlayerSymbol = 'X';
    

    public static void initializeBoard(){
        for( int row = 0; row < 3; row++ ){
            for( int col = 0; col < 3; col++ ){
                board[row][col] = ' ';
            }
        }
    }

    public static void endGame(){
        isGameRunning = false;
    }


    private static boolean isValidMove( Move currMove ){
        int row = currMove.getRow();
        int col = currMove.getCol();
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    public static void switchPlayer(){
        if( isGameRunning ){
            currentPlayerSymbol = (currentPlayerSymbol == 'X') ? 'O' : 'X';
        }
    }

    public static void initiateGameTerminationProcess(){
        closeScanner();
        endGame();
    }

    public static void makeMove(  ) {
        Player currentPlayer = playerMap.get(currentPlayerSymbol);
        boolean isValidationFailed = false;
        Move currMove = null;

        if( currentPlayer.getPlayerType().equals("HUMAN") ){
            do {
                if(isValidationFailed){
                    printInvalidCoOrdinatesMessage();
                }
                currMove = getCellCoOrdinatedFromUser(currentPlayer);
                isValidationFailed = true;
            } while (!isValidMove(currMove));
        }else{
            currMove =  botMove();
            System.out.println();
            System.out.println("The bot move is " + currMove.getRow() + ", "+ currMove.getCol());
        }

        if( currMove != null ){
            board[currMove.getRow()][currMove.getCol()] = currMove.getPlayerSymbol();
            undoStack.push(currMove);
            if( checkWinner()  ){
                winner = currentPlayer;
                initiateGameTerminationProcess();
            }else if ( isGameDraw() ){
                initiateGameTerminationProcess();
            }
        }
    }


    public static boolean checkWinner(  ){
        // Checking rows :
        for( int row = 0; row < 3; row++ ){
            if( board[row][0] == currentPlayerSymbol
                    && board[row][1] == currentPlayerSymbol && board[row][2] == currentPlayerSymbol ){
                return true;
            }
        }
        // Checking columns:
        for( int col = 0; col < 3; col++ ){
            if( board[0][col] == currentPlayerSymbol
                    && board[1][col] == currentPlayerSymbol && board[2][col] == currentPlayerSymbol ){
                return true;
            }
        }

        // checking diagonals :

        if( (board[0][0] == currentPlayerSymbol && board[1][1] == currentPlayerSymbol
                && board[2][2] == currentPlayerSymbol) ||
                (board[2][0] == currentPlayerSymbol && board[1][1] == currentPlayerSymbol
                        && board[0][2] == currentPlayerSymbol)){
            return true;
        }
        return false;
    }




    public static boolean isGameDraw(){
        for( int row = 0; row < 3; row++ ){
            for( int col = 0; col < 3; col++ ){
                if( board[row][col] == ' ' ){
                    return false;
                }
            }
        }
        return true;
    }

}
