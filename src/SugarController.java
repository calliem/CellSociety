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
		GroundCell curCell = (GroundCell) data.curCell();
		curCell.ageOneChronon(data, myNeighbor);
	}

	@Override
	protected List<String> typeTriage(List<String> list) {
		list.add("GroundCell");
		return list;
	}

}


