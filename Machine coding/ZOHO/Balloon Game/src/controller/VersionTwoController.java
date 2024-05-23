package controller;

import service.BoardService;
import service.UserInputOutputService;

public class VersionTwoController {
    private static boolean isGameRunning = true;

    public static void runGame(){
        while (isGameRunning ){
            playTheGame();

        }

    }


    private static void dropBalloon( int balloonCol ){
        char mat[][] = BoardService.getBoard();
        int startingRow = mat.length -1;


        for( int row = startingRow; row >= 0 ; row-- ){
            if( mat[row][balloonCol] == '-' ){
                char balloonColor = UserInputOutputService.getBalloonColor();
                mat[row][balloonCol] = balloonColor;

                BoardService.decrementEmptySpaces(); // since we have placed one balloon, the empty place count will reduce :
                UserInputOutputService.printMessageAndOneLine("🎊---Placed balloon at col: " + balloonCol + " ---🎊");
                break;
            }
        }

    }

    private static void playTheGame(){
        if( BoardService.getEmptyPlacesInBoard() > 0 ){

            int balloonCol = UserInputOutputService.getColumnToDropBalloons();
            dropBalloon(balloonCol);
            UserInputOutputService.printBoard(BoardService.getBoard());
            boolean isFull = isAnyColumnFull();

            if( isFull ){
                UserInputOutputService.printMessageAndOneLine("‼️---A column is full, " +
                        "so game will be terminated---‼️");
                shutDownGame();
            }else {
                int isGameContinued = UserInputOutputService.doPlayerWishToContinueGame();
                isGameRunning = ( isGameContinued == 2 ) ? false : isGameRunning;
            }

        }else{
            UserInputOutputService.printMessageAndOneLine("❌---Sorry,the board is full, " +
                    "you can't play this game any more---❌");
            shutDownGame();
        }
    }

    private static boolean isAnyColumnFull(){
        char mat[][] = BoardService.getBoard();
        int rowLimit = mat.length;
        int colLimit = mat[0].length;

        for( int col = 0; col < colLimit; col++ ){
            int filledCells = 0;

            for( int row = 0; row < rowLimit; row++ ){
                if( mat[row][col] != '-' ){
                    filledCells++;
                }
            }
            if( filledCells == rowLimit ){ // rowLim = height of the column :
                return true;
            }
        }
        return false;
    }

    private static void shutDownGame(){
        UserInputOutputService.printMessageAndOneLine("⚠️---Shutting Down game...⚠️");
        isGameRunning = false;
    }


}
