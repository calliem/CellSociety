import java.util.Map;

import javafx.scene.paint.Color;

public class FishCell extends Cell implements ReproducingCell{

	// private int reproductionAge; this should be passed into the controller

	private static int myReproducingAge;
	private int myCurrAge;
	private static Color myColor;
	

	public FishCell(String name){
		super(name);
		myCurrAge = 0;
		getShape().setFill(myColor);
	}

	public FishCell(Map<String, String> params) {
		super(params);
		myColor = Color.valueOf(params.get("color"));
		myReproducingAge = Integer.parseInt(params.get("reproductionAge"));
		getShape().setFill(myColor);
		myCurrAge = 0;

	}

	public int getAge(){
		return myReproducingAge;
	}

	//REFACTOR!!!! make ReproducingCell interactive
	@Override
	public Cell reproducingResult() {
		if(myCurrAge == myReproducingAge){
			myCurrAge = 0;
			return new FishCell();
		}
		else{
			return new EmptyCell();
		}
	}

	public Cell ageOneChronon(){
		myCurrAge++;
		return this;
	}
	
	public String toString(){
		return "FishCell";
	}

}