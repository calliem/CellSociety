



import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class SharkCell extends Cell implements ReproducingCell{
	
	private int myMaxEnergy = 2;
	private int myReproducingAge = 3;
	private int myCurrAge;
	private int myCurrEnergy;
	//private static Color myColor;
	private Color myColor;
		
	public SharkCell(){
		super();
		myCurrAge = 0;
		myCurrEnergy = myMaxEnergy;
		//getShape().setFill(myColor);
		getShape().setFill(Color.BLACK);
		
	}

	public SharkCell(Map<String, String> params) {
		super();
		myColor = Color.valueOf(params.get("color"));
		//getShape().setFill(myColor);
		getShape().setFill(Color.BLACK);
		myCurrAge = 0;
		myCurrEnergy = myMaxEnergy;
		//myEnergy = Integer.parseInt(params.get("fullEnergy"));
	}

	
	//REFACTOR!!!  make ReproducingCell abstract
	@Override
	public Cell reproducingResult() {
		if(myCurrAge == myReproducingAge){
			myCurrAge = 0;
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
	
	public String toString(){
		return "SharkCell";
	}

	public void replenishEnergy() {
		myCurrEnergy = myMaxEnergy;
		
	}
}
