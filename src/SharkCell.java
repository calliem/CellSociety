
import java.util.HashMap;

import javafx.scene.paint.Color;

public class SharkCell extends Cell implements ReproducingCell{
	
	private int myEnergy;
	private int myAge;
	private int startingEnergy;
	private int reproducingAge;
	
	//energyGained shoudl be a parameter passed from the XMLparser into the control
	
	public SharkCell(){
		super();
		myAge = 0;
		myEnergy = startingEnergy;
	}

	public SharkCell(Color color, HashMap<String, String> params) {
		super();
		setFill(color);
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
