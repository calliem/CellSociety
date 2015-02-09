package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cell.Cell;
import cellsociety.Strings;

public class SegregationController extends SimpleController{
	private double myHappyFraction;
	private ArrayList<Integer[]> myEmptyList;
	private ArrayList<Integer[]> updatedList;

	public SegregationController(Map<String, String> parameters) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		//this is duplicated :( could this be put into the super class constructor and then called with super();
		super(parameters);

		myHappyFraction = Double.parseDouble(parameters.get(Strings.HAPPY_PROBABILITY));
	}

	@Override
	public Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		updatedList = new ArrayList<Integer[]>();
		makeEmptyList(grid);
		return super.runOneSim(grid);
	}

	private void makeEmptyList(Cell[][] grid) {
		myEmptyList = new ArrayList<Integer[]>();
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[0].length; c++){
				String curString = grid[r][c].toString();
				if(curString.toString().equals(Strings.EMPTY_CELL)){
					Integer[] coords = {r,c};
					myEmptyList.add(coords);
				}
			}
		}
	}

	@Override
	protected String getNeighborsState(Cell[][] grid, List<Integer[]> neighbors) {
		double blueCount = 0;
		double redCount = 0;
		for(Integer[] coords: neighbors){
			if(grid[coords[Controller.X_COORD]][coords[Controller.Y_COORD]].toString().toString().equals(Strings.BLUE_CELL)){
				blueCount++;
			}
			if(grid[coords[Controller.X_COORD]][coords[Controller.Y_COORD]].toString().toString().equals(Strings.RED_CELL)){
				redCount++;
			}
		}
		return selectNeighborsState(blueCount, redCount);
	}

	private String selectNeighborsState(double bC, double rC) {
		double total = bC + rC;
		if(total == 0){
			return Strings.NONE;
		}
		boolean blueHappy = bC/total > myHappyFraction;
		boolean redHappy = rC/total > myHappyFraction;
		if(blueHappy){
			if(redHappy){
				return Strings.BOTH;
			}
			return Strings.BLUE_CELL;
		}
		if(redHappy){
			return Strings.RED_CELL;
		}
		return Strings.NONE;
	}

	@Override
	protected Cell newState(Cell[][] newGrid, Cell cell, String neighborsState, int r, int c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Integer[] curCoord = {r,c};
		if(!contains(updatedList, curCoord)){
			Random random = new Random();
			if(cell.toString().equals(neighborsState) || neighborsState.equals(Strings.BOTH)){
				return makeCell(cell.toString());
			}
			else if(!cell.toString().equals(Strings.EMPTY_CELL)){
				Integer[] randCoord = myEmptyList.remove(random.nextInt(myEmptyList.size()));
				newGrid[randCoord[0]][randCoord[1]] = makeCell(cell.toString());
				updatedList.add(randCoord);
				Integer[] newCoord = {r,c};
				myEmptyList.add(newCoord);
			}
			return makeCell(Strings.EMPTY_CELL);
		}
		return newGrid[r][c];
	}
}