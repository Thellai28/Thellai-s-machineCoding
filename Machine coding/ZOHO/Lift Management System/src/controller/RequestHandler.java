package controller;

import service.UserInputOutputService;

public class RequestHandler {
    public static void handleRequest( int request ){
        switch (request ){
            case 1 : {
                AssignLiftController.assignLift();
                break;
            } case 2 : {
                Main.shutDown();
                break;
            } default:{
                UserInputOutputService.printMessageAndBlankLine("❌---Invalid input---❌");
            }
        }
    }
}
