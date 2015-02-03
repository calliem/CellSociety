import java.util.Map;

import javafx.scene.paint.Color;


public class BlueCell extends Cell{

//	private Color myColor;
	
	public BlueCell(){
		super();
		
	//	setFill(Color.valueOf("blue"));
	}
	
	//could this be added to the cell class? this is beoming duplicated code for every class
	public BlueCell(Map<String, String> params){
		super();
		myColor = Color.valueOf(params.get("color"));
		getShape().setFill(myColor);
	}
	
	public String toString(){
		return "BlueCell";
	}
}
