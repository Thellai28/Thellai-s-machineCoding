package controller;

import service.UserInputOutputService;

import java.net.UnknownServiceException;

public class RequestHandler {
    public static void handleRequest( int request ){
        switch (request ){
            case 1 : {
                TournamentController.startTournament();
                break;
            } case 2 : {
                Main.shutDown();
                break;
            } default:{
                UserInputOutputService.printMessageAndTwoLine("❌---Invalid input---❌");
                break;
            }
        }
    }
}
