import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
