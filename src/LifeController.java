import java.util.ArrayList;


public class LifeController extends SimController{

	@Override
	/**
	 * Determines whether the surrounding eight cells produce  a live condition,
	 * dead condition, or in the case of two, a condition dependent upon the cell's
	 * previous condition.
	 */
	protected String getNeighborsState(ArrayList<Cell> neighbors) {
		int count = 0;
		for(Cell c: neighbors){
			if(c.myLabel.equals("live")){
				count++;
			}
		}
		if (count == 2){
			return "two";
		}
		else if (count == 3){
			return "live";
		}
		return "empty";
	}

	
	@Override
	/**
	 * Makes a new Cell based on the cell's previous state and
	 * the state of its neighbors
	 */
	protected Cell newState(Cell cell, String neighborsState) {
		if(neighborsState.equals("two")){
			return new Cell(cell.myLabel);
		}
		return new Cell(neighborsState);
	}
	
}
