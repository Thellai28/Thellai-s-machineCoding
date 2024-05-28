package controller;
import repository.SplitWiseRepository;
import service.UserInputOutputService;

public class Main {
    private static boolean isSplitWiseRunning = true;

    public static void main( String[] args ) {
        initializeAllServices();

        while( isSplitWiseRunning ){
            if( AuthController.getLoggedInUser() == null ){
                AuthController.logIn();
                continue; // Application will not move further until the user is logged in :
            }
            int request = UserInputOutputService.displayMainMenu();
            RequestHandler.handleRequest(request);
        }

    }
    public static void shutDown(){
        UserInputOutputService.closeScanner();
        UserInputOutputService.printMessageAndOneLine("⚠️---Shutting down Split wise---⚠️");
        isSplitWiseRunning = false;
    }

    private static void initializeAllServices() {
        UserInputOutputService.initializeScanner();
        SplitWiseRepository.initializeSplitWiseRepository();
    }
}
