package model;

import service.UserIoService;

import javax.print.MultiDocPrintService;
import java.util.HashMap;
import java.util.Map;

public class SnakeAndLadderGame {
    private Map<Character, Player> playerMap;
    private Map<Integer, Integer> snakeMap;
    private Map<Integer, Integer> ladderMap;
    private Map<Character, Integer>playerPositionMap;
    private char board[];
    private final int BOARD_SIZE = 10;

    //-------------------------< Constructor & methods >--------------------------------------------------

    public SnakeAndLadderGame() {
        this.board = new char[ BOARD_SIZE * BOARD_SIZE + 1 ];
        this.playerMap = new HashMap<>();
        this.snakeMap = new HashMap<>();
        this.ladderMap = new HashMap<>();
        this.playerPositionMap = new HashMap<>();

        int playerCount = UserIoService.getPlayerCount();
        createPlayers(playerCount);
        UserIoService.printHorizontalLines();

        int snakesCount = UserIoService.getSnakesCount();
        createSnakes(snakesCount);
        UserIoService.printHorizontalLines();

        int ladderCount = UserIoService.getLadderCount();
        createLadders(ladderCount);
        UserIoService.printHorizontalLines();

        fillBoard();
    }

    private void createPlayers( int playerCount){
        for( int i = 0; i < playerCount; i++ ){
            String name = UserIoService.getPlayerName();
            Player player = new Player(name, i);
            this.playerMap.put(player.getSymbol(), player);
            System.out.println();
        }
    }

    private void createSnakes(int snakesCount){
        for( int i = 0; i < snakesCount; i++ ){
            int snakesHead = UserIoService.getSnakeHeadPosition();
            int snakesTail = UserIoService.getSnakeTailPosition();
            this.snakeMap.put(snakesHead, snakesTail);
            System.out.println();
        }
    }

    private void createLadders( int ladderCount ){
        for( int i = 0; i < ladderCount; i++ ){
            int ladderStartPosition = UserIoService.getLadderStartingPosition();
            int ladderEndingPosition = UserIoService.getLadderEndingPosition();
            this.ladderMap.put(ladderStartPosition, ladderEndingPosition);
            System.out.println();
        }
    }

    private void fillBoard(){
        for( int i = 0; i < BOARD_SIZE *BOARD_SIZE; i++ ){
            board[i] = '.';
        }
    }
    //------------------------------< GETTERS & SETTERS >-----------------------------------------------

    public char[] getBoard() {
        return board;
    }

    public void setBoard( char[] board ) {
        this.board = board;
    }

    public int getBOARD_SIZE() {
        return BOARD_SIZE;
    }

    public Map<Integer, Integer> getLadderMap() {
        return ladderMap;
    }

    public void setLadderMap( Map<Integer, Integer> ladderMap ) {
        this.ladderMap = ladderMap;
    }

    public Map<Character, Player> getPlayerMap() {
        return playerMap;
    }

    public void setPlayerMap( Map<Character, Player> playerMap ) {
        this.playerMap = playerMap;
    }

    public Map<Integer, Integer> getSnakeMap() {
        return snakeMap;
    }

    public void setSnakeMap( Map<Integer, Integer> snakeMap ) {
        this.snakeMap = snakeMap;
    }

    public Map<Character, Integer> getPlayerPositionMap() {
        return playerPositionMap;
    }

    public void setPlayerPositionMap( Map<Character, Integer> playerPositionMap ) {
        this.playerPositionMap = playerPositionMap;
    }
}
