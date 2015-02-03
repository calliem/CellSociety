

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class RedCell extends Cell{
	
	private static Color myColor;
	
	public RedCell() {
		super();
		getShape().setFill(myColor);
	}

	public RedCell(Map<String, String> params) {
		super();
		myColor = Color.valueOf(params.get("color"));
		getShape().setFill(myColor);
	}
	
	public String toString(){
		return "RedCell";
	}
}
