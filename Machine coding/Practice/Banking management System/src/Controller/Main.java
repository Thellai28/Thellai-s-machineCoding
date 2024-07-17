package Controller;

import repository.BankingSystemRepository;
import service.UserInputOutputService;

public class Main {
    private static  boolean isBankingManagementSystemRunning = true;

    public static void main( String[] args ) {
        initializeAllServices();

        while( isBankingManagementSystemRunning ){
            if(AuthenticationController.logged_inCustomer == null ){
                AuthenticationController.login();
            }
            int request = UserInputOutputService.displayHomePageAndGetResponse();
            RequestHandler.handleRequest(request);
        }
    }

    private static void initializeAllServices(){
        BankingSystemRepository.initializeBankingSystemRepository();
    }

    public static void shutDownBankingSystem(){
        isBankingManagementSystemRunning = false;
        UserInputOutputService.closeScanner();
        AuthenticationController.logOut();
    }

}
