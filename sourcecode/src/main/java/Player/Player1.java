package Player;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Player1 extends Player{
    private static final List<Integer> range = Arrays.asList(1, 2, 3, 4, 5);
    public Player1(){
        super("Play 1");
    }

    @Override
    public List <Integer> getRange(){
        return  range;
    }

}
