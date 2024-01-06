package View;

import java.io.IOException;

import Board.Board;
import Controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Player.Player;
import Player.Player1;
import Player.Player2;

public class Game extends Application {
	private static Board board;
	private static Player1 player1;
	private static Player2 player2;
	private static Player playingPlayer;
	

	@Override
	public void start(Stage primaryStage) {
		try {
			final String FILE_PATH = "../XML/Game.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FILE_PATH));
			GameController gameController = new GameController(board, player1, player2, playingPlayer);
			fxmlLoader.setController(gameController);
			Parent root;
			root = fxmlLoader.load();
			primaryStage.setTitle(null);
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		board  = new Board();
		player1 = new Player1();
		player2 = new Player2("player2");
		playingPlayer = player1;
		
		launch(args);
	}
}
