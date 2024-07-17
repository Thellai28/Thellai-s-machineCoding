package controller;

import repository.TollPaymentSystemRepository;
import service.UserInputOutputService;

public class Main {
    private static boolean isTollPaymentServiceRunning = true;

    public static void main( String[] args ) {
        initializeAllServices();

        while ( isTollPaymentServiceRunning ){
            int request = UserInputOutputService.displayOptions();
            RequestHandler.handleRequest(request);
        }
    }

    public static void shutDownTollPaymentService(){
        isTollPaymentServiceRunning = false;
        UserInputOutputService.closeScanner();
    }

    private static void initializeAllServices(){
        TollPaymentSystemRepository.initializeTollPaymentRepository();
    }
}
