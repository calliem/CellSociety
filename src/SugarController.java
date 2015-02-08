import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class SugarController extends ComplexController{

	public SugarController(Map<String, String> parameters) {
		super(parameters);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void cellUpdate(Cell[][] grid, int row, int col,
			Cell[][] newGrid, List<Integer[]> updatedCoordinates)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		// TODO Auto-generated method stub
		//Updatable curCell = (Updatable) grid[row][col];
		Updatable curCell = (Updatable) data.curCell();
		GridData data = new GridData(grid, row, col, newGrid, updatedCoordinates);
		curCell.ageOneChronon(data);
	}

	@Override
	protected List<String> typeTriage(List<String> list) {
		list.add("AgentCell");
		list.add("GroundCell");
		return list;
	}

}


