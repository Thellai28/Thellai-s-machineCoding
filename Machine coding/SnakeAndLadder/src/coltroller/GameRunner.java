package coltroller;

import service.SnakeAndLadderGameService;
import service.UserIoService;

public class GameRunner {
    public static boolean isGameRunning = true;

    public static void main( String[] args ) {
        initiateAllServices();

        while( isGameRunning ){
            SnakeAndLadderGameService.rollDiceAndCheckNewPosition();
            SnakeAndLadderGameService.printBoard();
            SnakeAndLadderGameService.checkForWinner();
        }
    }

    private static void initiateAllServices(){
        SnakeAndLadderGameService.initializeBoardService();
    }

    public static void shutDownGame(){
        isGameRunning = false;
        UserIoService.closeScanner();
    }
}