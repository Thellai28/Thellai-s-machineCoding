package controller;
import service.UserInputOutputService;

public class Main {
    private static boolean isEventManagementRunning = true;

    public static void main( String[] args ) {
        while( isEventManagementRunning ){
            int request = UserInputOutputService.displayMainMenu();
            RequestHandler.handleRequest(request);
        }

    }

    public static void shutDown(){
        UserInputOutputService.printMessageAndOneLine("⚠️---Shutting down Event management----⚠️");
        isEventManagementRunning = false;
        UserInputOutputService.closeScanner();
    }
}
