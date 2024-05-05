package controller;

import service.UserIoService;
import service.VendingMachineService;

public class Main {
    private static boolean isVendingMachineActive = true;

    public static void main( String[] args ) {
        initiateAllServices();

        while( isVendingMachineActive){
            int request = UserIoService.showOptionsAndGetRequest();
            RequestHandler.handleRequest(request);
        }
    }

    private static void initiateAllServices(){
        VendingMachineService.initializeVendingMachineService();
    }

    public static void shutDownVendingMachine(){
        System.out.println("Shutting down vending machine...");
        isVendingMachineActive = false;
        UserIoService.closeScanner();
    }
}
