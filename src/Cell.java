
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle{
	
	// Get these from parsed XML data
	private static int myWidth = 10;
	private static int myHeight = 10;
	//private static String myStateName;
	
	public Cell() {
		super(myWidth, myHeight);
		//tell the cell by its name
		//Color 
	//	setFill();
	}

	/*public static void setType(String string){
		myStateName = string;
	}
	//do we ever use this? if we never use the name of the celltype, we should get rid of this
	//and revert our cellstate data structure back to just a simple hashmap
	public String toString(){
		return myStateName;
	}*/
	
	
}
