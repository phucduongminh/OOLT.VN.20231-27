package Move;
import java.util.ArrayList;
import java.util.List;

import Board.Board;
import Player.Player;

public class Move {
    private Board board;
    private Player playingPlayer;
    public int position;
    public int direction;
    public int eval=0;
	public Move(Board board, int position, int direction) {
        this.board = board;
        
        this.position = position;
        this.direction = direction;

    }
    public Move(Board board, Player playingPlayer, int position, int direction) {
        this.board = board;
        this.playingPlayer = playingPlayer;
        this.position = position;
        this.direction = direction;

    }
    
}