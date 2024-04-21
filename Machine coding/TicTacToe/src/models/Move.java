package models;

public class Move {
    private int row;
    private int col;
    private char PlayerSymbol;

    public Move(  int row, int col, char playerSymbol ) {
        this.col = col;
        PlayerSymbol = playerSymbol;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol( int col ) {
        this.col = col;
    }

    public char getPlayerSymbol() {
        return PlayerSymbol;
    }

    public void setPlayerSymbol( char playerSymbol ) {
        PlayerSymbol = playerSymbol;
    }

    public int getRow() {
        return row;
    }

    public void setRow( int row ) {
        this.row = row;
    }
}
