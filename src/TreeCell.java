import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class TreeCell extends Cell{
	
	private static Color myColor; 
	
	public TreeCell(){
		super();
		getShape().setFill(myColor);
		//System.out.println("hi" + myColor);

	}
	
	public TreeCell(Map<String, String> params) {
		super();
		myColor = Color.valueOf(params.get("color"));
		getShape().setFill(myColor);
	}

	public String toString(){
		return "TreeCell";
	}
	
}
