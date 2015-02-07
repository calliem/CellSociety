
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;


public class LifeController extends SimController{

	//{S}
	//private static ArrayList<Integer> stayOn;
	//{B}

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
			return Strings.TWO;
		}
		else if (count == bornOn){
			return Strings.LIVE_CELL; //MAKE THIS THE INSTANCE AND ALWAYS MAKE A CELL INSTEAD
		}
		return Strings.EMPTY_CELL; //this will go to Empty but java reflections calls this and it instead needs to go to Cell (and maybe include the name that it is empty.....)

	}




	//@Override
	/**
	 * Makes a new Cell based on the cell's previous state and
	 * the state of its neighbors


	protected Cell newState(Cell cell, String neighborsState) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		if(neighborsState.equals("two")){
			//return new Cell(cell.myLabel);
			return makeCell(cell.toString());
			//return (Cell) Class.forName(cell.toString()).getConstructor().newInstance();
		}
		//return new Cell(neighborsState);
		return makeCell(neighborsState);
		//(Cell) Class.forName(neighborsState).getConstructor().newInstance();
	}

	String className = "LiveCell";
	Class<?> currentClass = Class.forName(className);
	System.out.println(currentClass.toString());
	Constructor<?> constructor = currentClass.getConstructor(Integer.TYPE, Integer.TYPE);
	System.out.println(constructor);
	Object o = constructor.newInstance(10,10);
	 */


	@Override
	protected Cell newState(Cell[][] newGrid, Cell cell, String neighborsState,
			int r, int c) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {
		if(neighborsState.equals("two")){
			//return new Cell(cell.myLabel);
			System.out.println("cell statename" + cell.stateName());
			return makeCell(Strings.CELL, cell.stateName());
			//return (Cell) Class.forName(cell.toString()).getConstructor().newInstance();
		}
		//return new Cell(neighborsState);
		System.out.println("newState(): neighborsState" + neighborsState);
		return makeCell(Strings.CELL, neighborsState);
	}
}
