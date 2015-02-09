import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javafx.scene.paint.Color;


public abstract class Controller {
	
	protected Boundary myBoundary = new FiniteBoundary(); //defaulted to finite boundaries
	protected Neighbor myNeighbor = new FullNeighbor(myBoundary);
	
	public abstract Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException ;
	
	protected boolean contains(List<Integer[]> updatedCoordinates,
			Integer[] curCoordinates) {
		int[] coords = new int[curCoordinates.length];
		for(int i = 0; i < curCoordinates.length; i++){
			coords[i] = curCoordinates[i];
		}
		for(int j = 0; j < updatedCoordinates.size(); j++){
			int count = 0;
			for(int k = 0; k < coords.length; k++){
				if(coords[k] == updatedCoordinates.get(j)[k]){
					count++;
				}
			}
			if(count == coords.length){
				return true;
			}
		}
		return false;
	}
	
	//-----------inefficient cell ---------
	protected static Cell makeCell(String s) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		if (s.equals("SharkCell") | s.equals("FishCell"))
			return (Cell) Class.forName(s).getConstructor().newInstance(s);
		else {//this is hardcoded but that is the actual "state" of the cell as oppposed to just its name{
			return (Cell) Class.forName("Cell").getConstructor(String.class).newInstance(s);
		}
	}
	
	/*protected void setBoundary(String boundary){
		myBoundary = --------- new reflection;
		
		String className = "LiveCell";
Class<?> currentClass = Class.forName(className);
System.out.println(currentClass.toString());
Constructor<?> constructor = currentClass.getConstructor(Integer.TYPE, Integer.TYPE);
System.out.println(constructor);
Object o = constructor.newInstance(10,10);
	}*/
	
	
	

}
