package controller;

import model.Game;
import service.GameService;

import java.awt.font.GlyphMetrics;

public class RequestHandler {

    public static void handleRequest( int request ){
        switch (request){
            case 1 : {
                GameService.moveStraight();
                break;
            } case 2 : {
                GameService.moveLeft();
                break;
            } case 3 : {
                GameService.moveRight();
                break;
            } case 4 : {
                System.out.println("Shutting down game...");
                Main.shutDownGame();
                break;
            }
        }
    }
}
