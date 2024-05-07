package controller;

import repository.BookMyShowRepository;
import service.UserIoService;

public class Main {
    private static boolean isBookMyShowRunning = true;

    public static void main( String[] args ) {
        initializeAllServices();

        while( isBookMyShowRunning ){
            int request = UserIoService.displayOptionsAndGetChoice();
            RequestHandler.handleRequest(request);
        }
    }

    public static void shutDownBookMyShow(){
        System.out.println("Shutting down BookMyShow application...");
        isBookMyShowRunning = false;
        UserIoService.closeScanner();
    }

    private static void initializeAllServices(){
        BookMyShowRepository.initializeBookMyShowService();
    }
}
