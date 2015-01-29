
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle{
	
	//private static String myStateName;
	private static int myW = 10;
	private static int myH = 10;
	public String myLabel;
	//private Color myColor;
	
	public Cell(String s) {
		super(myW, myH);
		myLabel = s;
		//myColor = 
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

