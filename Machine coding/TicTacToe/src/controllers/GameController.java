package controllers;


import static services.BoardService.*;
import static services.DisplayInConsoleService.*;

public class GameController {
    public static boolean isGameRunning = true;

    public static void main( String[] args ) {
        initializeBoard();
        getPlayerInfoFromConsole();

        while( isGameRunning ){
            makeMove();
            switchPlayer();
            displayBoard();
        }

        if( winner == null ){
            displayGameDrawMessage();
        }else{
            displayWinnerMessage(winner);
        }
    }
}
