import java.util.HashMap;
import java.util.Map;


import javafx.scene.paint.Color;


public class EmptyCell extends Cell{
	
	public EmptyCell() {
		super();
		setFill(Color.WHITE);
		//above setfill should be removed
	}
	//add on map parameter
	public EmptyCell(Map<String, String> params) {
		super();
		setFill(Color.valueOf(params.get("color")));
	}
	
	//for debugging 
	public String toString(){
		return "EmptyCell";
	}
		

}
