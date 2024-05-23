package controller;

import service.BoardService;
import service.UserInputOutputService;


public class VersionOneController {
    private static boolean isGameRunning = true;

    public static void runGame(){
        while( isGameRunning ){
            playTheGame();
        }
    }

    private static void dropBalloon( int balloonCol ){
        char mat[][] = BoardService.getBoard();
        int startingRow = mat.length -1;

        boolean isBalloonPlaced = false;

        for( int row = startingRow; row >= 0 ; row-- ){
            if( mat[row][balloonCol] == '-' ){
                char balloonColor = UserInputOutputService.getBalloonColor();
                mat[row][balloonCol] = balloonColor;

                BoardService.decrementEmptySpaces();
                isBalloonPlaced = true;

                UserInputOutputService.printMessageAndOneLine("🎊---Placed balloon at col: " + balloonCol + " ---🎊");
                break;
            }
        }

        if( !isBalloonPlaced ){
            UserInputOutputService.printMessageAndOneLine("❌---Sorry the chosen column is filled, " +
                    "please choose another column---❌");
        }
    }

    private static void playTheGame(){

        if( BoardService.getEmptyPlacesInBoard() > 0 ){

            int balloonCol = UserInputOutputService.getColumnToDropBalloons();
            dropBalloon(balloonCol);
            UserInputOutputService.printBoard(BoardService.getBoard());

            int isGameContinued = UserInputOutputService.doPlayerWishToContinueGame();
            isGameRunning = ( isGameContinued == 2 ) ? false : isGameRunning;

        }else{
            UserInputOutputService.printMessageAndOneLine("❌---Sorry,the board is full, " +
                    "you can't play this game any more---❌");
            shutDownGame();
        }
    }

    private static void shutDownGame(){
        UserInputOutputService.printMessageAndOneLine("⚠️---Shutting Down game...⚠️");
        isGameRunning = false;
    }
}
