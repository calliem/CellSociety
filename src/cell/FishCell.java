package cell;

import java.util.Map;

import cellsociety.Strings;

public class FishCell extends Cell implements ReproducingCell {

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

	@Override
	public Cell reproducingResult() {
		if (myCurrAge >= myReproducingAge) {
			myCurrAge = 0;
			return new FishCell(Strings.FISH_CELL);
		} else {
			return new Cell(Strings.EMPTY_CELL);
		}
	}

	@Override
	public Cell ageOneChronon() {
		myCurrAge++;
		return this;
	}

	@Override
	public String toString() {
		return Strings.FISH_CELL;
	}

}
