package Console;
import java.util.Scanner;

import Board.Board;
import Gem.*;
import Player.Player;
import Player.Player1;
import Player.Player2;

public class Console {
	static Player playingPlayer;
	static Board board;
	static Scanner keyboard = new Scanner(System.in);

	public static void aTurn(Board board, Player playingPlayer) { // thực hiện lượt chơi bao gồm: chọn ô, chọn hướng, chơi
		if (playingPlayer instanceof Player1) {
		   playingPlayer = (Player1) playingPlayer;
	   } else if (playingPlayer instanceof Player2) {
		   playingPlayer = (Player2) playingPlayer;
	   }
	  
		// Choose square
	   System.out.println("Please choose a square in " + playingPlayer.getRange());
	   int chosenSquare = keyboard.nextInt();
	   while (playingPlayer.getRange().contains(chosenSquare) == false || board.getShape(chosenSquare).getPoint() == 0) {
		   System.out.println("You cannot choose this square!");
		   System.out.println("Please choose another square in " + playingPlayer.getRange());
		   chosenSquare = keyboard.nextInt();
	   }
	   
	   // choose direction
	   System.out.println("Please choose direction:");
	   System.out.println("1: counter clockwise");
	   System.out.println("-1: clockwise");
	   int chosenDirection = keyboard.nextInt();
	   while (chosenDirection != 1 && chosenDirection != -1) {
		   System.out.println("This number is not available!");
		   System.out.println("Please choose direction:");
		   System.out.println("1: counter clockwise");
		   System.out.println("-1: clockwise");
		   chosenDirection = keyboard.nextInt();
	   }
	   
	   board.turn( playingPlayer, chosenSquare, chosenDirection);
	   board.print();
	   
   }

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
		System.out.println(playingPlayer.getName() + " start turn");
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

	public static void refillGems(Board board, Player playingPlayer) {
		SmallGem gem = new SmallGem();
		// downcast class
		if (playingPlayer instanceof Player1) {
			playingPlayer = (Player1) playingPlayer;
		} else if (playingPlayer instanceof Player2) {
			playingPlayer = (Player2) playingPlayer;
		}
		
		// if all squares which playing player can choose are empty ==> refill 1 gem to all squares
		for (int i:playingPlayer.getRange()) {
			board.getShape(i).addGem(gem);
		}
		playingPlayer.minusPoint(5);
	}

	public static void main(String[] args) {
		Player1 player1 = new Player1();
		Player2 player2 = new Player2();
		Board board = new Board(player1,player2);

		playingPlayer = player1;
		board.print();
	
		// Start game
		while (stopGame(board) == false) {
			if (checkNeedRefill(board, playingPlayer)) {
				System.out.println("begin");
				board.print();
				System.out.println("end");
				refillGems(board, playingPlayer);
				
			}
			aTurn(board, playingPlayer);
			
			board.print();
			System.out.println("Player " + player1.getName() + " has " + player1.getTotalPoint() + " gems");
			System.out.println("Player " + player2.getName() + " has " + player2.getTotalPoint() + " gems");
			System.out.println("=======================================================");
			takeTurn(player1, player2);
			
		}
		
		keyboard.close();
		gameResult(player1, player2);
		
	}
}