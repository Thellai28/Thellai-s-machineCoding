package model;

import service.UserInputOutputService;

public class Game {
    private char[][] board;
    private final int size;
    private final int brickCount = 2;
    private final int finalSize;
    private final int ballRow;
    private int ballColumn;
    private char wallSymbol = 'W';
    private char ballSymbol = '0';
    private char brickSymbol = '1';
    private final int bricks[][] = {{2, 2}, {2, 3}, {2, 4}, {3, 2}, {3, 3}, {3, 4}};

    public Game() {
        this.size = UserInputOutputService.getBoardSize();
        this.finalSize = size + brickCount;
        board = new char[finalSize][finalSize];
        this.ballRow = finalSize-1;
        this.ballColumn = UserInputOutputService.getBallColValue();

        fillBoard();
        fillBricks();
        setBall();
    }

    private void fillBoard(){
        for( int row = 0; row < finalSize; row++ ){
            for( int col = 0; col < finalSize; col++ ){
                if( row == 0 || row == finalSize-1 || col == 0 || col == finalSize-1 ){
                    board[row][col] = wallSymbol;
                }else{
                    board[row][col] = '.';
                }
            }
        }
    }

    private void fillBricks(){
       for( int brick[] : bricks){
           int row = brick[0];
           int col = brick[1];
           board[row][col] = brickSymbol;
       }
    }

    public void setBall(){
        board[ballRow][ballColumn] = ballSymbol;
    }



    //-----------------<Setters & Getters>----------------------------


    public int getBallColumn() {
        return ballColumn;
    }

    public void setBallColumn( int ballColumn ) {
        this.ballColumn = ballColumn;
    }

    public int getBallRow() {
        return ballRow;
    }

    public char getBallSymbol() {
        return ballSymbol;
    }

    public void setBallSymbol( char ballSymbol ) {
        this.ballSymbol = ballSymbol;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard( char[][] board ) {
        this.board = board;
    }

    public int getBrickCount() {
        return brickCount;
    }

    public char getBrickSymbol() {
        return brickSymbol;
    }

    public void setBrickSymbol( char brickSymbol ) {
        this.brickSymbol = brickSymbol;
    }

    public int getFinalSize() {
        return finalSize;
    }

    public int getSize() {
        return size;
    }

    public char getWallSymbol() {
        return wallSymbol;
    }

    public void setWallSymbol( char wallSymbol ) {
        this.wallSymbol = wallSymbol;
    }
}
