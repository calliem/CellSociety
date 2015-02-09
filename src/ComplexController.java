import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public abstract class ComplexController extends Controller{

	public ComplexController(Map<String, String> parameters){
	}

	public Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Cell[][] newGrid = new Cell[grid.length][grid[0].length];
		List<Integer[]> updatedCoordinates = new ArrayList<Integer[]>();
		List<String> triage = typeTriage(new ArrayList<String>());
		Queue<Integer[]> myCoordinates= coordinatesTriage(grid, triage);

		for(Integer[] coords : myCoordinates){
			//int row = coords[0];
			//int col = coords[1];
			GridData data = new GridData(grid, coords[Controller.X_COORD], coords[Controller.Y_COORD], newGrid, updatedCoordinates);
			//Integer[] curCoordinates = {r,c};
			if(!contains(updatedCoordinates, coords)){
				cellUpdate(data);
			}
		}
		return newGrid;
	}
	protected abstract void cellUpdate(GridData data) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException;

	protected abstract List<String> typeTriage(List<String> list);

	private Queue<Integer[]> coordinatesTriage(Cell[][] grid, List<String> strings) {
		Queue<Integer[]> masterQueue = new LinkedList<Integer[]>();
		for(String s : strings){
			List<Integer[]> typeQueue = new LinkedList<Integer[]>();
			for(int i = 0; i < grid.length; i++){
				for(int j = 0; j < grid[0].length; j++){
					String curName = grid[i][j].toString();
					Integer[] curCoords = {i,j};
					if(curName.equals(s)){
						typeQueue.add(curCoords);
					}
				}
			}
			Collections.shuffle(typeQueue);
			masterQueue.addAll(typeQueue);
		}
		return masterQueue;
	}
}