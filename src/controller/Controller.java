package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import cell.Cell;
<<<<<<< HEAD
import neighbor.Neighbor;
import boundary.Boundary;
=======
import cellsociety.Strings;

public abstract class Controller {

	protected static final int X_COORD = 0;
	protected static final int Y_COORD = 1;

	protected Boundary myBoundary;
	protected Neighbor myNeighbor;

	public abstract Cell[][] runOneSim(Cell[][] grid) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException;

	public Controller(Map<String, String> parameters) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		String boundary = parameters.get(Strings.BOUNDARY_PARAMETER);
		if (boundary != null) {
			setBoundary(boundary);
		} else {
			myBoundary = new FiniteBoundary(); // defaulted to FiniteBoundary
		}

		String neighbors = parameters.get(Strings.NEIGHBOR_PARAMETER);
		if (neighbors != null) {
>>>>>>> 001611e161cb55179e03ea9eb8c7e639ac1ddf8c

//import javafx.scene.paint.Color;


public abstract class Controller {
/*
	public static final int X_COORD = 0;
	public static final int Y_COORD = 1;
*/
	protected Boundary myBoundary;// = new ToroidalBoundary();
	protected Neighbor myNeighbor;// = new HalfNeighbor(myBoundary);

	public abstract Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException ;
/*
	public static boolean contains(List<Integer[]> updatedCoordinates,
			Integer[] curCoordinates) {
		int[] coords = new int[curCoordinates.length];
		for(int i = 0; i < curCoordinates.length; i++){
			coords[i] = curCoordinates[i];
		}
		for(int j = 0; j < updatedCoordinates.size(); j++){
			int count = 0;
			for(int k = 0; k < coords.length; k++){
				if(coords[k] == updatedCoordinates.get(j)[k]){
					count++;
				}
			}
			if(count == coords.length){
				return true;
			}
		}
		return false;
	}*/

	//-----------inefficient cell ---------
	protected static Cell makeCell(String s) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		System.out.println("MAKECELLMETHOD: " + s);
		if (s.equals("SharkCell") | s.equals("FishCell"))
			return (Cell) Class.forName(s).getConstructor().newInstance(s);
		else {//this is hardcoded but that is the actual "state" of the cell as oppposed to just its name{
			return (Cell) Class.forName("Cell").getConstructor(String.class).newInstance(s);
		}
	}

<<<<<<< HEAD
=======
	private void setBoundary(String s) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		String boundary = s + Strings.BOUNDARY;
		myBoundary = (Boundary) Class.forName(Strings.BOUNDARY_PACKAGE + boundary)
				.getConstructor().newInstance();
	}

	private void setNeighbors(String s) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		String neighbor = s + Strings.NEIGHBOR;
		myNeighbor = (Neighbor) Class.forName(Strings.NEIGHBOR_PACKAGE + neighbor)
				.getConstructor(Boundary.class).newInstance(myBoundary);
	}
>>>>>>> 001611e161cb55179e03ea9eb8c7e639ac1ddf8c
}
