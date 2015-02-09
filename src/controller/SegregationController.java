package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cell.Cell;
import cellsociety.Coordinate;

public class SegregationController extends SimpleController{
	private double myHappyFraction;
	private ArrayList<Coordinate> myEmptyList;
	private ArrayList<Coordinate> updatedList;

	public SegregationController(Map<String, String> parameters){
		myHappyFraction = Double.parseDouble(parameters.get("probHappy"));
	}

	@Override
	public Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		updatedList = new ArrayList<Coordinate>();
		makeEmptyList(grid);
		return super.runOneSim(grid);

	}

	private void makeEmptyList(Cell[][] grid) {
		myEmptyList = new ArrayList<Coordinate>();
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[0].length; c++){
				String curString = grid[r][c].toString();
				if(curString.toString().equals("EmptyCell")){
					myEmptyList.add(new Coordinate(r,c));
				}
			}
		}
	}

	@Override
	protected String getNeighborsState(Cell[][] grid, List<Coordinate> neighbors) {
		double blueCount = 0;
		double redCount = 0;
		for(Coordinate coords: neighbors){
			if(grid[coords.getX()][coords.getY()].toString().toString().equals("BlueCell")){
				blueCount++;
			}
			if(grid[coords.getX()][coords.getY()].toString().toString().equals("RedCell")){
				redCount++;
			}
		}
		return selectNeighborsState(blueCount, redCount);
	}

	private String selectNeighborsState(double bC, double rC) {
		double total = bC + rC;
		if(total == 0){
			return "none";
		}
		boolean blueHappy = bC/total > myHappyFraction;
		boolean redHappy = rC/total > myHappyFraction;
		if(blueHappy){
			if(redHappy){
				return "both";
			}
			return "BlueCell";
		}
		if(redHappy){
			return "RedCell";
		}
		return "none";
	}

	@Override
	protected Cell newState(Cell[][] newGrid, Cell cell, String neighborsState, int r, int c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Coordinate curCoord = new Coordinate(r,c);
		if(!updatedList.contains(curCoord)){
			Random random = new Random();
			if(cell.toString().equals(neighborsState) || neighborsState.equals("both")){
				return makeCell(cell.toString());
			}
			else if(!cell.toString().equals("EmptyCell")){
				Coordinate randCoord = myEmptyList.remove(random.nextInt(myEmptyList.size()));
				newGrid[randCoord.getX()][randCoord.getY()] = makeCell(cell.toString());
				updatedList.add(randCoord);
				//Integer[] newCoord = {r,c};
				myEmptyList.add(curCoord);
			}
			return makeCell("EmptyCell");
		}
		return newGrid[r][c];
	}
}