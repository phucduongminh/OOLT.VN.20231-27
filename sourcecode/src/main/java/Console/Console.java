package Console;
import java.util.List;

import Board.Board;
import Gem.*;
import Shape.*;

public class Console {

	public static void main(String[] args) {
        Console console = new Console();
        console.startGame();
	}
    public void startGame(){
        Board board = new Board();

		board.print();
    }
	public boolean stopGame(List<Shape> board) {
		if (board.get(0).getPoint() == 0 && board.get(6).getPoint() == 0) {
			return true;
		}
		return false;
	}
	
}