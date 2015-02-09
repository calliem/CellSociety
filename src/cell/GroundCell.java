package cell;

import java.util.ArrayList;
import java.util.List;

import controller.Updatable;
import cellsociety.GridData;

public class GroundCell extends Cell implements Updatable {

	private static int sugarGrowBackRate;
	private static int sugarGrowBackInterval;
	private int maxSugar;
	private int curSugar;
	private int curInterval;

	// public GroundCell()

	@Override
	public void ageOneChronon(GridData data) {
		update();
		// Cell curCell = data.curCell();
		data.updateLocation(this);// = this;
		// data.getNewGrid()[data.getRow()][data.getCol()] = this;
	}

	public void update() {
		curInterval++;
		if (sugarGrowBackInterval == curInterval) {
			curSugar = Math.min(curSugar + sugarGrowBackRate, maxSugar);
		}

	}

	public int harvest() {
		int oldSugar = curSugar;
		curSugar = 0;
		return oldSugar;
	}
}
