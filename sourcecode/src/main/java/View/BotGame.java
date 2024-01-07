package View;

import java.io.IOException;

import Board.Board;
import Controller.BotController;
import Controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Player.BotPlayer;
import Player.Player;
import Player.Player1;
import Player.Player2;

public class BotGame extends Application {
	private static Board board;
	private static Player1 player1;
	private static BotPlayer player2;
	private static Player playingPlayer;
	

	@Override
	public void start(Stage primaryStage) {
		try {
			final String FILE_PATH = "../XML/BotGame.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FILE_PATH));
			BotController gameController = new BotController(board, player1, player2, playingPlayer);
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
		player1 = new Player1();
		player2 = new BotPlayer("Bot", 4);
		board = new Board(player1,player2);
		playingPlayer = player1;
		
		launch(args);
	}
}
