package repository;

import model.ChessTournament;
import model.Player;

import java.util.List;

public class ChessTournamentRepository {
    private static ChessTournament chessTournament;

    public static void initializeChessTournamentRepository(){
        chessTournament = new ChessTournament();
    }

    public static List<Player> getPlayerList(){
        return chessTournament.getPlayerList();
    }
}
