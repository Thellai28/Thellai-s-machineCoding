package controller;

import repository.EmployeeManagementRepository;
import service.UserInputOutputService;

public class Main {
    private static boolean isEmployeeManagementSystemRunning = true;
    public static void main( String[] args ) {
        initializeAllServices();

        while( isEmployeeManagementSystemRunning ){
            int request = UserInputOutputService.displayWelcomeMessage();
            RequestHandler.handleRequest(request);
        }

    }

    public static void shutDownEmployeeManagementSystem(){
        isEmployeeManagementSystemRunning = false;
        UserInputOutputService.closeScanner();
    }

    private static void initializeAllServices(){
         EmployeeManagementRepository.initializeService();
    }
}
