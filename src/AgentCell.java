public class AgentCell extends Cell implements Updatable{

	private GroundCell myGroundCell;
	
	public AgentCell(GroundCell groundCell){
		myGroundCell = groundCell;
	}
	
	@Override
	public void ageOneChronon(GridData data) {
		// TODO Auto-generated method stub
		//GetNeighborsWith max sugar
		//pick neighbor
		//go to neighbor (leaving GroundCell here)
		//replenish resources off of newDestination
		//update groundcell at new destination
		//and destination to visited list
	}

}
