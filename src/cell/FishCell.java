package cell;

import java.util.Map;

import cellsociety.Coordinate;
import cellsociety.GridData;
import cellsociety.Strings;
import javafx.scene.paint.Color;

public class FishCell extends AquaticCell {

	private static int myReproducingAge;
	
	public FishCell(String name){
		super(name);
	}

	public FishCell(Map<String, String> params) {
		super(params);
	}


	//this below method is necessary in order for AquaticCell to work properly
	@Override
	protected Cell instantiate(){
		return new FishCell(Strings.FISH_CELL);
	}

	public String toString(){
		return Strings.FISH_CELL;
	}

	@Override
	protected void swapRole(GridData data, Coordinate dest) {

	}

}
