

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class FireController extends CardinalSimController{

	//from XML
	private static int myProbCatch;// = 50;

	public FireController(Map<String, String> map){
		super();
		System.out.println(map.get("probCatch"));
		//is prob catch a probability (double) like .5 or a number out of 100?
		myProbCatch = Integer.parseInt(map.get("probCatch"));
	}	

	@Override
	protected String getNeighborsState(ArrayList<Cell> neighbors) {
		for(Cell c: neighbors){
			if(c.toString().equals("FireCell")){
				if(new Random().nextInt(100) < myProbCatch){
					return "FireCell";
				}
				break;
			}
		}
		return "TreeCell";
	}

	@Override
	protected Cell newState(Cell cell, String hoodState)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		if(cell.toString().equals("TreeCell")){
			return makeCell(hoodState);
		}
		return makeCell("EmptyCell");
	}
	


}
