package controller;

public class RequestHandler {
    public static void handleRequest( int request ){
        switch (request){
            case 1: {
                BookingController.handleBooking();
                break;
            } case 2 : {
                TaxiDetailsController.handleTaxiDetailsRequest();
                break;
            } case 3 : {
                System.out.println("Shutting down System...");
                Main.shutDownTaxiSystem();
                break;
            } default: {
                System.out.println("Invalid entry : entry valid input");
                break;
            }
        }
    }
}
