package Console;
import java.util.List;

import Board.Board;
import Gem.*;
import Shape.*;
import Player.Player;

public class Console {
	//position is 0-11, but except 0 and 6 is SemiCircle
	//direction is -1 for anticlockwise, 1 is for folling clockwise
	public static void pickAndDrop(Board board,Player playingPlayer, int position, int direction) {
		SmallGem gem = new SmallGem();
		playingPlayer.setPointInHand(board.getShape(position).getPoint());
		board.getShape(position).removeGem();
		int curPosition = position;
		while (playingPlayer.getPointInHand() > 0) {
			curPosition += direction;
			board.getShape(curPosition).addGem(gem);
			playingPlayer.setPointInHand(playingPlayer.getPointInHand() - 1);
		}
	}
    public void startGame(){
        Board board = new Board();

		System.out.println(board.getShape(0).getPoint());
    }

	public boolean stopGame(List<Shape> board) {
		if (board.get(0).getPoint() == 0 && board.get(6).getPoint() == 0) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Board board = new Board();
		Player player = new Player("Player1");
		pickAndDrop(board, player,11,-1);
		board.print();
	}
}