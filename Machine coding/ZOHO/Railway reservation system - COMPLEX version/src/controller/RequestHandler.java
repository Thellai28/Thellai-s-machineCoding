package controller;

public class RequestHandler {

    public static void handleRequest(int request){
        switch (request){
            case 1 : {
                BookingController.handleBooking();
                break;
            } case 2 : {
                CancelBookingController.handleBookingCancel();
                break;
            } case 3 : {
                TicketPrintingController.handleTicketPrintingRequest();
                break;
            } case 4 : {
                AvailableSeatsController.handleAvailableSeatPrinting();
                break;
            } case 5 : {
                System.out.println("Shutting down Railway reservation service...");
                Main.shutDownRailwayReservationSystem();
                break;
            } default:{
                System.out.println("Invalid Option, choose a valid option");
                break;
            }
        }
    }
}
