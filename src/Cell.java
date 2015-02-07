


import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Cell{
	
	private static double myWidth;
	private static double myHeight;	
	private static Color myColor;
	private Shape myShape;
	private String myName;
	
	//test to see if we even need this
	public Cell(String name){
		myShape.setFill(myColor);
		myName = name;
	}
	
	public Cell(Map<String, String> params) {
		//use reflection with shape, but right now it will simply be made into a rectangle.
		myShape = new Rectangle(myWidth, myHeight); //this is currently set to null ...maybe pass this as a parameter
		myName = params.get("name");
		myColor = Color.valueOf(params.get("color"));
		myShape.setFill(myColor);
	}

	protected Shape getShape(){
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
	
	public String toString(){
		return myName;
	}
	
	public String stateName(){
		return "Cell";
	}
}

