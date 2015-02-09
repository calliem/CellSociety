import java.lang.reflect.InvocationTargetException;
import java.util.List;

//import javafx.scene.paint.Color;


public abstract class Controller {

	public static final int X_COORD = 0;
	public static final int Y_COORD = 1;

	protected Boundary myBoundary = new FiniteBoundary();
	protected Neighbor myNeighbor = new HalfNeighbor(myBoundary);

	public abstract Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException ;

	protected boolean contains(List<Integer[]> updatedCoordinates,
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
	}

	//-----------inefficient cell ---------
	protected static Cell makeCell(String s) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		System.out.println("MAKECELLMETHOD: " + s);
		if (s.equals("SharkCell") | s.equals("FishCell"))
			return (Cell) Class.forName(s).getConstructor().newInstance(s);
		else {//this is hardcoded but that is the actual "state" of the cell as oppposed to just its name{
			return (Cell) Class.forName("Cell").getConstructor(String.class).newInstance(s);
		}
	}

}
