


//import Ground;

import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Cell{
	
	private static double myWidth;
	private static double myHeight;	
//	private static Color myColor;
	private Shape myShape;

	private String myName;
	//private Ground myGround;
	private Color myColor;

	
	public Cell(String name) {
		myShape = new Rectangle(myWidth, myHeight);
		myName = name;
		//myShape.setFill(color);
	}

	public Cell(Map<String, String> params) {
		//use reflection with shape, but right now it will simply be made into a rectangle.
		myShape = new Rectangle(myWidth, myHeight); //this is currently set to null ...maybe pass this as a parameter
		myName = params.get("name");
		myColor = Color.valueOf(params.get("color"));
		myShape.setFill(myColor);
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
	
	public Color getColor(){
		return myColor;
	}
	
	public String toString(){
		return myName;
	}
	
	public void setColor(Color color){
		myShape.setFill(color);
	}
	
	public static void setCellSize(double width, double height) {
		myWidth = width;
		myHeight = height;
	}
}

