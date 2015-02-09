import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;

/*
 * Talk about decision to put methods here
 */
public class AgentCell extends Cell {//implements Updatable{

	private PatchCell myGroundCell;
	private int myCurSugar;
	private static int myInitSugar;
	private static int mySugarMetabolism;
	private static int myVision;
	private static Color myColor;


	public AgentCell(String name, PatchCell groundCell){
		super(name);
		myCurSugar = myInitSugar;
		myGroundCell = groundCell;
		getShape().setFill(myColor);
	}

	public AgentCell(Map<String, String> params){
		super(params);
		myColor = Color.valueOf(params.get("color"));
		mySugarMetabolism = Integer.parseInt(params.get("sugarMetabolism"));
		myVision = Integer.parseInt(params.get("vision"));
		myInitSugar = Integer.parseInt(params.get("initSugar"));
		myCurSugar = myInitSugar;
	}

	//@Override
	/*
	public void ageOneChronon(GridData data, Neighbor neighbor) {
		// TODO Auto-generated method stub
		//List<GroundCell> elibileNeighbors = findEligibleNeighbors(data);
		//GetNeighborsWith max sugar
		Coordinate newLocation = chooseNewLocation(data, neighbor);
		cellSwap(data, newLocation, neighbor);
		//pick neighbor
		//go to neighbor (leaving GroundCell here)
		//replenish resources off of newDestination
		//update groundcell at new destination
		//and destination to visited list
	}*/
/*
	private void cellSwap(GridData data, Coordinate newLocation, Neighbor neighbor) {
		if(!sameLocation(data, newLocation)){
			//ageCurrentPatch
			myGroundCell.ageOneChronon(data, neighbor);
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
*/
	
	public void harvest(Cell cell) {
		// TODO Auto-generated method stub
		//HarvestSugarAtCurrentPatch
		myCurSugar-= mySugarMetabolism;
		if(myCurSugar > 0){
			myCurSugar += myGroundCell.harvest();
			//ageCurrentPatch
			myGroundCell.update();
			cell = this;
		}
		else{
			myGroundCell.update();
			cell = myGroundCell;
		}
	}
/*
	private void leaveCurrentPatch(GridData data, Coordinate newLocation) {
		// TODO Auto-generated method stub
		myGroundCell = (PatchCell) data.getGrid()[newLocation.getX()][newLocation.getY()];
		harvest(data.getNewGrid()[newLocation.getX()][newLocation.getY()]);
		data.getNewGrid()[newLocation.getX()][newLocation.getY()] = this;
	}

	private boolean sameLocation(GridData data, Coordinate newLocation) {
		return data.getRow() == newLocation.getX() && data.getCol() == newLocation.getY();
	}

	private Coordinate chooseNewLocation(GridData data, Neighbor neighbor) {
		List<Coordinate> eligibleNeighbors = findEligibleNeighbors(data, neighbor);
		if(eligibleNeighbors == null){
			return new Coordinate(data.getRow(), data.getCol());
		}
		return closestEligibleNeighbor(data, eligibleNeighbors);
	}
	*/
/*
	private Coordinate closestEligibleNeighbor(GridData data,
			List<Coordinate> eligibleNeighbors) {
		double minDist = Integer.MAX_VALUE;
		Coordinate closest = null;
		for(Coordinate coords : eligibleNeighbors){
			double curDist = Math.pow(coords.getX() - data.getRow(), 2) + Math.pow(coords.getY() - data.getCol(), 2); 
			if(curDist < minDist){
				minDist = curDist;
				closest = coords;
			}
		}
		return closest;
	}
*/
	public List<Coordinate> findEligibleNeighbors(GridData data, Neighbor neighbor) {
		// TODO Auto-generated method stub
		List<Coordinate> neighbors = neighbor.getNeighbors(data.getGrid(), data.getRow(), data.getCol(), myVision);
		for(Coordinate coords : neighbors){
			if(data.getList().contains(coords) || !emptyGround(data, coords)){
				neighbors.remove(coords);
			}
		}
		return neighbors;
	}
	
	private boolean emptyGround(GridData data, Coordinate coords) {
		return ((GroundCell) data.curCell()).getAgent() == null;
	}

}
