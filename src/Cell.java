import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Cell{
	
	// Get these from parsed XML data or from properties doc CELL_SIZE
	//how to do it with parsed XML? Make a random cell instance in the beginning to set its width and height values?
	private static double myWidth;
	private static double myHeight;	
	private Shape myShape;
	
	public Cell() {
		myShape = new Rectangle(myWidth, myHeight);
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
}

