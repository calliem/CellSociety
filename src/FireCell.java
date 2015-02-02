import java.util.Map;

import javafx.scene.paint.Color;

public class FireCell extends Cell{
	
	private static Color myColor;
	
	public FireCell(){
		super();
		setFill(myColor); //could we add this to the superclass somehow or make a new method to setfill that way we don't have duplicated code?
		
		
		//test below. color is not a static variable and needs to be updated every time for this to work. This isn't ideal so we'll have to find alternatives
		//i think this issue probably applies to every cell but not sure why it's only noticeable for firecell right now
	//	setFill(Color.valueOf("orange"));
	}

	public FireCell(Map<String, String> params) {
	
		super();
		myColor = Color.valueOf(params.get("color"));
		setFill(myColor);
	}
	
	public String toString(){
		return "FireCell";
	}
}
