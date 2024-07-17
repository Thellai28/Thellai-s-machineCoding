package model;

import service.UserInputOutputService;

public class Board {
    private char matrix[][];
    private int emptyPlaces;

    public Board() {
        int rows = UserInputOutputService.getNumberOfRows();
        int cols = UserInputOutputService.getNumberOfColumns();

        this.emptyPlaces = rows * cols;

        matrix = new char[rows][cols];
        fillBoard();
    }

    private void fillBoard(){
        for( int row = 0; row < matrix.length; row++ ){
            for( int col = 0; col < matrix[0].length; col++ ){
                matrix[row][col] = '-';
            }
        }
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public int getEmptyPlaces() {
        return emptyPlaces;
    }

    public void setEmptyPlaces( int emptyPlaces ) {
        this.emptyPlaces = emptyPlaces;
    }
}
