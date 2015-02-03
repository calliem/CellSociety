import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Cell{
	
	// Get these from parsed XML data or from properties doc CELL_SIZE
	//how to do it with parsed XML? Make a random cell instance in the beginning to set its width and height values?
	private static int myWidth = 10;
	private static int myHeight = 10;
	
	private Shape myShape;
	
	public Cell() {
		Shape myShape = new Rectangle(myWidth, myHeight);
	}
	
	protected Shape getShape(){
		return myShape;
	}
	
	protected int getWidth(){
		return myWidth;
	}
	
	protected int getHeight(){
		return myHeight;
	}
}

