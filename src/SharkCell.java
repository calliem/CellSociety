


import java.util.HashMap;

import javafx.scene.paint.Color;

public class SharkCell extends Cell implements ReproducingCell{
	
	private int myEnergy = 4;
	private int myAge = 3;
	private int myCurrAge;
	private int myCurrEnergy;
	//private static Color myColor;
	private Color myColor;
		
	public SharkCell(){
		super();
		myCurrAge = 0;
		myCurrEnergy = myEnergy;
		//getShape().setFill(myColor);
		getShape().setFill(Color.BLACK);
		
	}

	public SharkCell(HashMap<String, String> params) {
		super();
		myColor = Color.valueOf(params.get("color"));
		//getShape().setFill(myColor);
		getShape().setFill(Color.BLACK);
		myAge = 0;
		myCurrEnergy = myEnergy;
		//myEnergy = Integer.parseInt(params.get("fullEnergy"));
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
	
	public String toString(){
		return "SharkCell";
	}
}
