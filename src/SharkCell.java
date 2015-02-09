





import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class SharkCell extends Cell implements ReproducingCell{
	
	private static int myMaxEnergy;
	private static int myReproducingAge;
	private int myCurrAge;
	private int myCurrEnergy;
	//private static Color myColor;
	private static Color myColor;
		
	public SharkCell(String name){
		super(name);
		myCurrAge = 0;
		myCurrEnergy = myMaxEnergy;
		//getShape().setFill(myColor);
		getShape().setFill(myColor);	 
	}

	public SharkCell(Map<String, String> params) {
		super(params);
		System.out.println("cellparammap in shark cell:");
		for (String s: params.keySet()){
			System.out.print(s + ": ");
			System.out.println(params.get(s));
		}
		
		myColor = Color.valueOf(params.get("color"));
		myMaxEnergy = Integer.parseInt(params.get("maxEnergy"));
		System.out.println(myMaxEnergy);
		myReproducingAge = Integer.parseInt(params.get("reproductionAge"));
		System.out.println(myReproducingAge);
		//getShape().setFill(myColor);
		getShape().setFill(Color.BLACK);
		myCurrAge = 0;
		myCurrEnergy = myMaxEnergy;
		//myEnergy = Integer.parseInt(params.get("fullEnergy"));
	}

	
	//REFACTOR!!!  make ReproducingCell abstract
	@Override
	public Cell reproducingResult() {
		if(myCurrAge >= myReproducingAge){
			myCurrAge = 0;
			return new SharkCell("SharkCell");
		}
		else{
			return new Cell("EmptyCell");
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
