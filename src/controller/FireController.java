package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cell.Cell;

public class FireController extends SimpleController{

	private int myProbCatch;

	public FireController(Map<String, String> parameters) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		super(parameters);
		myProbCatch = Integer.parseInt(parameters.get("probCatch"));
	}	

	@Override
	protected String getNeighborsState(Cell[][] grid, List<Integer[]> neighbors) {
		for(Integer[] coords: neighbors){
			if(grid[coords[Controller.X_COORD]][coords[Controller.Y_COORD]].toString().equals("FireCell")){
				if(new Random().nextInt(100) < myProbCatch){
					return "FireCell";
				}
				break;
			}
		}
		return "TreeCell";
	}

	@Override
	protected Cell newState(Cell[][] newGrid, Cell cell, String hoodState,
			int r, int c) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {
		if(cell.toString().equals("TreeCell")){
			return makeCell(hoodState);
		}
		return makeCell("EmptyCell");
	}
}
