package controller;

import repository.LiftRepository;
import service.UserInputOutputService;

public class Main {
    private static boolean isLiftManagementRunning = true;

    public static void main( String[] args ) {
        initiateAllServices();

        while( isLiftManagementRunning ){
            int request = UserInputOutputService.displayMainMenu();
            RequestHandler.handleRequest(request);
        }
    }

    private static void initiateAllServices(){
        LiftRepository.initializeLiftRepository();
    }

    public static void shutDown(){
        UserInputOutputService.printMessageAndBlankLine("⚠️---Shutting down Lift Management System---⚠️");
        isLiftManagementRunning = false;
        UserInputOutputService.closeScanner();
    }
}
