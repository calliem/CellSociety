import java.util.HashMap;
import java.util.Map;


import javafx.scene.paint.Color;


public class EmptyCell extends Cell{
	
	private Color myColor;
	
	public EmptyCell() {
		super();
		//getShape().setFill(myColor);
		//above setfill should be removed
	}
	//add on map parameter
	public EmptyCell(Map<String, String> params) {
		super();
		//myColor = Color.valueOf(params.get("color"));
		Color color = Color.valueOf(params.get("color"));
		getShape().setFill(color);
	}
	
	//for debugging 
	public String toString(){
		return "EmptyCell";
	}
		

}
