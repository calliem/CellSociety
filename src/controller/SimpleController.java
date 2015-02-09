package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import cell.Cell;

public abstract class SimpleController extends Controller{
	
	public SimpleController(Map<String, String>parameters) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		super(parameters);
	}

	@Override
	public Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Cell[][] newGrid = new Cell[grid.length][grid[0].length];
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[0].length; c++){
				Cell curCell = grid[r][c];
				List<Integer[]> neighbors = myNeighbor.getNeighbors(grid, r, c);
				String neighborsState = getNeighborsState(grid, neighbors);
				Cell newCell = newState(newGrid, curCell, neighborsState, r, c);
				newGrid[r][c] = newCell;
			}
		}
		return newGrid;
	}
	
	protected abstract String getNeighborsState(Cell[][] grid, List<Integer[]> neighbors);

	protected abstract Cell newState(Cell[][] newGrid, Cell cell, String neighborsState, int r, int c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException;
	
}	
