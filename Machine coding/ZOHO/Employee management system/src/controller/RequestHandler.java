package controller;

public class RequestHandler {
    public static void handleRequest( int request ){
        switch ( request ){
            case 1 : {
                DisplayEmployeeCompiler.printAllEmployeeAsTable();
                break;
            } case 2 : {
                SearchController.handleEmployeeSearchRequest();
                break;
            }
        }
    }
}
