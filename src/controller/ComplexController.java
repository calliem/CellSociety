package controller;



import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import cell.Cell;
import cellsociety.Coordinate;
import cellsociety.GridData;

public abstract class ComplexController extends Controller{

	public ComplexController(Map<String, String> parameters) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		super(parameters);
	}

	public Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Cell[][] newGrid = new Cell[grid.length][grid[0].length];
		List<Coordinate> updatedCoordinates = new ArrayList<Coordinate>();
		List<String> triage = typeTriage(new ArrayList<String>());
		Queue<Coordinate> myCoordinates= coordinatesTriage(grid, triage);


		for(Coordinate coords : myCoordinates){
			GridData data = new GridData(grid, coords.getX(), coords.getY(), newGrid, updatedCoordinates);
			if(!updatedCoordinates.contains(coords)){

				cellUpdate(data);
			}
		}
		return newGrid;
	}
	
	protected abstract void cellUpdate(GridData data) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException;

	protected abstract List<String> typeTriage(List<String> list);

	private Queue<Coordinate> coordinatesTriage(Cell[][] grid, List<String> strings) {
		Queue<Coordinate> masterQueue = new LinkedList<Coordinate>();
		for(String s : strings){
			List<Coordinate> typeQueue = new LinkedList<Coordinate>();
			for(int i = 0; i < grid.length; i++){
				for(int j = 0; j < grid[0].length; j++){
					String curName = grid[i][j].toString();
					//Integer[] curCoords = {i,j};
					if(curName.equals(s)){
						typeQueue.add(new Coordinate(i,j));
					}
				}
			}
			Collections.shuffle(typeQueue);
			masterQueue.addAll(typeQueue);
		}
		return masterQueue;
	}
}