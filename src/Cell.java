<<<<<<< HEAD

=======
>>>>>>> 2248ad8ddc79ce1cb07478e7a624f431890bf07d

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

<<<<<<< HEAD
public class Cell extends Rectangle{
	
	private static Color myColor; 
	private static String myStateName;
	
	public Cell(int width, int height) {
		super(width, height);
		setFill(myColor);
	}
	
	public static void setColor(Color color){
		myColor = color;
	}
	
	public static void setType(String string){
		myStateName = string;
	}
	
	public String toString(){
		return myStateName;
	}
	
	
}
=======

class Cell extends Rectangle{
	private CellState myState;
	
	public Cell(String state){
		myState = new CellState(state);
		setFill(myState.getColor());
	}
	
	public CellState getState(){
		return myState;
	}
}

>>>>>>> 2248ad8ddc79ce1cb07478e7a624f431890bf07d
