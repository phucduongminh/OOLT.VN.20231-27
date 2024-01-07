package Controller;

import java.io.IOException;

import Board.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Player.Player;
import Player.Player1;
import Player.Player2;

public class EndController {
	private Board board;
	private Player1 player1;
	private Player2 player2;
	private Player playingPlayer;
	
	public EndController(Board board, Player1 player1, Player2 player2, Player playingPlayer) {
		this.player1 = player1;
		this.player2 = player2;
		this.board = board;
		this.playingPlayer = playingPlayer;
	}
	
	@FXML
    private Label wonPlayer;
    
    @FXML
    void btnQuit(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void btnRestart(ActionEvent event) {
		try {
			final String FILE_PATH = "../XML/Menu.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FILE_PATH));
			player1 = new Player1();
			player2 = new Player2();
			playingPlayer = player1;
			MenuController menuController = new MenuController(new Board(), player1, player2, playingPlayer);
			fxmlLoader.setController(menuController);
			Parent root;
			root = fxmlLoader.load();
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			stage.setScene(new Scene(root));
    		stage.setTitle(null);
    		stage.show();
    		
		}catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void initialize() {
    	Player wonplayer;
		player1.plusPoint(10);
		player2.plusPoint(20);
    	if (player1.getTotalPoint() > player2.getTotalPoint()) {
    		wonplayer = player1;
    	} else {
    		wonplayer = player2;
    	}
    	
    	wonPlayer.setText(wonplayer.getName());
    }
}
