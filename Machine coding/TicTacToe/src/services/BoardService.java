package services;

import models.Move;
import models.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
            // implement the bot functionality: 
        }

        if( currMove != null ){
            board[currMove.getRow()][currMove.getCol()] = currMove.getPlayerSymbol();
            undoStack.push(currMove);
            if( checkWinner(currentPlayer)  ){
                winner = currentPlayer;
                initiateGameTerminationProcess();
            }else if ( isGameDraw() ){
                initiateGameTerminationProcess();
            }
        }
    }


    public static boolean checkWinner( Player currPlayer ){
        char playerSymbol = currPlayer.getSymbol();
        for( int row = 0; row < 3; row++ ){
            if( board[row][0] == playerSymbol
                    && board[row][1] == playerSymbol && board[row][2] == playerSymbol ){
                return true;
            }
        }
        for( int col = 0; col < 3; col++ ){
            if( board[0][col] == playerSymbol
                    && board[1][col] == playerSymbol && board[2][col] == playerSymbol ){
                return true;
            }
        }

        if( (board[0][0] == playerSymbol && board[1][1] == playerSymbol && board[2][2] == playerSymbol)
        || board[2][0] == playerSymbol && board[1][1] == playerSymbol && board[0][2] == playerSymbol){
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
