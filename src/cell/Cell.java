package cell;
import java.util.Map;

import javafx.scene.paint.Color;

public class Cell{
	
	private String myName;
	private Color myColor;

	public Cell(String name) {
		myName = name;
	}

	public Cell(Map<String, String> params) {
		//use reflection with shape, but right now it will simply be made into a rectangle.
		myName = params.get("name");
		myColor = Color.valueOf(params.get("color"));
	}
	
	public Color getColor(){
		return myColor;
	}
	
	@Override
	public String toString(){
		return myName;
	}
	
	public void setColor(Color color){
		myColor = color;
	}
	
}

