
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;



public class WatorController extends ComplexController{

	public WatorController(Map<String, String> parameters){
		super(parameters);
	}

	private void manipulateCell(Cell[][] grid, int r, int c, Cell[][] newGrid, List<Integer[]> updatedCoordinates){        
		String type = grid[r][c].toString();
		List<Integer[]> eligibleDestinations = makeEligible(grid, r, c, updatedCoordinates, type);//new ArrayList<Integer[]>();                                                                                                                                      
		if(eligibleDestinations.isEmpty()){                                                                                                      
			newGrid[r][c] = ((ReproducingCell) grid[r][c]).ageOneChronon(); 
			//grid[r][c] = ((ReproducingCell) grid[r][c]).ageOneChronon();                                                             
		}                                                                                                                                        
		else{                                                                                                                                    
			Integer[] dest = getDestination(eligibleDestinations);                                                                                   
			newGrid[dest[0]][dest[1]] = ((ReproducingCell) (grid[r][c])).ageOneChronon();                       
			//grid[dest[0]][dest[1]] = ((ReproducingCell) (grid[r][c])).ageOneChronon();                       
			Integer[] currCoord = {r,c};
			if(type.equals("SharkCell")){
				if(grid[dest[0]][dest[1]].toString().equals("FishCell")){
					((SharkCell) newGrid[dest[0]][dest[1]]).replenishEnergy();
					//((SharkCell) grid[dest[0]][dest[1]]).replenishEnergy();
				}
			}
			updatedCoordinates.add(dest);                                                                                                            
			updatedCoordinates.add(currCoord);                                                                                                       
			newGrid[r][c] = ((ReproducingCell) (grid[r][c])).reproducingResult();                                     
			//grid[r][c] = ((ReproducingCell) (grid[r][c])).reproducingResult();                                     
		}                                                                                                                                        
	}                                                                                                                                        

	private List<Integer[]> makeEligible(Cell[][] grid, int r, int c,
			List<Integer[]> updatedCoordinates, String type) {
		List<Integer[]> list = new ArrayList<Integer[]>();
		if(type.equals("FishCell")){                                                                                                             
			list = getNeighbors(grid, r, c, "EmptyCell", updatedCoordinates);                                                        
		}                                                                                                                                        
		else{                                                                                                                                    
			list = getNeighbors(grid, r, c, "FishCell", updatedCoordinates);                                                         
			if(list.isEmpty()){                                                                                                      
				list = getNeighbors(grid, r, c, "EmptyCell", updatedCoordinates);                                                        
			}                                                                                                                                        
		}                                                                                                                                        
		return list;
	}

	private List<Integer[]> getNeighbors(Cell[][] grid, int r, int c, String s, List<Integer[]> updatedCoordinates){
		List<Integer[]> list = new ArrayList<Integer[]>();
		for(int d = -1; d <= 1; d += 2){
			/*
			//REFACTOR
			if(myBoundary.findCell(grid, r+d, c) != null){
				//list.add(grid[r+d][c]);
				Integer[] currArr = new Integer[2];
				currArr[0] = r+d;
				currArr[1] = c;
				list.add(currArr);
			}
			if(myBoundary.findCell(grid, r, c+d) != null){
				//list.add(grid[r][c+d]);
				Integer[] currArr = new Integer[2];
				currArr[0] = r;
				currArr[1] = c+d;	
				list.add(currArr);
			}
			*/
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
	
	private void neighborsFromTwoDirections(Cell[][] grid, int newR, int newC, List<Integer[]> list) {
		if(myBoundary.findCell(grid, newR, newC) != null){
			Integer[] currArr = new Integer[2];
			currArr[0] = newR;
			currArr[1] = newC;
			list.add(currArr);
		}
	}
	 
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
	protected void cellUpdate(Cell[][] grid, int row, int col, Cell[][] newGrid, List<Integer[]> updatedCoordinates) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Cell curCell = grid[row][col];
		if(deadShark(curCell)){
			newGrid[row][col] = ((SharkCell) curCell).reproducingResult();
		}
		else if(curCell.toString().equals("EmptyCell")){
			newGrid[row][col] = makeCell("EmptyCell");
		}
		else{
			manipulateCell(grid, row, col, newGrid, updatedCoordinates); 
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
