package cell;

import java.util.Map;

import cellsociety.Strings;

public class SharkCell extends Cell implements ReproducingCell {

	private static int myMaxEnergy;
	private static int myReproducingAge;
	private int myCurrAge;
	private int myCurrEnergy;

	public SharkCell(String name) {
		super(name);
		myCurrAge = 0;
		myCurrEnergy = myMaxEnergy;
	}

	public SharkCell(Map<String, String> params) {
		super(params);
		myMaxEnergy = Integer.parseInt(params.get("maxEnergy"));
		myReproducingAge = Integer.parseInt(params.get("reproductionAge"));
		myCurrAge = 0;
		myCurrEnergy = myMaxEnergy;
	}

	// REFACTOR!!! make ReproducingCell abstract
	@Override
	public Cell reproducingResult() {
		if (myCurrAge >= myReproducingAge) {
			myCurrAge = 0;
			return new SharkCell(Strings.SHARK_CELL);
		} else {
			return new Cell(Strings.EMPTY_CELL);
		}
	}

	public int getEnergy() {
		return myCurrEnergy;
	}

	public Cell ageOneChronon() {
		myCurrAge++;
		myCurrEnergy--;
		return this;
	}

	public boolean isDead() {
		return myCurrEnergy == 0;
	}

	public String toString() {
		return Strings.SHARK_CELL;
	}

	public void replenishEnergy() {
		myCurrEnergy = myMaxEnergy;

	}
}
