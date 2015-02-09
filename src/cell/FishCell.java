package cell;

import java.util.Map;

import cellsociety.Strings;

public class FishCell extends Cell implements ReproducingCell {

	// private int reproductionAge; this should be passed into the controller

	private static int myReproducingAge;
	private int myCurrAge;

	public FishCell(String name) {
		super(name);
		myCurrAge = 0;
	}

	public FishCell(Map<String, String> params) {
		super(params);
		myReproducingAge = Integer.parseInt(params.get(Strings.REPRODUCTION_AGE));
		myCurrAge = 0;

	}

	public int getAge() {
		return myReproducingAge;
	}

	// REFACTOR!!!! make ReproducingCell interactive
	@Override
	public Cell reproducingResult() {
		if (myCurrAge >= myReproducingAge) {
			myCurrAge = 0;
			return new FishCell(Strings.FISH_CELL);
		} else {
			return new Cell(Strings.EMPTY_CELL);
		}
	}

	public Cell ageOneChronon() {
		myCurrAge++;
		return this;
	}

	public String toString() {
		return Strings.FISH_CELL;
	}

}
