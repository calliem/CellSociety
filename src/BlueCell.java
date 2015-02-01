import java.util.Map;

import javafx.scene.paint.Color;


public class BlueCell extends Cell{

	public BlueCell(){
		super();
		setFill(Color.valueOf("blue"));
	}
	
	//could this be added to the cell class? this is beoming duplicated code for every class
	public BlueCell(Map<String, String> params){
		super();
		setFill(Color.valueOf(params.get("color")));
	}
	
	public String toString(){
		return "BlueCell";
	}
}
