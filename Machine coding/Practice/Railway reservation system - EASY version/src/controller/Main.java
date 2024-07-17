package controller;

import repository.RailwayReservationRepository;
import service.UserInputOutputService;

public class Main {
    private static boolean isRailwayReservationRunning = true;

    public static void main( String[] args ) {
        initializeAllServices();

        while( isRailwayReservationRunning ){
            int request = UserInputOutputService.displayMainMenu();
            RequestHandler.handleRequest(request);
        }
    }

    private static  void initializeAllServices(){
        RailwayReservationRepository.initiateRailwayReservation();
        UserInputOutputService.initializeScanner();
    }

    public static void shutDown(){
        UserInputOutputService.closeScanner();
        isRailwayReservationRunning = false;
        UserInputOutputService
                .printMessageAndSingleLine("⚠️---Shutting down railway reservation system---");
    }
}
