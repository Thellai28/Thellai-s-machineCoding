package controller;

public class RequestHandler {
    public static void handleRequest(int request){
        switch (request){
            case 1:{
                BookMyShowController.handleBookingRequest();
                break;
            } case 2 : {
                BookMyShowController.handleAddMovieRequest();
                break;
            } case 3 : {
                BookMyShowController.handleCancelMovieRequest();
                break;
            } case 4 : {
                Main.shutDownBookMyShow();
                break;
            } default:{
                System.out.println("☹️☹️ Invalid option");
                break;
            }
        }
    }
}
