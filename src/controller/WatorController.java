package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import cell.AquaticCell;
import cell.Cell;
import cell.SharkCell;
import cellsociety.GridData;
import controller.ComplexController;

public class WatorController extends ComplexController{

	public WatorController(Map<String, String> parameters){
		super(parameters);
	}

	private boolean deadShark(Cell cell) {
		if(cell instanceof SharkCell){
			return ((SharkCell) cell).isDead();
		}
		return false;
	}

	@Override
	protected void cellUpdate(GridData data) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		if(deadShark(data.curCell())){
			data.getNewGrid()[data.getRow()][data.getCol()] = ((SharkCell) data.curCell()).reproducingResult();
		}
		else if(data.curCell().toString().equals("EmptyCell")){
			data.getNewGrid()[data.getRow()][data.getCol()] = makeCell("EmptyCell");
		}
		else{
			((AquaticCell) data.curCell()).ageOneChronon(data, myNeighbor);
		}	
	}

	@Override
	protected List<String> typeTriage(List<String> list) {
		list.add("SharkCell");
		list.add("FishCell");
		list.add("EmptyCell");
		return list;
	}
}
