


import java.util.Map;

import javafx.scene.paint.Color;

public class FishCell extends Cell implements ReproducingCell{

	// private int reproductionAge; this should be passed into the controller

	private static int myReproducingAge = 3;
	private int myCurrAge;
	private Color myColor;

	public FishCell(){
		super();
		myCurrAge = 0;

		//getShape().setFill(myColor);
		getShape().setFill(myColor);

	}

	public FishCell(Map<String, String> params) {
		super();
		myColor = Color.valueOf(params.get("color"));

		//setFill(myColor);
		//setFill(Color.YELLOW);
		//myCurrAge = 0;


		//getShape().setFill(myColor);
		getShape().setFill(Color.YELLOW);
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
