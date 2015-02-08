import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Cell{
	
	private static double myWidth;
	private static double myHeight;	
	private Shape myShape;
	private Color myColor = Color.CYAN;
	
	public Cell() {
		myShape = new Rectangle(myWidth, myHeight);
	}
	
	public Color getColor() {
		return myColor;
	}
	
	public void setColor(Color color) {
		myColor = color;
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

