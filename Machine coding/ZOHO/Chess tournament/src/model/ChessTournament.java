package model;

import service.UserInputOutputService;

import java.util.ArrayList;
import java.util.List;

public class ChessTournament {
    private List<Player> playerList;

    public ChessTournament() {
        playerList = new ArrayList<>();
        int playersCount = UserInputOutputService.getPlayersCount();

        for( int i = 0; i < playersCount; i++ ){
            playerList.add( new Player() );
        }
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
