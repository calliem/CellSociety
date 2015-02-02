
import java.util.Map;

import javafx.scene.paint.Color;

public class FishCell extends Cell{
	
	// private int reproductionAge; this should be passed into the controller
	
	private int myAge;
	private Color myColor;
	
	public FishCell(int age){
		super();
		setFill(myColor);
		myAge = age;
	}

	public FishCell(Map<String, String> params) {
		super();
		myColor = Color.valueOf(params.get("color"));
		setFill(myColor);
		myAge = 0;
	}
	
	public int getAge(){
		return myAge;
	}

}
