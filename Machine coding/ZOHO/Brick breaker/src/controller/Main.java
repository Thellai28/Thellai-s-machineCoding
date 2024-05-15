package controller;

import service.GameService;
import service.UserInputOutputService;

public class Main {
    private static boolean isGameRunning = true;

    public static void main( String[] args ) {
        initializeAllServices();

        while( isGameRunning ){
            int request = UserInputOutputService.displayWelcomeMessage();
            RequestHandler.handleRequest(request);
        }

    }

    public static void shutDownGame(){
        isGameRunning = false;
        UserInputOutputService.closeScanner();
    }

    private static void initializeAllServices(){
        GameService.initializeGameService();
    }
}
