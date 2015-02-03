
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle{
	
	private static Color myColor;
	
	// Get these from parsed XML data or from properties doc CELL_SIZE
	//how to do it with parsed XML? Make a random cell instance in the beginning to set its width and height values?
	private static double myWidth;
	private static double myHeight;
	
	public Cell() {
		super(myWidth, myHeight);
	}
	
	public static void setCellSize(double width, double height) {
		myWidth = width;
		myHeight = height;
	}
}

