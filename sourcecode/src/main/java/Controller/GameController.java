package Controller;

import java.io.IOException;
import Board.Board;
import Gem.SmallGem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Player.Player;
import Player.Player1;
import Player.Player2;

public class GameController{
	private Board board;
	private static Player1 player1;
	private static Player2 player2;
	static Player playingPlayer;
	static int curDirection;
	static int curPosition;

	public GameController(Board board, Player1 player1, Player2 player2, Player playingPlayer) {
		this.board = board;
		GameController.player1 = player1;
		GameController.player2 = player2;
		GameController.playingPlayer = playingPlayer;
	}
	
    @FXML
    private Label name2;

    @FXML
    private Label name1;
    

    @FXML
    private Pane sq0;

    @FXML
    private ImageView imgGem;

    @FXML
    private Label pnt0;

    @FXML
    private Pane sq1;

    @FXML
    private Label pnt1;

    @FXML
    private Pane sq2;

    @FXML
    private Label pnt2;

    @FXML
    private Pane sq3;

    @FXML
    private Label pnt3;

    @FXML
    private Pane sq4;

    @FXML
    private Label pnt4;

    @FXML
    private Pane sq5;

    @FXML
    private Label pnt5;

    @FXML
    private Pane sq6;

    @FXML
    private Label pnt6;

    @FXML
    private Pane sq7;

    @FXML
    private Label pnt7;

    @FXML
    private Pane sq8;

    @FXML
    private Label pnt8;

    @FXML
    private Pane sq9;

    @FXML
    private Label pnt9;

    @FXML
    private Pane sq10;
    
    @FXML
    private Label pnt10;

    @FXML
    private Pane sq11;

    @FXML
    private Label pnt11;
    
    @FXML
    private Label score2;

    @FXML
    private Label score1;
    
    @FXML
    private Button leftDir;

    @FXML
    private Button rightDir;
    

    @FXML
    private ImageView turn1;

    @FXML
    private ImageView turn2;
    

    @FXML
    void chooseSquClicked(MouseEvent event) {
    	curPosition = Integer.parseInt(((Node) event.getSource()).getId().substring(2));
    	
    	if (playingPlayer == player1) {
    		if (player1.getRange().contains(curPosition) && board.getShape(curPosition).getPoint() != 0) {
    			leftDir.setVisible(true);
    			rightDir.setVisible(true);
    		} 
    		
    	} else if (playingPlayer == player2) {
    		if (player2.getRange().contains(curPosition) && board.getShape(curPosition).getPoint() != 0) {
    			leftDir.setVisible(true);
    			rightDir.setVisible(true);
    		}
    		
    	}

    }
    
    
    @FXML
    void btnChooseDir(ActionEvent event) {
    	if (playingPlayer == player1) {
    		if (((Node) event.getSource()).getId().equals("leftDir")) {
    			curDirection = -1;
    		} else if (((Node) event.getSource()).getId().equals("rightDir")) {
    			curDirection = 1;
    		}
    	} else if (playingPlayer == player2) {
    		if (((Node) event.getSource()).getId().equals("leftDir")) {
    			curDirection = 1;
    		} else if (((Node) event.getSource()).getId().equals("rightDir")) {
    			curDirection = -1;
    		}
    	}
		
    	leftDir.setVisible(false);
		rightDir.setVisible(false);	
		
		turn(board, playingPlayer, curPosition, curDirection);
		
		
		if (board.getShape(0).getPoint() == 0 && board.getShape(6).getPoint() == 0) {
			
		} else {
			takeTurn(player1, player2, turn1, turn2);
		}
		
		if (checkNeedRefill(board, playingPlayer)) {
			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("REFILL ALERT!");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Your squares are empty! You need to refill");
	    	alert.showAndWait();
	    	
			refillGems(board, playingPlayer);
			updatePoint();
		}
		updatePoint();
		
		if (stopGame(board)) {
			try {
				final String FILE_PATH = "../XML/End.fxml";
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FILE_PATH));
				EndController endController = new EndController(board, player1, player2, playingPlayer);
				fxmlLoader.setController(endController);
				Parent root;
				root = fxmlLoader.load();
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    		
	    		stage.setScene(new Scene(root));
	    		stage.setTitle("Cart");
	    		stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    
		}
    }
    
	public static boolean stopGame(Board board) { // kiểm tra điều kiện dừng game
		if (board.getShape(0).getPoint() == 0 && board.getShape(6).getPoint() == 0) {
			return true;
		}
		return false;
	}
	
    public static void takeTurn(Player1 player1, Player2 player2, ImageView turn1, ImageView turn2) { // đổi người thực hiện lượt chơi
		if (playingPlayer == player1 ) {
			playingPlayer = player2;
			turn1.setVisible(false);
			turn2.setVisible(true);
			
		} else if (playingPlayer == (player2)) {
			playingPlayer = player1;
			turn1.setVisible(true);
			turn2.setVisible(false);
		}
    }
    
    
    public static void aPhase(Board board,Player playingPlayer, int position, int direction) {
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
    
    public static void turn(Board board,Player playingPlayer, int position, int direction) { // thực hiện một lượt chơi khi đã có hướng và ô
		if (playingPlayer instanceof Player1) {
			playingPlayer = (Player1) playingPlayer;
		} else if (playingPlayer instanceof Player2) {
			playingPlayer = (Player2) playingPlayer;
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Notification!");
    	alert.setHeaderText(null);
    	String content = "";
		
		while(true) {
			if(board.getShape(position).getPoint() > 0) {
				int curPosition = position;
				int phasePoint = board.getShape(position).getPoint();	 // number of gems in chosen square
				aPhase(board, playingPlayer, position, direction);
				curPosition = position + direction*phasePoint;
				while(true) {
					if(board.getShape(curPosition + direction).getPoint() != 0 && !board.getShape(curPosition + direction).toString().equals("Semicircle")) {
						// Next square != Semicircle && point != 0 => continue
					
						int nextPosition = curPosition + direction;
						phasePoint = board.getShape(nextPosition).getPoint();
						aPhase(board, playingPlayer, nextPosition, direction);
						curPosition = nextPosition + direction*phasePoint;
					}
					
					else if(board.getShape(curPosition + direction).toString().equals("Semicircle")) {
						// next square is a Semicircle and point != 0 => stop turn
						content += "Stop because the next square is the Semicircle" + "\n";
				
						break;
					}
					
					
					else if (board.getShape(curPosition + direction).getPoint() == 0 && board.getShape(curPosition + 2*direction).getPoint() != 0 ) {
						// next square has 0 gems
						while (board.getShape(curPosition + direction).getPoint() == 0 && board.getShape(curPosition + 2*direction).getPoint() != 0 && !board.getShape(curPosition + direction).toString().equals("Semicircle")) {
							content += "Player " + playingPlayer.getName() + " take " + board.getShape(curPosition + 2*direction).getPoint() + " gems" + "\n";
			
							playingPlayer.plusPoint(board.getShape(curPosition + 2*direction).getPoint());
							board.getShape(curPosition + 2*direction).removeGem();
							curPosition += 2*direction;
						}
						
						break;
					}
					else {
						content += "You get 0 gem!";
			
						break;
					}
					
				}
				break;
			}
			else {
				content += "The chosen square does not have any gems to disperse";

				break;
			}
		}
		
		alert.setContentText(content);
		alert.showAndWait();

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

    
    public void updatePoint() {
    	score1.setText(Integer.toString(player1.getTotalPoint()));
    	score2.setText(Integer.toString(player2.getTotalPoint()));
    	pnt0.setText(Integer.toString(board.getShape(0).getPoint()));
    	pnt1.setText(Integer.toString(board.getShape(1).getPoint()));
    	pnt2.setText(Integer.toString(board.getShape(2).getPoint()));
    	pnt3.setText(Integer.toString(board.getShape(3).getPoint()));
    	pnt4.setText(Integer.toString(board.getShape(4).getPoint()));
    	pnt5.setText(Integer.toString(board.getShape(5).getPoint()));
    	pnt6.setText(Integer.toString(board.getShape(6).getPoint()));
    	pnt7.setText(Integer.toString(board.getShape(7).getPoint()));
    	pnt8.setText(Integer.toString(board.getShape(8).getPoint()));
    	pnt9.setText(Integer.toString(board.getShape(9).getPoint()));
    	pnt10.setText(Integer.toString(board.getShape(10).getPoint()));
    	pnt11.setText(Integer.toString(board.getShape(11).getPoint()));

    }
    

    
    public void initialize() {
    	
    	leftDir.setVisible(false);
		rightDir.setVisible(false);

    	updatePoint();

    	name1.setText(player1.getName());
    	name2.setText(player2.getName());
    	
    
    	if (playingPlayer == player1) {
    		turn1.setVisible(true);
    		turn2.setVisible(false);
    	} else {
    		turn2.setVisible(true);
    		turn1.setVisible(false);
    	}
    	
    	
    }
}
