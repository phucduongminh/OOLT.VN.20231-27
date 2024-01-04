package Player;

public class TestPlayer {

    public static void main(String[] args) {
        Player1 p1 = new Player1();
        System.out.println(p1.getName());
        Player2 p2 = new Player2();
        p2.setPointInHand(20);
        System.out.println(p2.getName() + " has " +p2.getPointInHand() + " on hand " + "and has " + p2.getTotalPoint());
        Player p = new Player("May");
        p.plusPoint(3);
        System.out.println(p.getName() + " has " + p.getTotalPoint());


    }
}
