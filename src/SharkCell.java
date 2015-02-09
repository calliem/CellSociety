import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;

public class SharkCell extends AquaticCell {
	
	private static int myMaxEnergy;
	private static int myReproducingAge;
	private int myCurEnergy;
	private static Color myColor;
		
	public SharkCell(String name){
		super(name);
		myCurEnergy = myMaxEnergy;
		getShape().setFill(myColor);	 
	}

	public SharkCell(Map<String, String> params) {
		super(params);
		myColor = Color.valueOf(params.get("color"));
		myMaxEnergy = Integer.parseInt(params.get("maxEnergy"));
		myReproducingAge = Integer.parseInt(params.get("reproductionAge"));
		getShape().setFill(Color.BLACK);
		myCurEnergy = myMaxEnergy;
	}

	protected Cell instantiate(){
		return new SharkCell("SharkCell");
	}
	
	public int getEnergy(){
		return myCurEnergy;
	}
	
	public void update(){
		super.update();
		myCurEnergy--;
	}
	
	protected void swapRole(GridData data, Coordinate dest){
			if(data.getGrid()[dest.getX()][dest.getY()].toString().equals("FishCell")){
				replenishEnergy();
			}	
	}
	
	protected List<Coordinate> findEligibleNeighbors(GridData data,
			Neighbor neighbor) {
		List<Coordinate> list = getNeighbors(data, neighbor, "FishCell");
		if(list.isEmpty()){
			list = super.findEligibleNeighbors(data, neighbor);
		}
		return list;
	}
	
	public boolean isDead(){
		return myCurEnergy == 0;
	}
	
	public String toString(){
		return "SharkCell";
	}

	public void replenishEnergy() {
		myCurEnergy = myMaxEnergy;
		
	}
}
