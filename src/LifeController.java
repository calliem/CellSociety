import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;


public class LifeController extends SimController{

	private int stayOn;
	private int bornOn;
	 
	public LifeController(Map<String, String>parameters){
		stayOn = Integer.parseInt(parameters.get("stayOn"));
		bornOn = Integer.parseInt(parameters.get("bornOn"));
	}
	
	protected String getNeighborsState(ArrayList<Cell> neighbors) {
		int count = 0;
		for(Cell c: neighbors){
			System.out.println("cell" + c.toString());

			if(c.toString().equals(Strings.LIVE_CELL)){
				count++;
			}
		}

		if (count == stayOn){
			return "two";
		}

		else if (count == bornOn){
			return Strings.LIVE_CELL; //MAKE THIS THE INSTANCE AND ALWAYS MAKE A CELL INSTEAD
		}
		else
			return Strings.EMPTY_CELL; //this will go to Empty but java reflections calls this and it instead needs to go to Cell (and maybe include the name that it is empty.....)
	}

	@Override
	protected Cell newState(Cell[][] newGrid, Cell cell, String neighborsState,
			int r, int c) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {
		if(neighborsState.equals("two")){
			//return new Cell(cell.myLabel);
			System.out.println("cell statename" + cell.stateName());
			return makeCell(cell.stateName());
			//return (Cell) Class.forName(cell.toString()).getConstructor().newInstance();
		}
		//return new Cell(neighborsState);
		System.out.println("newState(): neighborsState" + neighborsState);
		return makeCell(neighborsState);
	}
}
