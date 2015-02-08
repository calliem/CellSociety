import java.lang.reflect.InvocationTargetException;
import java.util.List;


public abstract class Controller {
	
	protected Boundary myBoundary = new FiniteBoundary();
	protected Neighbor myNeighbor = new FullNeighbor(myBoundary);
	
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
	
	public static Cell makeCell(String s) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		return (Cell) Class.forName(s).getConstructor().newInstance();
	}

}
