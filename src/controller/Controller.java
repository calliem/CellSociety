package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import boundary.Boundary;
import boundary.FiniteBoundary;
import neighbor.Neighbor;
import cell.Cell;
import cellsociety.Strings;
import neighbor.FullNeighbor;


public abstract class Controller {

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
			setNeighbors(neighbors);
		}
		else{
			myNeighbor = new FullNeighbor(myBoundary);
		}
	}

	protected static Cell makeCell(String s) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		System.out.println("MAKECELLMETHOD: " + s);
		if (s.equals(Strings.SHARK_CELL) | s.equals(Strings.FISH_CELL))
			return (Cell) Class.forName(s).getConstructor().newInstance(s);
		else {
			return (Cell) Class.forName(Strings.CELL_PACKAGE + Strings.CELL).getConstructor(String.class).newInstance(s);
		}
	}


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

}
