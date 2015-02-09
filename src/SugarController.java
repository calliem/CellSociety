import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class SugarController extends ComplexController{

	public SugarController(Map<String, String> parameters) {
		super(parameters);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void cellUpdate(GridData data)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		//Updatable curCell = (Updatable) grid[row][col];
		GroundCell curCell = (GroundCell) data.curCell();
		//GridData data = new GridData(grid, row, col, newGrid, updatedCoordinates);
		curCell.ageOneChronon(data, myNeighbor);
	}

	@Override
	protected List<String> typeTriage(List<String> list) {
		list.add("AgentCell");
		list.add("GroundCell");
		return list;
	}

}


