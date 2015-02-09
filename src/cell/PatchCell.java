package cell;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;

public class PatchCell extends Cell {// implements Updatable {

	private static int mySugarGrowBackRate;
	private static int mySugarGrowBackInterval;
	//private static int myInitSugar;
	private static int myMaxSugar;
	private int myCurSugar;
	private int myCurInterval;
	private Color myColor;
	
	public PatchCell(String name){
		super(name);
		myCurSugar = myMaxSugar;
		myCurInterval = 0;
		//getShape().setFill(myColor);
	}
	
	public PatchCell(Map<String, String> params) {
		super(params);
		// TODO Auto-generated constructor stub
		myColor = Color.valueOf(params.get("color"));
		mySugarGrowBackRate = Integer.parseInt(params.get("sugarGrowBackRate"));
		mySugarGrowBackInterval = Integer.parseInt(params.get("sugarGrowBackInterval"));
		//myInitSugar = Integer.parseInt(params.get("initSugar"));
		myMaxSugar = Integer.parseInt(params.get("maxSugar"));
		myCurSugar = myMaxSugar;
		myCurInterval = 0;
	}
	

	
	//public GroundCell()
/*
	@Override
	public void ageOneChronon(GridData data, Neighbor neighbor) {
		update();
		//Cell curCell = data.curCell();
		data.updateLocation(this);// = this;
		//data.getNewGrid()[data.getRow()][data.getCol()] = this;
	}
	*/
	public void update(){
		myCurInterval++;
		if(mySugarGrowBackInterval == myCurInterval){
			myCurSugar = Math.min(myCurSugar+mySugarGrowBackRate, myMaxSugar);
		}
		
	}
	public int harvest(){
		int oldSugar = myCurSugar;
		myCurSugar = 0;
		return oldSugar;
	}
}


