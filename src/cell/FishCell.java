// This entire file is part of my masterpiece.
// Kevin Delgado

package cell;

import java.util.Map;

import cellsociety.Coordinate;
import cellsociety.GridData;
import javafx.scene.paint.Color;

public class FishCell extends AquaticCell {

	private static int myReproducingAge;
	private static Color myColor;
	public FishCell(String name){
		super(name);
		//getShape().setFill(myColor);

	}

	public FishCell(Map<String, String> params) {
		super(params);
		myColor = Color.valueOf(params.get("color"));
		myReproducingAge = Integer.parseInt(params.get("reproductionAge"));
		//getShape().setFill(myColor);

	}

	public int getAge(){
		return myReproducingAge;
	}

	
	@Override
	protected Cell instantiate(){
		return new FishCell("FishCell");
	}

	public String toString(){
		return "FishCell";
	}

	@Override
	protected void swapRole(GridData data, Coordinate dest) {

	}

}
