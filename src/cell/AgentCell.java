package cell;

import java.util.List;
import java.util.Map;

import neighbor.Neighbor;
import javafx.scene.paint.Color;
import controller.Controller;
import cellsociety.Coordinate;
import cellsociety.GridData;

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
		//getShape().setFill(myColor);
	}

	public AgentCell(Map<String, String> params){
		super(params);
		myColor = Color.valueOf(params.get("color"));
		mySugarMetabolism = Integer.parseInt(params.get("sugarMetabolism"));
		myVision = Integer.parseInt(params.get("vision"));
		myInitSugar = Integer.parseInt(params.get("initSugar"));
		myCurSugar = myInitSugar;
	}
	
	public void harvest(Cell cell) {
		myCurSugar-= mySugarMetabolism;
		if(myCurSugar > 0){
			myCurSugar += myGroundCell.harvest();

			myGroundCell.update();
			cell = this;
		} else {
			myGroundCell.update();
			cell = myGroundCell;
		}
	}

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
