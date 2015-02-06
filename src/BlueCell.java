


import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class BlueCell extends Cell{
	
	private static Color myColor;
	
	public BlueCell() {
		super();
		getShape().setFill(myColor);
	}

	public BlueCell(Map<String, String> params) {
		super();
		myColor = Color.valueOf(params.get("color"));
		getShape().setFill(myColor);
	}
	
	public String toString(){
		return "BlueCell";
	}
		

}
