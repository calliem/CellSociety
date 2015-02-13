package cell;

import java.util.List;
import java.util.Map;

import neighbor.Neighbor;
import cellsociety.Coordinate;
import cellsociety.GridData;
import cellsociety.Strings;
import javafx.scene.paint.Color;

public class SharkCell extends AquaticCell {
	
	private static int myMaxEnergy;
	private int myCurEnergy;
		
	public SharkCell(String name){
		super(name);
		myCurEnergy = myMaxEnergy;
	}

	
	public SharkCell(Map<String, String> params) {
		super(params);
		myMaxEnergy = Integer.parseInt(params.get("maxEnergy"));
	}

	public int getEnergy(){
		return myCurEnergy;
	}
	
	public void update(){
		super.update();
		myCurEnergy--;
	}
	
	protected void swapRole(GridData data, Coordinate dest){
			if(data.getGrid()[dest.getX()][dest.getY()].toString().equals(Strings.FISH_CELL)){
				replenishEnergy();
			}	
	}
	
	protected List<Coordinate> findEligibleNeighbors(GridData data,
			Neighbor neighbor) {
		List<Coordinate> list = getNeighbors(data, neighbor, Strings.FISH_CELL);
		if(list.isEmpty()){
			list = super.findEligibleNeighbors(data, neighbor);
		}
		return list;
	}
	
	protected Cell instantiate(){
		return new SharkCell(Strings.SHARK_CELL);
	}
	
	public boolean isDead(){
		return myCurEnergy == 0;
	}
	
	public String toString(){
		return Strings.SHARK_CELL;
	}

	public void replenishEnergy() {
		myCurEnergy = myMaxEnergy;
		
	}
}
