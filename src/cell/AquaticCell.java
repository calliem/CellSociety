package cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import neighbor.Neighbor;
import cellsociety.Coordinate;
import cellsociety.GridData;
import javafx.scene.paint.Color;

public abstract class AquaticCell extends Cell {
	
	private int myCurAge;
	private static int myReproducingAge;
	
	public AquaticCell(String name){
		super(name);
		myCurAge = 0;
	}
	
	public AquaticCell(Map<String, String> params) {
		super(params);
		myReproducingAge = Integer.parseInt(params.get("reproductionAge"));
		//getShape().setFill(Color.BLACK);
		myCurAge = 0;
	}
	
	public Cell reproducingResult() {
		if(myCurAge >= myReproducingAge){
			myCurAge = 0;
			return instantiate();
		}
		else{
			return new Cell("EmptyCell");
		}
	}

	public void update(){
		myCurAge++;
	}
	
	protected void cellSwap(GridData data, Coordinate newLocation, Neighbor neighbor){
		if(!data.sameLocation(newLocation)){
			update();
			swapRole(data, newLocation);
			data.getNewGrid()[newLocation.getX()][newLocation.getY()] = this;
			data.getList().add(newLocation);
			data.getList().add(data.curCoord());
			data.getNewGrid()[data.getRow()][data.getCol()] = ((AquaticCell) data.curCell()).reproducingResult();                 
		}
		else{
			update();
			data.getNewGrid()[data.getRow()][data.getCol()] = this;
		}
	}
	
	public void ageOneChronon(GridData data, Neighbor neighbor){
		Coordinate newLocation = chooseNewLocation(data, neighbor);
		cellSwap(data, newLocation, neighbor);
	}
	
	private Coordinate chooseNewLocation(GridData data, Neighbor neighbor) {
		List<Coordinate> eligibleNeighbors = findEligibleNeighbors(data, neighbor);
		if(eligibleNeighbors.isEmpty()){
			return data.curCoord();
		}
		int rand = new Random().nextInt(eligibleNeighbors.size());
		return eligibleNeighbors.get(rand);
	}

	protected List<Coordinate> findEligibleNeighbors(GridData data,
			Neighbor neighbor) {
		return getNeighbors(data, neighbor, "EmptyCell");
	}
	
	protected List<Coordinate> getNeighbors(GridData data, Neighbor neighbor, String s){
		List<Coordinate> list = neighbor.getNeighbors(data.getGrid(), data.getRow(), data.getCol());
		for(Coordinate c : new ArrayList<Coordinate>(list)){
			if (!(data.getGrid()[c.getX()][c.getY()].toString().equals(s)) || data.getList().contains(c)){
				list.remove(c);
			}
		}
		return list;
	}

	protected abstract void swapRole(GridData data, Coordinate dest);
	
	protected abstract Cell instantiate();

}
