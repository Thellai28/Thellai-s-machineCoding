package controller;

import repository.TaxiManagementSystemRepository;
import service.UserInputOutputService;

public class Main {
    public static boolean isTaxiSystemRunning = true;
    public static void main( String[] args ) {
        initializeAllServices();

        while( isTaxiSystemRunning ){
            int request = UserInputOutputService.displayGreetingOptionsAndGetResponse();
            RequestHandler.handleRequest(request);
        }
    }

    public static void shutDownTaxiSystem(){
        isTaxiSystemRunning = false;
        UserInputOutputService.closeScanner();
    }

    private static void initializeAllServices(){
        TaxiManagementSystemRepository.initializeTaxiManagementSystem();
    }
}
