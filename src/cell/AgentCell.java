package cell;
import java.util.List;

import controller.Controller;
import controller.Updatable;
import cellsociety.GridData;

/*
 * Talk about decision to put methods here
 */
public class AgentCell extends Cell implements Updatable{

	private GroundCell myGroundCell;
	private int mySugar;
	private int mySugarMetabolism;
	private int myVision;


	public AgentCell(GroundCell groundCell){
		myGroundCell = groundCell;
	}

	@Override
	public void ageOneChronon(GridData data) {
		// TODO Auto-generated method stub
		//List<GroundCell> elibileNeighbors = findEligibleNeighbors(data);
		//GetNeighborsWith max sugar
		Integer[] newLocation = chooseNewLocation(data);
		cellSwap(data, newLocation);
		//pick neighbor
		//go to neighbor (leaving GroundCell here)
		//replenish resources off of newDestination
		//update groundcell at new destination
		//and destination to visited list
	}

	private void cellSwap(GridData data, Integer[] newLocation) {
		if(!sameLocation(data, newLocation)){
			//ageCurrentPatch
			myGroundCell.ageOneChronon(data);
			//data.currentCell() = myGroundCell.ageOneChronon
			//LeaveCurrentPatch
			leaveCurrentPatch(data,newLocation);
			//HarvestSugarAtNewPatch
			//ageNewPatch

		}
		else{
			harvest(data.getNewGrid()[data.getRow()][data.getCol()]);

		}


	}

	private void harvest(Cell cell) {
		// TODO Auto-generated method stub
		//HarvestSugarAtCurrentPatch
		if(mySugar > 0){
			mySugar += myGroundCell.harvest();
			//ageCurrentPatch
			myGroundCell.update();
			cell = this;
		}
		else{
			myGroundCell.update();
			cell = myGroundCell;
		}
	}

	private void leaveCurrentPatch(GridData data, Integer[] newLocation) {
		// TODO Auto-generated method stub
		myGroundCell = (GroundCell) data.getGrid()[newLocation[Controller.X_COORD]][newLocation[Controller.Y_COORD]];
		harvest(data.getNewGrid()[newLocation[Controller.X_COORD]][newLocation[Controller.Y_COORD]]);
		data.getNewGrid()[newLocation[Controller.X_COORD]][newLocation[Controller.Y_COORD]] = this;
	}

	private boolean sameLocation(GridData data, Integer[] newLocation) {
		return data.getRow() == newLocation[Controller.X_COORD] && data.getCol() == newLocation[Controller.Y_COORD];
	}

	private Integer[] chooseNewLocation(GridData data) {
		List<Integer[]> eligibleNeighbors = findEligibleNeighbors(data);
		if(eligibleNeighbors == null){
			Integer[] coords = new Integer[Controller.Y_COORD + Controller.Y_COORD];
			coords[Controller.X_COORD] = data.getRow();
			coords[Controller.Y_COORD] = data.getCol();
			return coords;
		}
		return closestEligibleNeighbor(data, eligibleNeighbors);
	}

	private Integer[] closestEligibleNeighbor(GridData data,
			List<Integer[]> eligibleNeighbors) {
		double minDist = Integer.MAX_VALUE;
		Integer[] closest = null;
		for(Integer[] coords : eligibleNeighbors){
			double curDist = Math.pow(coords[Controller.X_COORD], 2) + Math.pow(coords[Controller.Y_COORD], 2); 
			if(curDist < minDist){
				minDist = curDist;
				closest = coords;
			}
		}
		return closest;
	}

	private List<Integer[]> findEligibleNeighbors(GridData data) {
		// TODO Auto-generated method stub
		return null;
	}

}
