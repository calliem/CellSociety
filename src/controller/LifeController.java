package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import cell.Cell;
import cellsociety.Strings;

public class LifeController extends SimpleController {

	private int stayOn;
	private int bornOn;

	public LifeController(Map<String, String> parameters) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		super(parameters);

		stayOn = Integer.parseInt(parameters.get(Strings.STAY_ON));
		bornOn = Integer.parseInt(parameters.get(Strings.BORN_ON));
	}

	protected String getNeighborsState(Cell[][] grid, List<Integer[]> neighbors) {
		int count = 0;
		for (Integer[] coords : neighbors) {
			if (grid[coords[Controller.X_COORD]][coords[Controller.Y_COORD]].toString()
					.equals(Strings.LIVE_CELL)) {
				count++;
			}
		}
		if (count == stayOn) {
			return Strings.TWO;
		}

		else if (count == bornOn) {
			return Strings.LIVE_CELL;
		}
		return Strings.EMPTY_CELL;
	}

	@Override
	protected Cell newState(Cell[][] newGrid, Cell cell, String neighborsState, int r,
			int c) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {
		if (neighborsState.equals(Strings.TWO)) {
			return makeCell(cell.toString());
		}
		return makeCell(neighborsState);
	}

}
