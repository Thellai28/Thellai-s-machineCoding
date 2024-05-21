package controller;

import service.UserInputOutputService;

public class RequestHandler {
    public static void handleRequest( int request ){
        switch (request){
            case 1:{
                TollAndShortestPathController.manageJourney();
                break;
            } case 2 :{
                PrintTollsController.printAllTollRecords();
                break;
            } case 3:{
                VehicleTicketsController.printAllTickets();
                break;
            } case 4 : {
                UserInputOutputService.printMessageAndOneLine("⚠️shutting down toll payment system...⚠️");
                Main.shutDownTollPaymentService();
            }
        }
    }
}
