package controller;

public class RequestHandler {
    public static void handleRequest( int request ){
        switch (request){
            case 1 :{
                EventManagementController.manageEvents();
                break;
            } case 2 : {
                Main.shutDown();
                break;
            }
        }
    }
}
