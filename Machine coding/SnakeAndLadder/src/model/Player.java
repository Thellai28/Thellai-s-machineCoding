package model;

public class Player {
    private static final char basePlayerSymbol = 'A';

    private String name;
    private char symbol;

    public Player( String name, int playerPosition) {

        this.name = name;
        this.symbol = (char)(basePlayerSymbol + playerPosition);
    }

    //-----------------------< GETTER & SETTER >----------------------------------------

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol( char symbol ) {
        this.symbol = symbol;
    }
}
