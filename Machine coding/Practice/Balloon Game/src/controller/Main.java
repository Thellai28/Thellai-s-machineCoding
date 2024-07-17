package controller;

import service.BoardService;
import service.UserInputOutputService;

public class Main {
    private static boolean isBalloonGameRunning = true;

    public static void main( String[] args ) {
        initializeAllServices();

        while( isBalloonGameRunning ){
            int request = UserInputOutputService.displayMenu();
            RequestHandler.handleRequest(request);
        }

    }

    private static void initializeAllServices(){
        BoardService.initializeBoardService();
    }

    public static void shutDownBalloonGame(){
        isBalloonGameRunning = false;
        UserInputOutputService.closeScanner();
    }
}
