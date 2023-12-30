package Player;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int totalPoint;
    private String name;
    private int direction; // 1: counterclockwise, -1: clockwise
    private static List<Integer> range = new ArrayList<Integer>();
    private int pointInHand;

    public Player(String name) {
        this.totalPoint = 0;
        this.name = name;
        this.pointInHand = 0;
    }
    public int getTotalPoint() {
        return totalPoint;
    }
    public String getName() {
        return name;
    }
    public List<Integer> getRange() {
        return range;
    }
    public int getDirection() {
        return this.direction;
    }
    public void plusPoint(int point) {
        totalPoint += point;
    }
    public void minusPoint(int point) {
        totalPoint -= point;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getPointInHand() {
        return pointInHand;
    }
    public void setPointInHand(int pointInHand) {
        this.pointInHand = pointInHand;
    }

}