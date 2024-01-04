package Board;

public class TestBoard {
    //Test hoat dong, cac method cua Board o day
    public static void main(String args[]){
        Board board = new Board();
        System.out.println(board.getShape(-15).getPoint());
        board.print();
    }
}
