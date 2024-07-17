package controller;

import repository.ChessTournamentRepository;
import service.UserInputOutputService;

public class Main {
    private static boolean isTournamentRunning = true;

    public static void main( String[] args ) {
        initializeAllServices();
        while( isTournamentRunning ){
            int request = UserInputOutputService.displayMainMenu();
            RequestHandler.handleRequest(request);
        }

    }

    public static void shutDown(){
        UserInputOutputService.closeScanner();
        UserInputOutputService.printMessageAndOneLineAbove("⚠️---Shutting down chess tournament---⚠️");
        isTournamentRunning = false;
    }

    private static void initializeAllServices(){
        ChessTournamentRepository.initializeChessTournamentRepository();
    }
}
