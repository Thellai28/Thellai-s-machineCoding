package controller;

import repository.RailwayReservationSystemRepository;
import service.UserInputOutputService;

public class Main {
    public static boolean isRailwayReservationSystemRunning = true;

    public static void main( String[] args ) {
        initializeAllServices();

        while( isRailwayReservationSystemRunning ){
            int request = UserInputOutputService.displayHomePageAndGetResponse();
            RequestHandler.handleRequest(request);
        }
    }

    public static void shutDownRailwayReservationSystem(){
        isRailwayReservationSystemRunning = false;
        UserInputOutputService.closeScanner();
    }

    private static void initializeAllServices(){
        RailwayReservationSystemRepository.initializeRailwayReservationSystem();
    }
}
