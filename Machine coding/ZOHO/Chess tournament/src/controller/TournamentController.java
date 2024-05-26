package controller;

import model.Player;
import repository.ChessTournamentRepository;
import service.UserInputOutputService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TournamentController {
    public static void startTournament(){
        playGame();
        Collections.sort(
                ChessTournamentRepository.getPlayerList(),
                (a, b)-> Float.compare(b.getScore(), a.getScore())
        );
        UserInputOutputService.printAllPlayers(ChessTournamentRepository.getPlayerList());
    }

    private static int decideWinner(){
        Random random = new Random();
        // Generates a number between 0 (inclusive) and 3 (exclusive), then adds 1 to shift the range to 1-3
        return random.nextInt(3) + 1;

        // return (int) (Math.random() * 3) + 1;
    }

    private static void playGame(){
        List<Player> playerList = ChessTournamentRepository.getPlayerList();

        for( int i = 0; i < playerList.size(); i++ ){
            for( int j = i+1; j < playerList.size(); j++ ){

                Player p1 = playerList.get(i);
                Player p2 = playerList.get(j);

                float p1_Score = 0f;
                float p2_Score = 0f;

                String p1_GameStatus ="";
                String p2_GameStatus = "";

                int result = decideWinner();
                if( result == 1 ){
                    p1.setScore( p1.getScore() + 1f);
                    p1_Score = 1f;
                    p2_Score = 0f;
                    p1_GameStatus = "WON";
                    p2_GameStatus = "LOST";

                }else if( result == 2 ){
                    p2.setScore( p2.getScore() + 1f);
                    p1_Score = 0f;
                    p2_Score = 1f;
                    p2_GameStatus = "WON";
                    p1_GameStatus = "LOST";

                }else { // match draw :
                    p1.setScore( p1.getScore() + 0.5f);
                    p2.setScore( p1.getScore() + 0.5f);
                    p1_Score = 0.5f;
                    p2_Score = 0.5f;
                    p2_GameStatus = "DRAW";
                    p1_GameStatus = "DRAW";
                }

                UserInputOutputService.printCurrentRoundWinner(p1,p1_Score, p1_GameStatus,
                        p2, p2_Score, p2_GameStatus );
            }
        }
    }

}
