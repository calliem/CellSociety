import java.util.List;
import java.util.Map;


public class GroundCell extends Cell implements Updatable{

	private PatchCell myPatch;
	private AgentCell myAgent;
	
	public GroundCell(String name){
		super(name);
		
	}
	
	public GroundCell(Map<String, String> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}


	@Override
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
	}

	private void cellSwap(GridData data, Coordinate newLocation, Neighbor neighbor) {
		if(!data.sameLocation(newLocation)){
			//ageCurrentPatch
			myPatch.update();
			//data.currentCell() = myGroundCell.ageOneChronon
			//LeaveCurrentPatch
			leaveCurrentPatch(data,newLocation);
			//HarvestSugarAtNewPatch
			//ageNewPatch

		}
		else{
			myAgent.harvest(data.getNewGrid()[data.getRow()][data.getCol()]);
		}
	}
	
/*
	private void harvest(Cell cell) {
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
*/
	
	private void leaveCurrentPatch(GridData data, Coordinate newLocation) {
		// TODO Auto-generated method stub
		//myPatch = (PatchCell) data.getGrid()[newLocation.getX()][newLocation.getY()];
		myAgent.harvest(data.getNewGrid()[newLocation.getX()][newLocation.getY()]);
		((GroundCell) data.getNewGrid()[newLocation.getX()][newLocation.getY()]).setAgent(myAgent);
		myAgent = null;
	}
/*
	private boolean sameLocation(GridData data, Coordinate newLocation) {
		return data.getRow() == newLocation.getX() && data.getCol() == newLocation.getY();
	}
*/
	private Coordinate chooseNewLocation(GridData data, Neighbor neighbor) {
		List<Coordinate> eligibleNeighbors = myAgent.findEligibleNeighbors(data, neighbor);
		if(eligibleNeighbors == null){
			return new Coordinate(data.getRow(), data.getCol());
		}
		return closestEligibleNeighbor(data, eligibleNeighbors);
	}

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
/*
	private List<Coordinate> findEligibleNeighbors(GridData data, Neighbor neighbor) {
		// TODO Auto-generated method stub
		List<Coordinate> neighbors = neighbor.getNeighbors(data.getGrid(), data.getRow(), data.getCol(), myAgent.getVision());
		for(Coordinate coords : neighbors){
			if(data.getList().contains(coords) || !emptyGround(data, coords)){
				neighbors.remove(coords);
			}
		}
		return neighbors;
	}
*/
/*
	private boolean emptyGround(GridData data, Coordinate coords) {
		return ((GroundCell) data.curCell()).getAgent() == null;
	}
*/
	public PatchCell getPatch() {
		return myPatch;
	}

	public void setPatch(PatchCell patch) {
		myPatch = patch;
	}

	public AgentCell getAgent() {
		return myAgent;
	}

	public void setAgent(AgentCell agent) {
		myAgent = agent;
	}



}
