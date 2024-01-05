package Shape;
import java.util.*;
import Gem.*;
public class Shape {
    private int position;
	public static final int MAX_POINTS = 60;
	private ArrayList<Gem> shape = new ArrayList<Gem>();
	
	public Shape(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		while (position < 0) {
			position += 12;
		}
		return position % 12;
	}

	public int getPoint() {
		int sum = 0;
		for (Gem gem:shape) {
			sum += gem.getPoint();
		}
		return sum;
	}
        
	public Shape copy(){
		Shape tmpShape = new Shape(this.position);
		for (Gem gem : this.shape) {
				tmpShape.addGem(gem);
		}
			return tmpShape;
			
	}
	public void addGem(Gem gem) {
		shape.add(gem);
	}
	public void removeGem() {
		while (shape.size() !=0) {
			shape.remove(0);
		}
	}
   
    
	
}

