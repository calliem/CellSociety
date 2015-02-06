
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;



public class WatorController extends SimController{

/*
public class WatorController extends CardinalSimController{
*/


	public WatorController(Map<String, String> parameters){
	}

	public Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Cell[][] newGrid = new Cell[grid.length][grid[0].length];
		ArrayList<Integer[]> updatedCoordinates = new ArrayList<Integer[]>();
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[0].length; c++){
				Integer[] curCoordinates = {r,c};
				if(!contains(updatedCoordinates, curCoordinates)){
					Cell curCell = grid[r][c];
					if(deadShark(curCell)){
						//newGrid[r][c] = isReproducing(curCell);
						newGrid[r][c] = ((SharkCell) curCell).reproducingResult();
					}
					else if(curCell.toString().equals("EmptyCell")){
						newGrid[r][c] = makeCell("EmptyCell");
						System.out.println("row " + r + " col " + c);
					}
					else{
						updateDestination(grid, r, c, newGrid, updatedCoordinates);
						//updateFromCell(grid, r, c, newGrid, updatedCoordinates, grid[r][c].toString()); 
					}	
				}
			}
		}
		return newGrid;
	}

	private void updateDestination(Cell[][] grid, int r, int c, Cell[][] newGrid, ArrayList<Integer[]> updatedCoordinates) {
		String state = grid[r][c].toString();
		if(state.equals("SharkCell")){
			updateFromShark(grid, r, c, newGrid, updatedCoordinates);
		}
		else{
			updateFromFish(grid, r, c, newGrid, updatedCoordinates);
		}

	}

	private void updateFromShark(Cell[][] grid, int r, int c, Cell[][] newGrid, ArrayList<Integer[]> updatedCoordinates){//,ArrayList<Cell> neighbors) {
		ArrayList<Integer[]> eligibleDestinations = getNeighbors(grid, r, c, "FishCell", updatedCoordinates);
		if(eligibleDestinations.isEmpty()){
			eligibleDestinations = getNeighbors(grid, r, c, "EmptyCell", updatedCoordinates);
		}
		if(eligibleDestinations.isEmpty()){
			newGrid[r][c] = ((ReproducingCell) grid[r][c]).ageOneChronon();
		}
		else{
			Integer[] dest = getDestination(eligibleDestinations);//[0],getDestination(eligibleDestinations)[1]};
			newGrid[dest[0]][dest[1]] = ((ReproducingCell) grid[r][c]).ageOneChronon();
			Integer[] currCoord = {r,c};
			if(grid[dest[0]][dest[1]].toString().equals("FishCell")){
				((SharkCell) newGrid[dest[0]][dest[1]]).replenishEnergy();
			}
			updatedCoordinates.add(dest);
			updatedCoordinates.add(currCoord);
			newGrid[r][c] = ((ReproducingCell) (grid[r][c])).reproducingResult();
		}
	}
	
	private void updateFromFish(Cell[][] grid, int r, int c, Cell[][] newGrid, ArrayList<Integer[]> updatedCoordinates){//,ArrayList<Cell> neighbors) {
		ArrayList<Integer[]> eligibleDestinations = getNeighbors(grid, r, c, "EmptyCell", updatedCoordinates);
		if(eligibleDestinations.isEmpty()){
			newGrid[r][c] = ((ReproducingCell)grid[r][c]).ageOneChronon();//ageOneChronon((SharkCell) grid[r][c]);
		}
		else{
			Integer[] dest = getDestination(eligibleDestinations);
			newGrid[dest[0]][dest[1]] = ((ReproducingCell) (grid[r][c])).ageOneChronon();
			Integer[] currCoord = {r,c};
			updatedCoordinates.add(dest);
			updatedCoordinates.add(currCoord);
			newGrid[r][c] = ((ReproducingCell) (grid[r][c])).reproducingResult();
		}
	}

/*	
	private void updateFromCell(Cell[][] grid, int r, int c, Cell[][] newGrid, ArrayList<Integer[]> updatedCoordinates, String type){        
		ArrayList<Integer[]> eligibleDestinations = new ArrayList<Integer[]>();                                                                  
		if(type.equals("FishCell")){                                                                                                             
			eligibleDestinations = getNeighbors(grid, r, c, "EmptyCell", updatedCoordinates);                                                        
		}                                                                                                                                        
		else{                                                                                                                                    
			eligibleDestinations = getNeighbors(grid, r, c, "FishCell", updatedCoordinates);                                                         
			if(eligibleDestinations.isEmpty()){                                                                                                      
				eligibleDestinations = getNeighbors(grid, r, c, "EmptyCell", updatedCoordinates);                                                        
			}                                                                                                                                        
		}                                                                                                                                        
		if(eligibleDestinations.isEmpty()){                                                                                                      
			newGrid[r][c] = ((ReproducingCell) grid[r][c]).reproducingResult();                                                                      
		}                                                                                                                                        
		else{                                                                                                                                    
			Integer[] dest = getDestination(eligibleDestinations);                                                                                   
			newGrid[dest[0]][dest[1]] = ((ReproducingCell) (grid[r][c])).ageOneChronon();                                                            
			Integer[] currCoord = {r,c};
			if(grid[dest[0]][dest[1]].toString().equals("FishCell")){
				((SharkCell) newGrid[dest[0]][dest[1]]).replenishEnergy();
			}
			updatedCoordinates.add(dest);                                                                                                            
			updatedCoordinates.add(currCoord);                                                                                                       
			newGrid[r][c] = ((ReproducingCell) (grid[r][c])).reproducingResult();                                                                    
		}                                                                                                                                        
	}                                                                                                                                        


	*/

	private ArrayList<Integer[]> getNeighbors(Cell[][] grid, int r, int c, String s, ArrayList<Integer[]> updatedCoordinates){
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		for(int d = -1; d <= 1; d += 2){

			//REFACTOR
			if(myBoundary.makeCell(grid, r+d, c) != null){
				//list.add(grid[r+d][c]);
				Integer[] currArr = new Integer[2];
				currArr[0] = r+d;
				currArr[1] = c;
				list.add(currArr);
			}
			if(myBoundary.makeCell(grid, r, c+d) != null){
				//list.add(grid[r][c+d]);
				Integer[] currArr = new Integer[2];
				currArr[0] = r;
				currArr[1] = c+d;	
				list.add(currArr);
			}
/*
			neighborsFromTwoDirections(grid, r+d, c, list);
			neighborsFromTwoDirections(grid, r, c+d, list);
*/
		}
		for(Integer[] i : new ArrayList<Integer[]>(list)){
			if (!(grid[i[0]][i[1]].toString().equals(s)) || contains(updatedCoordinates, i)){
				list.remove(i);
			}
		}
		return list;
	}
/*
	private void neighborsFromTwoDirections(Cell[][] grid, int newR, int newC, ArrayList<Integer[]> list) {
		if(inBounds(grid, newR, newC)){
			Integer[] currArr = new Integer[2];
			currArr[0] = newR;
			currArr[1] = newC;
			list.add(currArr);
		}
	}
*/
	private Integer[] getDestination(ArrayList<Integer[]> eligibleDestinations) {
		return eligibleDestinations.get(new Random().nextInt(eligibleDestinations.size()));
	}

	private boolean deadShark(Cell cell) {
		if(cell instanceof SharkCell){
			return ((SharkCell) cell).isDead();
		}
		return false;
	}

	@Override
	protected String getNeighborsState(ArrayList<Cell> neighbors) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Cell newState(Cell[][] newGrid, Cell cell, String hoodState,
			int r, int c) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
