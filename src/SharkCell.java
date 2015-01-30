
import java.util.HashMap;

import javafx.scene.paint.Color;

public class SharkCell extends Cell{
	
	private int myEnergy;
	private int myAge;
	
	//energyGained shoudl be a parameter passed from the XMLparser into the control
	
	public SharkCell(int age){
		super();
		myAge = age;
	}

	public SharkCell(Color color, HashMap<String, String> params) {
		super();
		setFill(color);
		myAge = 0;
		myEnergy = Integer.parseInt(params.get("fullEnergy"));
	}
	
	

}
