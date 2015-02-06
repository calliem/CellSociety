



import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Cell{
	
	private static double myWidth;
	private static double myHeight;	
	private static Color myColor;
	private String myName;
	private Shape myShape;
	
	public Cell(String name) {
		myName = name;
	}
	
	public Cell(Map<String, String> params){
		myShape = new Rectangle(myWidth, myHeight);
		myColor = Color.valueOf(params.get("color"));
		getShape().setFill(myColor);
		myName = params.get("state");
		
	}
	
	public String toString(){
		return myName;
	}
	
	public Shape getShape(){
		return myShape;
	}
	
	protected double getWidth(){
		return myWidth;
	}
	
	protected double getHeight(){
		return myHeight;
	}
	
	public static void setCellSize(double width, double height) {
		myWidth = width;
		myHeight = height;
	}
}

