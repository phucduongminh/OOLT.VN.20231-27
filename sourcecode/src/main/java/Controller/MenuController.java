package Controller;

import java.io.IOException;

import Board.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import Player.Player;
import Player.Player1;
import Player.Player2;
import Player.BotPlayer;

public class MenuController {
	private Board board;
	private Player1 player1;
	private Player2 player2;
	private BotPlayer botPlayer;
	private Player playingPlayer;

    @FXML
    private Button button_start;

    @FXML
    private Button button_rule;

	@FXML
    private Button button_bot;

	@FXML
    private Button button_quit;
    
    private Stage primaryStage;
    private Scene scene;
    private Parent root;
    
    public MenuController(Board board, Player1 player1, Player2 player2, Player playingPlayer) {
		this.player1 = player1;
		this.player2 = player2;
		this.board = board;
		this.playingPlayer = playingPlayer;
	}
	@FXML
	void switch_Start(ActionEvent event)throws IOException{  	   	  
		try {
			final String FILE_PATH = "../XML/Game.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FILE_PATH));
			player1 = new Player1();
			player2 = new Player2(player2.getName());
			playingPlayer = player1;
			GameController gameController = new GameController(new Board(), player1, player2, playingPlayer);
			fxmlLoader.setController(gameController);
			Parent root;
			root = fxmlLoader.load();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();   		
    		stage.setScene(new Scene(root));
    		stage.setTitle(null);
    		stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
	@FXML
	void switch_Bot(ActionEvent event)throws IOException{
		try {
			final String FILE_PATH = "../XML/BotGame.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FILE_PATH));
			player1 = new Player1();
			botPlayer = new BotPlayer("Bot", 4);
			board = new Board(player1,botPlayer);
			playingPlayer = player1;
			BotController gameController = new BotController(board, player1, botPlayer, playingPlayer);
			fxmlLoader.setController(gameController);
			Parent root;
			root = fxmlLoader.load();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();   		
    		stage.setScene(new Scene(root));
    		stage.setTitle(null);
    		stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
	}
	@FXML
	void switch_Rule(ActionEvent event)throws IOException{
		try {
			final String FILE_PATH = "../XML/Rule.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FILE_PATH));
			player1 = new Player1();
			player2 = new Player2();
			playingPlayer = player1;
			RuleController ruleController = new RuleController(board, player1, player2, playingPlayer);
			fxmlLoader.setController(ruleController);
			Parent root;
			root = fxmlLoader.load();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		
    		stage.setScene(new Scene(root));
    		stage.setTitle(null);
    		stage.show();
    		
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
	void quit_menu(ActionEvent event){

		System.exit(0);

  }
}
