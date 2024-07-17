package controller;

import service.UserInputOutputService;

public class RequestHandler {

    public static void handleRequest( int request ){
        switch ( request ){
            case 1 : {
                BookingController.bookSeat();
                break;

            } case 2 : {
                SeatAvailabilityController.displaySeats();
                break;

            } case 3 : {
                CancellationController.cancelTicket();
                break;

            } case 4 : {
                ChartController.createChart();
                break;

            } case 5 : {
                Main.shutDown();
                break;

            } default:{
                UserInputOutputService
                        .printMessageAndSingleLine("‼️---Please choose a valid option---");
            }
        }
    }
}
