package model;

public class Player {
    private static int idGenerator = 1;

    private String playerName;
    private float score;

    public Player() {
        this.playerName = "Player-" + idGenerator++;
        this.score = 0f;
    }

    @Override
    public String toString() {
        return String.format("%-9s| %-6.2f",playerName, score );
    }


    // --------------< Getter & Setter >-----------------------
    public String getPlayerName() {
        return playerName;
    }

    public float getScore() {
        return score;
    }

    public void setScore( float score ) {
        this.score = score;
    }
}
