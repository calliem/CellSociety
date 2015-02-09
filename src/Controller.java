import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

//import javafx.scene.paint.Color;

public abstract class Controller {
/*
	public static final int X_COORD = 0;
	public static final int Y_COORD = 1;
<<<<<<< HEAD
*/
	protected Boundary myBoundary = new FiniteBoundary();
	protected Neighbor myNeighbor = new FullNeighbor(myBoundary);

	public abstract Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException ;
/*
	public static boolean contains(List<Integer[]> updatedCoordinates,
=======

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

		// why are there so many throw/catches??
		String boundary = parameters.get("boundary");
		if (boundary != null) {
			setBoundary(boundary);
		} else
			myBoundary = new FiniteBoundary(); // defaulted to FiniteBoundary

		String neighbors = parameters.get("neighbors");
		if (neighbors != null) {
			setNeighbors(neighbors);
		} else
			myNeighbor = new FullNeighbor(myBoundary); // defaulted to
														// FullNeighbor
	}

	protected boolean contains(List<Integer[]> updatedCoordinates,
>>>>>>> 34117744d8d73640c88a35e5a94ed724ecbf7c73
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
	}*/

	// -----------inefficient cell ---------
	protected static Cell makeCell(String s) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {
		if (s.equals("SharkCell") | s.equals("FishCell"))
			return (Cell) Class.forName(s).getConstructor().newInstance(s);
		else {// this is hardcoded but that is the actual "state" of the cell as
				// oppposed to just its name{
			return (Cell) Class.forName("Cell").getConstructor(String.class)
					.newInstance(s);
		}
	}

	private void setBoundary(String s) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {
		String boundary = s + "Boundary";
		myBoundary = (Boundary) Class.forName(boundary).getConstructor()
				.newInstance();
		// myNeighbor = new FullNeighbor(myBoundary); //is this necessary?
	}

	private void setNeighbors(String s) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {
		String neighbor = s + "Neighbor";
		myNeighbor = (Neighbor) Class.forName(neighbor).getConstructor()
				.newInstance(myBoundary);
	}

	// protected abstract String getNeighborsState(Cell[][] grid,
	// List<Integer[]> neighbors);
}
