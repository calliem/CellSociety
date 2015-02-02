
import java.util.HashMap;

import javafx.scene.paint.Color;

public class SharkCell extends Cell{
	
	private int myEnergy;
	private int myAge;
	private static Color myColor;
	
	//energyGained shoudl be a parameter passed from the XMLparser into the control
	
	public SharkCell(int age){
		super();
		myAge = age;
		setFill(myColor);
	}

	public SharkCell(HashMap<String, String> params) {
		super();
		myColor = Color.valueOf(params.get("color"));
		setFill(myColor);
		myAge = 0;
		myEnergy = Integer.parseInt(params.get("fullEnergy"));
	}
	
	

}
