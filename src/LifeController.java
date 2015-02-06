
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
			//if(c != null){
				if(c.toString().equals("LiveCell")){
					count++;
				}
			//}
		}

		if (count == stayOn){
			return "two";
		}

		else if (count == bornOn){
			return "LiveCell";
		}
		return "EmptyCell";
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
			return makeCell(cell.toString());
			//return (Cell) Class.forName(cell.toString()).getConstructor().newInstance();
		}
		//return new Cell(neighborsState);
		return makeCell(neighborsState);
	}
}
