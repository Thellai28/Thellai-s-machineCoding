package service;

import model.Game;

public class GameService {
    private static Game game;
    private static char[][] board;

    public static void initializeGameService(){
        game = new Game();
        board = game.getBoard();
    }

    //-------------< Move straight >---------------------------------------------
    public static void moveStraight() {
        UserInputOutputService.displayBoard(game.getBoard());
        int ballRow = game.getBallRow();
        int ballCol = game.getBallColumn();

        resetBallStartingPosition( ballRow, ballCol );
        final int MOVING_DIRECTION = -1;

        while( ballRow >= 0 ){
            if( board[ballRow][ballCol] == game.getBrickSymbol() ){
                UserInputOutputService.displayBrickSmashedMessage(ballRow, ballCol);
                board[ballRow][ballCol] = '.';
                downFall(ballRow, ballCol);
                break;
            }else if(ballRow == 0  ){
                downFall(ballRow, ballCol);
            }
            ballRow += MOVING_DIRECTION;
        }
        UserInputOutputService.displayBoard(game.getBoard());
    }

    private static void downFall( int ballRow, int ballCol ){
        final int MOVING_DIRECTION = 1;

        while( ballRow < game.getFinalSize()){
            if( game.getBoard()[ballRow][ballCol] == game.getBrickSymbol() ){
                game.getBoard()[ballRow][ballCol] = '.';
                UserInputOutputService.displayBrickSmashedMessage(ballRow,ballCol);
            }
            ballRow += MOVING_DIRECTION;
        }

        makeNewBallStartingPosition(ballCol);
    }


    //-----------------------------< Move left >---------------------------------------------------
    public static void moveLeft(){
        int ballRow = game.getBallRow();
        int ballCol = game.getBallColumn();
        final int MOVING_DIRECTION = -1;

        UserInputOutputService.displayBoard(game.getBoard());
        resetBallStartingPosition(ballRow, ballCol);

        while( ballRow >= 0 && ballCol >= 0 ){
            UserInputOutputService.printMessageAndBlankLine("current ball Position " + ballRow + ", " + ballCol);

            if( board[ballRow][ballCol] ==  game.getBrickSymbol() ){
                UserInputOutputService.displayBrickSmashedMessage(ballRow, ballCol);
                board[ballRow][ballCol] = '.';
                downFall(ballRow, ballCol);
                break;
            }else if( ballRow == 0 || ballCol == 0 ){
                UserInputOutputService.printMessageAndBlankLine("HIt a wall at " + ballRow + ", " + ballCol);
                moveHorizontallyRight(ballRow, ballCol);
            }
            ballRow += MOVING_DIRECTION;
            ballCol += MOVING_DIRECTION;
        }

        UserInputOutputService.displayBoard(game.getBoard());
    }

    private static void moveHorizontallyRight( int ballRow, int ballCol ){
        System.out.println();
        int MOVING_DIRECTION = 1;

        while( ballCol < game.getFinalSize() ){
            if( board[ballRow][ballCol] ==  game.getBrickSymbol() ){
                UserInputOutputService.displayBrickSmashedMessage(ballRow, ballCol);
                board[ballRow][ballCol] = '.';
                downFall(ballRow, ballCol);
                break;
            }else if( ballCol == game.getFinalSize() -1 ){
                makeNewBallStartingPosition(ballCol -1);
            }
            ballCol += MOVING_DIRECTION;
        }
    }


    //------------------------------------< Move right >---------------------------------------------

    public static void moveRight(){
        int ballRow = game.getBallRow();
        int ballCol = game.getBallColumn();
        final int ROW_MOVEMENT_DIRECTION = -1;
        final int COL_MOVEMENT_DIRECTION = 1;

        UserInputOutputService.displayBoard(game.getBoard());
        resetBallStartingPosition(ballRow, ballCol);

        while( ballRow >= 0 && ballCol < game.getFinalSize() ){
            UserInputOutputService.printMessageAndBlankLine("current ball Position " + ballRow + ", " + ballCol);

            if( board[ballRow][ballCol] ==  game.getBrickSymbol() ){
                UserInputOutputService.displayBrickSmashedMessage(ballRow, ballCol);
                board[ballRow][ballCol] = '.';
                downFall(ballRow, ballCol);
                break;
            }else if( ballRow ==  0 || ballCol == game.getFinalSize()-1 ){
                UserInputOutputService.printMessageAndBlankLine("HIt a wall at " + ballRow + ", " + ballCol);
                moveHorizontallyLeft(ballRow, ballCol);
            }
            ballRow += ROW_MOVEMENT_DIRECTION;
            ballCol += COL_MOVEMENT_DIRECTION;
        }
        UserInputOutputService.displayBoard(game.getBoard());
    }

    private static void moveHorizontallyLeft( int ballRow, int ballCol){
        final int MOVING_DIRECTION = -1;

        while( ballCol >= 0 ){
            if( board[ballRow][ballCol] ==  game.getBrickSymbol() ){
                UserInputOutputService.displayBrickSmashedMessage(ballRow, ballCol);
                board[ballRow][ballCol] = '.';
                downFall(ballRow, ballCol);
                break;
            }else if( ballCol == 0 ){
                makeNewBallStartingPosition(ballCol +1);
            }
            ballCol += MOVING_DIRECTION;
        }
    }

    //---------------------< Common methods >-------------------------------------------
    private static void resetBallStartingPosition( int ballRow, int ballCol ){
        game.getBoard()[ballRow][ballCol] = game.getWallSymbol();
    }

    private static void makeNewBallStartingPosition( int ballCol ){
        int ballRow = game.getBallRow();
        game.getBoard()[ballRow][ballCol] = game.getBallSymbol();
        game.setBallColumn(ballCol);
        System.out.println("New ball position is " + ballRow +", " + ballCol);
    }
}
