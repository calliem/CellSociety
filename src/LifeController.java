import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;

public class LifeController extends SimpleController{

	//{S}
	//private static ArrayList<Integer> stayOn;
	//{B}

	private int stayOn;
	private int bornOn;
	 

	public LifeController(Map<String, String>parameters) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		super(parameters);
		/*//why are there so many throw/catches??
		//this is duplicated :(
		String boundary = parameters.get("boundary");
		if (boundary != null){
			setBoundary(boundary);
		}*/

		
		stayOn = Integer.parseInt(parameters.get("stayOn"));
		bornOn = Integer.parseInt(parameters.get("bornOn"));
	}

	protected String getNeighborsState(Cell[][] grid, List<Integer[]> neighbors) {
		int count = 0;
		for(Integer[] coords: neighbors){
			//if(c != null){
				if(grid[coords[Controller.X_COORD]][coords[Controller.Y_COORD]].toString().equals("LiveCell")){
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

	@Override
	protected Cell newState(Cell[][] newGrid, Cell cell, String neighborsState,
			int r, int c) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {
		if(neighborsState.equals("two")){
			//return new Cell(cell.myLabel);
			System.out.println("makeCell" + cell.toString());
			return makeCell(cell.toString());
			//return (Cell) Class.forName(cell.toString()).getConstructor().newInstance();
		}
		//return new Cell(neighborsState);
		return makeCell(neighborsState);
	}


}
