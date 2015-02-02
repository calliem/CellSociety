
import java.util.HashMap;

import javafx.scene.paint.Color;

public class SharkCell extends Cell implements ReproducingCell{
	
	private int myEnergy;
	private int myAge;
<<<<<<< HEAD
	private static Color myColor;
=======
	private int startingEnergy;
	private int reproducingAge;
>>>>>>> caf5952b1232272a701d228e66c17f65a84acd7b
	
	//energyGained shoudl be a parameter passed from the XMLparser into the control
	
	public SharkCell(){
		super();
<<<<<<< HEAD
		myAge = age;
		setFill(myColor);
=======
		myAge = 0;
		myEnergy = startingEnergy;
>>>>>>> caf5952b1232272a701d228e66c17f65a84acd7b
	}

	public SharkCell(HashMap<String, String> params) {
		super();
		myColor = Color.valueOf(params.get("color"));
		setFill(myColor);
		myAge = 0;
		myEnergy = Integer.parseInt(params.get("fullEnergy"));
	}

	@Override
	public Cell reproducingResult() {
		if(myAge == reproducingAge){
			return new SharkCell();
		}
		else{
			return new EmptyCell();
		}
	}
	
	

}
