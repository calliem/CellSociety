// This entire file is part of my masterpiece.
// Kevin Delgado

package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import cell.AquaticCell;
import cell.Cell;
import cell.SharkCell;
import cellsociety.GridData;
import cellsociety.Strings;
import controller.ComplexController;

public class WatorController extends ComplexController{

	public WatorController(Map<String, String> parameters) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
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
		else if(data.curCell().toString().equals(Strings.EMPTY_CELL)){
			data.getNewGrid()[data.getRow()][data.getCol()] = makeCell(Strings.EMPTY_CELL);
		}
		else{
			((AquaticCell) data.curCell()).ageOneChronon(data, myNeighbor);
		}	
	}

	@Override
	protected List<String> typeTriage(List<String> list) {
		list.add(Strings.SHARK_CELL);
		list.add(Strings.FISH_CELL);
		list.add(Strings.EMPTY_CELL);
		return list;
	}
}
