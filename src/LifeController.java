

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;


public class LifeController extends SimController{

	//{S}
	//private static ArrayList<Integer> stayOn;
	//{B}
	//private static ArrayList<Integer> bornOn;
	
	
	//can we possibly make the simcontroller an (abstract) class that has this contstructor for all? --> will be passing in a hashmap for all 
	public LifeController(Map<String, String>parameters){
	}
	
	@Override
	/**
	 * Determines whether the surrounding eight cells produce  a live condition,
	 * dead condition, or in the case of two, a condition dependent upon the cell's
	 * previous condition.
	 */
	protected String getNeighborsState(ArrayList<Cell> neighbors) {
		int count = 0;
		for(Cell c: neighbors){

			if(c.toString().equals("LiveCell")){
				count++;
			}
		}
		//if(count = {S} - {B})
		//if(count = stayNotBorn(stayOn, bornOn)
		if (count == 2){
			return "two";
		}
		//else if(count == {S}&{B})
		//else if(count == stayAndBorn(stayOn, bornOn)
		else if (count == 3){
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
