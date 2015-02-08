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
		
	}

	@Override
	protected List<String> typeTriage(List<String> list) {
		list.add("AgentCell");
		list.add("GroundCell");
		return list;
	}

}
