package models;

public class Player {
    private String name;
    private char symbol;
    private String PlayerType;

    public Player( String name, String playerType, char symbol ) {
        this.name = name;
        PlayerType = playerType;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getPlayerType() {
        return PlayerType;
    }

    public void setPlayerType( String playerType ) {
        PlayerType = playerType;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol( char symbol ) {
        this.symbol = symbol;
    }
}
