package services;

import models.Move;
import models.Player;

public class BotPlayerService {

    public static Move botMove() {
        return findBestMove();
    }

    public static Move findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (BoardService.board[row][col] == ' ') {
                    BoardService.board[row][col] = BoardService.currentPlayerSymbol;
                    int score = minimax(BoardService.board, 0, false);
                    BoardService.board[row][col] = ' ';

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new Move(row, col, BoardService.currentPlayerSymbol);
                    }
                }
            }
        }
        return bestMove;
    }

    public static int minimax(char[][] board, int depth, boolean isMaximizing) {
        if (BoardService.checkWinner()) {
            return isMaximizing ? -10 + depth : 10 - depth;
        }
        if (BoardService.isGameDraw()) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == ' ') {
                        board[row][col] = 'O';
                        int score = minimax(board, depth + 1, false);
                        board[row][col] = ' ';
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == ' ') {
                        board[row][col] = 'X';
                        int score = minimax(board, depth + 1, true);
                        board[row][col] = ' ';
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
            return bestScore;
        }
    }
}
