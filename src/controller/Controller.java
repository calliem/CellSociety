package controller;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import boundary.Boundary;
import boundary.FiniteBoundary;
import neighbor.FullNeighbor;
import neighbor.HalfNeighbor;
import neighbor.Neighbor;
import cell.Cell;
import cellsociety.Strings;

//import javafx.scene.paint.Color;

public abstract class Controller {

	public static final int X_COORD = 0;
	public static final int Y_COORD = 1;

	protected Boundary myBoundary;
	protected Neighbor myNeighbor;

	public abstract Cell[][] runOneSim(Cell[][] grid)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException;

	public Controller(Map<String, String> parameters)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {

		System.out.println("list");
		for (String s: parameters.keySet()){
			System.out.println(s);
			System.out.println(parameters.get(s));
		}
		
		//this part here broke the code
		String boundary = parameters.get("boundary");
		System.out.println(boundary);
		if (boundary != null) {
			setBoundary(boundary);
		} 
		else{
			myBoundary = new FiniteBoundary(); // defaulted to FiniteBoundary
		}

		String neighbors = parameters.get("neighbor");
		System.out.println(neighbors);
		if (parameters.get("neighbor") != null) {
			setNeighbors(neighbors);
		} else{
			myNeighbor = new FullNeighbor(myBoundary); // defaulted to
														// FullNeighbor
		}
	}

	protected boolean contains(List<Integer[]> updatedCoordinates,
			Integer[] curCoordinates) {
		int[] coords = new int[curCoordinates.length];
		for (int i = 0; i < curCoordinates.length; i++) {
			coords[i] = curCoordinates[i];
		}
		for (int j = 0; j < updatedCoordinates.size(); j++) {
			int count = 0;
			for (int k = 0; k < coords.length; k++) {
				if (coords[k] == updatedCoordinates.get(j)[k]) {
					count++;
				}
			}
			if (count == coords.length) {
				return true;
			}
		}
		return false;
	}

	protected static Cell makeCell(String s) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {
		if (s.equals("SharkCell") | s.equals("FishCell"))
			return (Cell) Class.forName(Strings.CELL_PACKAGE + s).getConstructor().newInstance(s);
		else {
			return (Cell) Class.forName(Strings.CELL_PACKAGE + "Cell").getConstructor(String.class)
					.newInstance(s);
		}
	}

	private void setBoundary(String s) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {
		String boundary = s + "Boundary";
		myBoundary = (Boundary) Class.forName(Strings.BOUNDARY_PACKAGE + boundary).getConstructor()
				.newInstance();
		//myNeighbor = new HalfNeighbor(myBoundary); //is this necessary?
	}

	private void setNeighbors(String s) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {
		String neighbor = s + "Neighbor";
		System.out.println("NEIGHHHH" + neighbor);
		myNeighbor = (Neighbor) Class.forName(Strings.NEIGHBOR_PACKAGE + neighbor).getConstructor(Boundary.class)
				.newInstance(myBoundary);
		System.out.println("neighbor created");
	}

	// protected abstract String getNeighborsState(Cell[][] grid,
	// List<Integer[]> neighbors);
}
