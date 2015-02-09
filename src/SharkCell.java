



import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class SharkCell extends Cell implements ReproducingCell{
	
	private static int myMaxEnergy;
	private static int myReproducingAge;
	private int myCurAge;
	private int myCurEnergy;
	//private static Color myColor;
	private static Color myColor;
		
	public SharkCell(String name){
		super(name);
		myCurAge = 0;
		myCurEnergy = myMaxEnergy;
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
		myCurAge = 0;
		myCurEnergy = myMaxEnergy;
		//myEnergy = Integer.parseInt(params.get("fullEnergy"));
	}

	
	//REFACTOR!!!  make ReproducingCell abstract
	@Override
	public Cell reproducingResult() {
		if(myCurAge >= myReproducingAge){
			myCurAge = 0;
			return new SharkCell("SharkCell");
		}
		else{
			return new Cell("EmptyCell");
		}
	}
	
	public int getEnergy(){
		return myCurEnergy;
	}
	
	public Cell ageOneChronon(){
		myCurAge++;
		myCurEnergy--;
		return this;
	}
	
	public boolean isDead(){
		return myCurEnergy == 0;
	}
	
	public String toString(){
		return "SharkCell";
	}

	public void replenishEnergy() {
		myCurEnergy = myMaxEnergy;
		
	}
}
