import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FireController extends SimpleController{

	private int myProbCatch;

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
		if(cell.toString().equals("TreeCell")){
			return makeCell(hoodState);
		}
		return makeCell("EmptyCell");
	}
}
