
import java.util.HashMap;

import javafx.scene.paint.Color;

public class SharkCell extends Cell implements ReproducingCell{
	
	private int myEnergy = 5;
	private int myAge = 3;
	private int myCurrAge;
	private int myCurrEnergy;
	private static Color myColor;
	
	//energyGained shoudl be a parameter passed from the XMLparser into the control
	
	public SharkCell(){
		super();
		myCurrAge = 0;
		myCurrEnergy = myEnergy;
		getShape().setFill(myColor);
		
	}

	public SharkCell(HashMap<String, String> params) {
		super();
		myColor = Color.valueOf(params.get("color"));
		getShape().setFill(myColor);
		myAge = 0;
		myEnergy = Integer.parseInt(params.get("fullEnergy"));
	}

	
	//REFACTOR!!!  make ReproducingCell abstract
	@Override
	public Cell reproducingResult() {
		if(myCurrAge == myAge){
			return new SharkCell();
		}
		else{
			return new EmptyCell();
		}
	}
	
	public int getEnergy(){
		return myCurrEnergy;
	}
	
	public Cell ageOneChronon(){
		myCurrAge++;
		myCurrEnergy--;
		return this;
	}
	
	public boolean isDead(){
		return myCurrEnergy == 0;
	}
}
