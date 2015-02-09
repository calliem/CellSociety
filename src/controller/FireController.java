package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import cellsociety.Strings;
import cell.Cell;
import cellsociety.Coordinate;

public class FireController extends SimpleController{

	private int myProbCatch;


	public FireController(Map<String, String> parameters) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		super(parameters);
		myProbCatch = Integer.parseInt(parameters.get(Strings.CATCH_PROBABILITY));
	}	

	@Override
	protected String getNeighborsState(Cell[][] grid, List<Coordinate> neighbors) {
		for(Coordinate coords: neighbors){
			if(grid[coords.getX()][coords.getY()].toString().equals(Strings.FIRE_CELL)){
				if(new Random().nextInt(100) < myProbCatch){
					return Strings.FIRE_CELL;

				}
				break;
			}
		}
		return Strings.TREE_CELL;
	}

	@Override
	protected Cell newState(Cell[][] newGrid, Cell cell, String hoodState,
			int r, int c) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {

		if(cell.toString().equals(Strings.TREE_CELL)){

			return makeCell(hoodState);
		}
		return makeCell(Strings.EMPTY_CELL);
	}
}
