package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cell.Cell;
import cellsociety.Coordinate;

public class FireController extends SimpleController{

	private int myProbCatch;

<<<<<<< HEAD
	public FireController(Map<String, String> map){
		super();
		myProbCatch = Integer.parseInt(map.get("probCatch"));
	}	

	@Override
	protected String getNeighborsState(Cell[][] grid, List<Coordinate> neighbors) {
		for(Coordinate coords: neighbors){
			if(grid[coords.getX()][coords.getY()].toString().equals("FireCell")){
				if(new Random().nextInt(100) < myProbCatch){
					return "FireCell";
=======
	public FireController(Map<String, String> parameters) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		super(parameters);
		myProbCatch = Integer.parseInt(parameters.get(Strings.CATCH_PROBABILITY));
	}	

	@Override
	protected String getNeighborsState(Cell[][] grid, List<Integer[]> neighbors) {
		for(Integer[] coords: neighbors){
			if(grid[coords[Controller.X_COORD]][coords[Controller.Y_COORD]].toString().equals(Strings.FIRE_CELL)){
				if(new Random().nextInt(100) < myProbCatch){
					return Strings.FIRE_CELL;
>>>>>>> 001611e161cb55179e03ea9eb8c7e639ac1ddf8c
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
<<<<<<< HEAD
		if(cell.toString().equals("TreeCell")){
=======
		if(cell.toString().equals(Strings.TREE_CELL)){
>>>>>>> 001611e161cb55179e03ea9eb8c7e639ac1ddf8c
			return makeCell(hoodState);
		}
		return makeCell("EmptyCell");
	}
}
