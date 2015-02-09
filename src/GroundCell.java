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
		Coordinate newLocation = chooseNewLocation(data, neighbor);
		cellSwap(data, newLocation, neighbor);
	}

	private void cellSwap(GridData data, Coordinate newLocation, Neighbor neighbor) {
		if(!data.sameLocation(newLocation)){
			myPatch.update();
			leaveCurrentPatch(data,newLocation);
		}
		else{
			myAgent.harvest(data.getNewGrid()[data.getRow()][data.getCol()]);
		}
	}
	
	private void leaveCurrentPatch(GridData data, Coordinate newLocation) {
		myAgent.harvest(data.getNewGrid()[newLocation.getX()][newLocation.getY()]);
		((GroundCell) data.getNewGrid()[newLocation.getX()][newLocation.getY()]).setAgent(myAgent);
		myAgent = null;
	}

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
