package controllers;

import services.ParkingLotService;

import static services.DisplayInConsoleService.*;

public class ParkingLotController {
    public static boolean isParkingLotActive = true;

    public static void main( String[] args ) {
        ParkingLotService parkingLot = getValuesAndInitializeParkingLot();

        while( isParkingLotActive ){
            parkingLot.handleIncomingRequest();
        }
    }
}
