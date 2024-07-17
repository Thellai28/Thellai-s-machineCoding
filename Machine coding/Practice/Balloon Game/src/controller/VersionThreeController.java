package controller;

import service.BoardService;
import service.UserInputOutputService;

public class VersionThreeController {
    private static boolean isGameRunning = true;

    public static void runGame(){
        while (isGameRunning ){
            playTheGame();
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

    private static void shutDownGame(){
        UserInputOutputService.printMessageAndOneLine("⚠️---Shutting Down game...⚠️");
        isGameRunning = false;
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

    private static void dropBalloon( int balloonCol ){
        char mat[][] = BoardService.getBoard();
        int startingRow = mat.length -1;

        for( int row = startingRow; row >= 0 ; row-- ){

            if( mat[row][balloonCol] != '-'){ // The column is already filled :
                boolean isFilled = fillFromLeft(row, mat[0].length, mat);
                if( isFilled ){
                    BoardService.decrementEmptySpaces();
                    UserInputOutputService.printMessageAndOneLine("🎊---Placed balloon at ROW: "
                            + row + " ---🎊");
                    break;
                }

            } else if( mat[row][balloonCol] == '-' ){ // The column is empty :
                char balloonColor = UserInputOutputService.getBalloonColor();
                mat[row][balloonCol] = balloonColor;

                BoardService.decrementEmptySpaces(); // since we have placed one balloon, the empty place count will reduce :
                UserInputOutputService.printMessageAndOneLine("🎊---Placed balloon at COL: "
                        + balloonCol + " ---🎊");
                break;
            }

        }
    }

    private static boolean fillFromLeft( int row, int colLimit, char mat[][] ){

        for( int col = 0; col < colLimit; col++ ){
            if( mat[row][col] == '-' ){
                char balloonColor = UserInputOutputService.getBalloonColor();
                mat[row][col] = balloonColor;
                return true;
            }
        }
        return false;
    }
}
