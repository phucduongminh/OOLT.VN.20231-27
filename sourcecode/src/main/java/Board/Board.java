package Board;
import Gem.BigGem;
import Gem.SmallGem;
import Shape.Semicircle;
import Shape.Square;
import Shape.Shape;

public class Board {
    //Công việc
    //Viết code phải kèm theo chú thích
    //Yêu cầu với method print
    

    //Mang kich thuoc 12 chua cac hinh trong bang tro choi
    private Shape[] board = new Shape[12];
    
    
    
    //khoi tao bang va cac hinh trong bang
    public Board() {
    
       
        //khoi tao 2 hinh ban nguyet(o quan) nam o vi tri 0 va 6 trong mang
      board[0] = new Semicircle(0);
      board[6] = new Semicircle(6);
      
      //khoi tao 10 o vuong (o dan) nam o cac vi tri con lai
      //cac vi tri la 1,2,3,4,5,7,8,9,10,11 (khong co 6)
      for(int i = 1; i <= 11; i++){
          if(i == 6) continue;
          board[i] = new Square(i);
      }
      
    
    
      
      
      //them quan quan(big gem) vao o quan(Semicircle)
      BigGem bigGem = new BigGem();
      board[0].addGem(bigGem);
      board[6].addGem(bigGem);
      
      
      //them 5 quan dan(small gem) vao moi o dan(Square)
      SmallGem smallGem = new SmallGem();
      for(int i = 1; i <= 5; i++){
          for(int j = 1; j <= 11; j++){
              if(j == 6) continue;
              board[j].addGem(smallGem);
          } 
      }
    }
    
    //lay hinh trong bang theo vi tri
    public Shape getShape(int position) {
		while (position < 0) {
			position += 12;
		}
		while (position >= 12) {
			position -= 12;
		}
		return board[position];
	}
    
    
    
    //in thong tin cua moi hinh trong bang
    //thong tin bao gom "ten hinh", "diem"
    public void print() {
		//Phải in được tên của Shape v�  số điểm trong Shape đó
        //VD: Nếu shape đang l�  Semicircle, yêu cầu in ra
        // Semicircle : 5 point
        System.out.println("=====================");
        for(Shape shape : board){
          System.out.println("" + shape + " : " + shape.getPoint() + " point(s)" + "\n");
        }
	}
}