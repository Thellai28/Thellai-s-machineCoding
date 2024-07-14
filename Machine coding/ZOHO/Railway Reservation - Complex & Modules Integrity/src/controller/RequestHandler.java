package controller;

import service.UserInputOutputService;

public class RequestHandler {
    public static void handleRequest( int request ){
        switch (request ){
            case 1 : {
                BookingController.handleBooking();
                break;
            } case 2 : {
                CancelBookingController.handleCancel();
                break;
            } case 3 : {
                TicketSummaryHandler.handleTicketSummary();
                break;

            } case 4 :{
                ChartHandler.printChart();
                break;
            } case 5 :{
                Main.shutDown();
                break;
            }
            default:{
                UserInputOutputService.printMessageAndOneLine("☹️-- Invalid input");
            }
        }
    }
}
