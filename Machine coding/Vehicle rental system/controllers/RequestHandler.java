package controllers;
import static services.UserIoService.closeScanner;

public class RequestHandler {

    public static void handleRequest( int request ){
        switch (request){
            case 1:{ // Display all methods :
                RentalSystemController.displayAllAvailableVehicles();
                break;
            } case 2 : {
                RentalSystemController.bookVehicle();
                break;
            } case 3 : {
                RentalSystemController.dropVehicle();
                break;
            } case 4 : {
                RentalSystemController.cancelVehicle();
                break;
            } case 5:{
                System.out.println("‼️Closing Application, have a nice day & please visit again ☺️");
                closeScanner();
                Main.shutDownRentalService();
                break;
            } default:{
                System.out.println("😟 Invalid input, please choose again");
                break;
            }
        }
    }

}
