package Player;
import java.util.Arrays;
import java.util.List;

public class Player2 extends Player{
    private static final List<Integer> range = Arrays.asList(7, 8, 9, 10, 11);
    public Player2(){
        super("Play 2");
    }
    public Player2(String name){
        super(name);
    }
    @Override
    public List <Integer> getRange(){
        return  range;
    }


}
