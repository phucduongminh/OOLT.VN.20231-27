package Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Board.Board;
import Console.Console;
import Move.Move;

public class BotPlayer extends Player2 {
    private int depth;
    private List<Integer> range = Arrays.asList(new Integer[] { 7, 8, 9, 10, 11 });
    public BotPlayer(String name, int depth) {
        super(name);
        this.depth = depth;
    }
    public List<Move> getValidMoves(Board board) {
        List<Move> validMoves = new ArrayList<>();

        for (int i : this.getRange()){
            if (board.getShape(i).getPoint() > 0) {
                validMoves.add(new Move(board, i, -1));
                validMoves.add(new Move(board, i, 1));

            }
        }
        

        return validMoves;
    }
    public static List<Move> getValidMovesPlayer(Board board ) {
        List<Move> validMoves = new ArrayList<>();

        for (int i =1 ; i < 6 ;i ++){
            if (board.getShape(i).getPoint() > 0) {
                validMoves.add(new Move(board, i, -1));
                validMoves.add(new Move(board, i, 1));

            }
        }
        

        return validMoves;
    }

    public Move chooseSquare(Board board) {
        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;
        if (board.getPlayer1() == null) System.out.println("null");


        for (Move move : getValidMoves(board)) {
            Board tempBoard = new Board();
            if (tempBoard.getPlayer1() == null) System.out.println("null");
            if (board.getPlayer1() == null) System.out.println("null");
            System.out.println(tempBoard.getPlayer1().getTotalPoint());

            tempBoard.copyBoard(board);
            System.out.println(move.position + " " + move.direction);
            tempBoard.turn(this,move.position,move.direction);
            tempBoard.print();
            board.print();            
            
            int score = minimax(tempBoard, this.depth, false);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
                System.out.println(score);
            }
        }

        return bestMove;
    }

    private int minimax(Board board, int depth, boolean maximizingPlayer) {
        if (depth == 0 || Console.stopGame(board)) {
            return evaluate(board);
        }

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Move move : getValidMoves(board)) {
                Board tempBoard = new Board();
                tempBoard.copyBoard(board);
                tempBoard.turn(this,move.position,move.direction);
                int eval = minimax(tempBoard, depth - 1, false);
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Move move : getValidMovesPlayer(board)) {
                Board tempBoard = new Board();
                tempBoard.copyBoard(board);
                tempBoard.turn(board.getPlayer1(),move.position,move.direction);
                int eval = minimax(tempBoard, depth - 1, true);
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }
    }

    private int evaluate(Board board) {
        // Calculate the score of the AI player
        int aiScore = this.getTotalPoint() - board.getPlayer1().getTotalPoint();

        // The evaluation is the AI's score
        return aiScore;
    }
    public List<Integer> getRange() {
        return range;
    }
}