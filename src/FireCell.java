import java.util.HashMap;
import java.util.Map;


import javafx.scene.paint.Color;

public class FireCell extends Cell{
	
	private static Color myColor;
	
	public FireCell() {
		super();
		getShape().setFill(myColor);
	}

	public FireCell(Map<String, String> params) {
		super();
		myColor = Color.valueOf(params.get("color"));
		getShape().setFill(myColor);
	}
	
	public String toString(){
		return "FireCell";
	}
		

}
