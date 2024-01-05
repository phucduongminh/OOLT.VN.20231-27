package Board;

public class TestBoard {
    private TestBoard(){}
    //Test hoat dong, cac method cua Board o day
    public static void main(){
        Board board = new Board();
        System.out.println(board.getShape(-15).getPoint());
        board.print();
    }
}
