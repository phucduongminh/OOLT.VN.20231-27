package Console;
import java.util.List;

import Board.Board;
import Gem.*;
import Shape.*;
import Player.Player;
import Player.Player1;
import Player.Player2;

public class Console {
	static Player playingPlayer;
	static Board board;

	public static boolean stopTurn(Board board,Player playingPlayer, int curPosition , int direction) { // done
		int nextPosition = curPosition + direction;
		if (playingPlayer.getPointInHand()==0 && board.getShape(nextPosition).getPoint() == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean stopGame(Board board) { //2 semicircle ==0
		if (board.getShape(0).getPoint() == 0 && board.getShape(6).getPoint() == 0) {
			return true;
		}
		return false;
	}

	public static void takeTurn(Player1 player1, Player2 player2) {
		if (playingPlayer == player1 ) {
			playingPlayer = player2;
		} else if (playingPlayer == (player2)) {
			playingPlayer = player1;
		}
		System.out.println("Player " + playingPlayer.getName() + " start turn");
	}

	public static void gameResult(Player1 player1, Player2 player2) {
		if (player1.getTotalPoint() > player2.getTotalPoint()) {
			System.out.println(player1.getName() + " wins");
		} else if (player1.getTotalPoint() < player2.getTotalPoint()) {
			System.out.println(player2.getName() + " wins");
		} else {
			System.out.println("Draw");
		}
	}

	public static boolean checkNeedRefill(Board board, Player playingPlayer) { 
		// if all squares which playing player can choose are empty => true
		
		int sum = 0;
		
		if (playingPlayer instanceof Player1) {
			playingPlayer = (Player1) playingPlayer;
		} else if (playingPlayer instanceof Player2) {
			playingPlayer = (Player2) playingPlayer;
		}
		
		for (int i:playingPlayer.getRange()) {
			sum += board.getShape(i).getPoint();
		}
		if (sum != 0) {
			return false;
		}
		System.out.println(playingPlayer.getName() + " need to refill your square!");
		return true;
	}

	public static void main(String[] args) {
		Board board = new Board();
		Player1 player = new Player1();

		board.turn(player, 4, -1);
		board.print();
		board.turn(player, 8, -1);
		board.print();
		board.turn(player, 2, 1);
		board.print();
		board.turn(player, 7, 1);
		board.print();
		board.turn(player, 2, -1);
		board.print();
		board.turn(player, 11, -1);
		board.print();
		board.turn(player, 5, 1);
		board.print();
		System.out.println(player.getTotalPoint());
	}
}