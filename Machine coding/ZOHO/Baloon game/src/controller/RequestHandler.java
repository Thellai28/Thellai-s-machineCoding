package controller;

import service.UserInputOutputService;

public class RequestHandler {
    public static void handleRequest( int request ){
        switch (request){
            case 1 : {
                VersionOneController.runGame();
                break;
            } case 2: {
                VersionTwoController.runGame();
                break;
            } case 3 : {
                VersionThreeController.runGame();
                break;
            } case 4 : {
                VersionFourController.runGame();
                break;
            } case 5 : {
                VersionFiveController.runGame();
                break;
            } case 6 : {
                UserInputOutputService.printMessageAndOneLine("⚠️...Shutting down Main game...⚠️");
                Main.shutDownBalloonGame();
                break;
            } default:{
                UserInputOutputService.printMessageAndOneLine("‼️---Invalid input---‼️");
            }
        }
    }
}
