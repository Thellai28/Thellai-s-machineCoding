package controller;

import repository.RailwayReservationRepository;
import service.UserInputOutputService;

public class Main {
    private static boolean isRailwayReservationRunning = true;

    public static void main( String[] args ) {
        initializeAllServices();

        while( isRailwayReservationRunning ){
            int request = UserInputOutputService.displayMenu();
            RequestHandler.handleRequest(request);
        }
    }

    private static void initializeAllServices(){
        RailwayReservationRepository
                .initializeRailwayReservation();
    }

    public static void shutDown(){
        isRailwayReservationRunning = false;
        UserInputOutputService
                .printMessageAndOneLine("⚠️-- shutting down railway reservation system");
    }
}

