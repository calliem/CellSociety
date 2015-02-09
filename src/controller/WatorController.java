package controller;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cell.Cell;
import cell.ReproducingCell;
import cell.SharkCell;
import cellsociety.GridData;
import cellsociety.Strings;

public class WatorController extends ComplexController{

	public WatorController(Map<String, String> parameters) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		super(parameters);
	}

	private void manipulateCell(GridData data){        
		String type = data.curCell().toString();
		List<Integer[]> eligibleDestinations = makeEligible(data, type);//new ArrayList<Integer[]>();                                                                                                                                      
		if(eligibleDestinations.isEmpty()){                                                                                                      
			data.getNewGrid()[data.getRow()][data.getCol()] = ((ReproducingCell) data.curCell()).ageOneChronon(); 
			//grid[r][c] = ((ReproducingCell) grid[r][c]).ageOneChronon();                                                             
		}                                                                                                                                        
		else{                                                                                                                                    
			Integer[] dest = getDestination(eligibleDestinations);                                                                                   
			data.getNewGrid()[dest[0]][dest[1]] = ((ReproducingCell) (data.curCell())).ageOneChronon();                       
			//grid[dest[0]][dest[1]] = ((ReproducingCell) (grid[r][c])).ageOneChronon();                       
			Integer[] currCoord = data.curCoord();
			if(type.equals(Strings.SHARK_CELL)){
				if(data.getGrid()[dest[0]][dest[1]].toString().equals(Strings.FISH_CELL)){
					((SharkCell) data.getNewGrid()[dest[0]][dest[1]]).replenishEnergy();
					//((SharkCell) grid[dest[0]][dest[1]]).replenishEnergy();
				}
			}
			data.getList().add(dest);                                                                                                            
			data.getList().add(currCoord);                                                                                                       
			data.getNewGrid()[data.getRow()][data.getCol()] = ((ReproducingCell) data.curCell()).reproducingResult();                                     
			//grid[r][c] = ((ReproducingCell) (grid[r][c])).reproducingResult();                                     
		}                                                                                                                                        
	}                                                                                                                                        

	/*
	private List<Integer[]> makeEligible(GridData data, String type) {
		List<Integer[]> list = new ArrayList<Integer[]>();
		if(type.equals(Strings.FISH_CELL)){                                                                                                             
			list = getNeighbors(data, Strings.EMPTY_CELL);                                                        
		}                                                                                                                                        
		else{                                                                                                                                    
			list = getNeighbors(data, Strings.FISH_CELL);                                                         
			if(list.isEmpty()){                                                                                                      
				list = getNeighbors(data, Strings.EMPTY_CELL);                                                        
			}                                                                                                                                        
		}                                                                                                                                        
		return list;
	}

	private List<Integer[]> getNeighbors(Cell[][] grid, int r, int c, String s, List<Integer[]> updatedCoordinates){
		List<Integer[]> list = new ArrayList<Integer[]>();
		for(int d = -1; d <= 1; d += 2){

			neighborsFromTwoDirections(grid, r+d, c, list);
			neighborsFromTwoDirections(grid, r, c+d, list);
			 
		}
		for(Integer[] i : new ArrayList<Integer[]>(list)){
			if (!(grid[i[0]][i[1]].toString().equals(s)) || contains(updatedCoordinates, i)){
				list.remove(i);
			}
		}
		return list;
	}
	
	*/
	
	private List<Integer[]> makeEligible(GridData data, String type) {
		List<Integer[]> list = new ArrayList<Integer[]>();
		if(type.equals(Strings.FISH_CELL)){                                                                                                             
			list = getNeighbors(data, Strings.EMPTY_CELL);                                                        
		}                                                                                                                                        
		else{                                                                                                                                    
			list = getNeighbors(data, Strings.FISH_CELL);                                                         
			if(list.isEmpty()){                                                                                                      
				list = getNeighbors(data, Strings.EMPTY_CELL);                                                        
			}                                                                                                                                        
		}                                                                                                                                        
		return list;
	}

	private List<Integer[]> getNeighbors(GridData data, String s){
		List<Integer[]> list = myNeighbor.getNeighbors(data.getGrid(), data.getRow(), data.getCol());
		/*for(int d = -1; d <= 1; d += 2){

			neighborsFromTwoDirections(grid, r+d, c, list);
			neighborsFromTwoDirections(grid, r, c+d, list);
			 
		}*/
		for(Integer[] i : new ArrayList<Integer[]>(list)){
			if (!(data.getGrid()[i[0]][i[1]].toString().equals(s)) || contains(data.getList(), i)){
				list.remove(i);
			}
		}
		return list;
	}
	/*
	private void neighborsFromTwoDirections(Cell[][] grid, int newR, int newC, List<Integer[]> list) {
		if(myBoundary.findCell(grid, newR, newC) != null){
			Integer[] currArr = new Integer[2];
			currArr[0] = newR;
			currArr[1] = newC;
			list.add(currArr);
		}
	}
	 */
	private Integer[] getDestination(List<Integer[]> eligibleDestinations) {
		return eligibleDestinations.get(new Random().nextInt(eligibleDestinations.size()));
	}

	private boolean deadShark(Cell cell) {
		if(cell instanceof SharkCell){
			return ((SharkCell) cell).isDead();
		}
		return false;
	}

	@Override
	protected void cellUpdate(GridData data) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		//Cell curCell = data
		if(deadShark(data.curCell())){
			data.getNewGrid()[data.getRow()][data.getCol()] = ((SharkCell) data.curCell()).reproducingResult();
		}
		else if(data.curCell().toString().equals(Strings.EMPTY_CELL)){
			data.getNewGrid()[data.getRow()][data.getCol()] = makeCell(Strings.EMPTY_CELL);
		}
		else{
			manipulateCell(data); 
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
