package Board;

import Gem.BigGem;
import Gem.SmallGem;
import Player.Player;
import Player.Player1;
import Player.Player2;
import Shape.Semicircle;
import Shape.Square;
import Shape.Shape;

public class Board {
  private Player1 player1;
	private Player2 player2 ;

  // Mang kich thuoc 12 chua cac hinh trong bang tro choi
  private Shape[] board = new Shape[12];

  // khoi tao bang va cac hinh trong bang
  public Board() {

    // khoi tao 2 hinh ban nguyet(o quan) nam o vi tri 0 va 6 trong mang
    board[0] = new Semicircle(0);
    board[6] = new Semicircle(6);

    // khoi tao 10 o vuong (o dan) nam o cac vi tri con lai
    // cac vi tri la 1,2,3,4,5,7,8,9,10,11 (khong co 6)
    for (int i = 1; i <= 11; i++) {
      if (i == 6)
        continue;
      board[i] = new Square(i);
    }

    // them quan quan(big gem) vao o quan(Semicircle)
    BigGem bigGem = new BigGem();
    board[0].addGem(bigGem);
    board[6].addGem(bigGem);

    // them 5 quan dan(small gem) vao moi o dan(Square)
    SmallGem smallGem = new SmallGem();
    for (int i = 1; i <= 5; i++) {
      for (int j = 1; j <= 11; j++) {
        if (j == 6)
          continue;
        board[j].addGem(smallGem);
      }
    }
  }

  // lay hinh trong bang theo vi tri
  public Shape getShape(int position) {
    while (position < 0) {
      position += 12;
    }
    while (position >= 12) {
      position -= 12;
    }
    return board[position];
  }

  // in thong tin cua moi hinh trong bang
  // thong tin bao gom "ten hinh", "diem"
  public void print() {
    // Phải in được tên của Shape v� số điểm trong Shape đó
    // VD: Nếu shape đang l� Semicircle, yêu cầu in ra
    // Semicircle : 5 point
    System.out.println("=====================");
    for (Shape shape : board) {
      System.out.println("" + shape + " " + shape.getPosition() + ": " + shape.getPoint() + " point(s)" + "\n");
    }
  }

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

  public void turn(Player playingPlayer, int position, int direction) {
		while(true) {
			if(this.getShape(position).getPoint() > 0) {
				int curPosition = position;
				int phasePoint = this.getShape(position).getPoint();	
				pickAndDrop(this, playingPlayer, position, direction);
				curPosition = position + direction*phasePoint;
				while(true) {
					if(this.getShape(curPosition + direction).getPoint() != 0 && !this.getShape(curPosition + direction).toString().equals("Semicircle")) {
						System.out.println();
						int nextPosition = curPosition + direction;
						phasePoint = this.getShape(nextPosition).getPoint();
						pickAndDrop(this, playingPlayer, nextPosition, direction);
						curPosition = nextPosition + direction*phasePoint;
					}
					else if (this.getShape(curPosition + direction).getPoint() == 0 && this.getShape(curPosition + 2*direction).getPoint() != 0 ) {
						System.out.println("Player " + playingPlayer.getName() + " won " + this.getShape(curPosition + 2*direction).getPoint() + " gems");
						playingPlayer.plusPoint(this.getShape(curPosition + 2*direction).getPoint());
						this.getShape(curPosition + 2*direction).removeGem();
						break;
					}
					else if(this.getShape(curPosition + direction).toString().equals("Semicircle")) {
						System.out.println("Stop because the next square is the half circle");
						break;
					}
				}
				break;
			}
			else {
				System.out.println("The chosen square does not have any gems to disperse");
				break;
			}
		}
	}
}