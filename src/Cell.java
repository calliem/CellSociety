
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle{
	
	private static Color myColor;
	
	// Get these from parsed XML data or from properties doc CELL_SIZE
	//how to do it with parsed XML? Make a random cell instance in the beginning to set its width and height values?
	private static int myWidth = 10;
	private static int myHeight = 10;
	
	public Cell() {
		super(myWidth, myHeight);
	}
}

