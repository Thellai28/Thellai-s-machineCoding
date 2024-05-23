package service;

import model.Board;

public class BoardService {
    private static Board board;

    public static void initializeBoardService(){
        board = new Board();
    }

    public static char[][] getBoard(){
        return board.getMatrix();
    }

    public static void decrementEmptySpaces(){
        board.setEmptyPlaces(board.getEmptyPlaces() -1);
    }

    public  static void incrementEmptySpaces(){
        board.setEmptyPlaces(board.getEmptyPlaces() +1);
    }

    public static int getEmptyPlacesInBoard(){
        return board.getEmptyPlaces();
    }


}
