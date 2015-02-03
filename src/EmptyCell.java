

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class EmptyCell extends Cell{
	
	private static Color myColor;
	
	public EmptyCell() {
		super();

		//setFill(Color.CYAN);
		getShape().setFill(myColor);
	}
	//add on map parameter
	public EmptyCell(Map<String, String> params) {
		super();
		myColor = Color.valueOf(params.get("color"));
		getShape().setFill(myColor);
	}
	
	//for debugging 
	public String toString(){
		return "EmptyCell";
	}
		

}
