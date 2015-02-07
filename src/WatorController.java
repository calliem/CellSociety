
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
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
		List<Integer[]> updatedCoordinates = new ArrayList<Integer[]>();
		

		/**
		 * Do shark first
		 * then fish
		 * then empty
		 */
		List<ArrayList<Integer[]>> myCoordinates= findCoordinates(grid);
		//CoordinateObject 
		
		for(List<Integer[]> types : myCoordinates){
			for(Integer[] coords : types){
				int row = coords[0];
				int col = coords[1];
				//Integer[] curCoordinates = {r,c};
				if(!contains(updatedCoordinates, coords)){
					Cell curCell = grid[row][col];
					if(deadShark(curCell)){

						newGrid[row][col] = ((SharkCell) curCell).reproducingResult();
						//grid[row][col] = ((SharkCell) curCell).reproducingResult();
					}
					else if(curCell.toString().equals("EmptyCell")){
						//grid[row][col] = makeCell("EmptyCell");
						newGrid[row][col] = makeCell("EmptyCell");
						//System.out.println("row " + row + " col " + col);
					}
					else{
						//updateDestination(grid, r, c, newGrid, updatedCoordinates);
						updateFromCell(grid, row, col, newGrid, updatedCoordinates, grid[row][col].toString()); 
					}	
				}
			}
		}
		/*
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
						//updateDestination(grid, r, c, newGrid, updatedCoordinates);
						updateFromCell(grid, r, c, newGrid, updatedCoordinates, grid[r][c].toString()); 
					}	
				}
			}
		}*/
		//return grid;
		return newGrid;
	}
	
	private List<ArrayList<Integer[]>> findCoordinates(Cell[][] grid) {
		List<ArrayList<Integer[]>> returnList = new ArrayList<ArrayList<Integer[]>>();
		ArrayList<Integer[]> sharkList = new ArrayList<Integer[]>();
		ArrayList<Integer[]> fishList = new ArrayList<Integer[]>();
		ArrayList<Integer[]> emptyList = new ArrayList<Integer[]>();
		
		returnList.add(sharkList);
		returnList.add(fishList);
		returnList.add(emptyList);
		
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				String curName = grid[i][j].toString();
				Integer[] curCoords = {i,j};
				if(curName.equals("SharkCell")){
					sharkList.add(curCoords);
					returnList.set(0, sharkList);
				}
				else if(curName.equals("FishCell")){
					fishList.add(curCoords);
					returnList.set(1, fishList);
				}
				else{
					emptyList.add(curCoords);
					returnList.set(2, emptyList);
				}
			}
		}
		return returnList;
	}
	
/*
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
*/

	

	private void updateFromCell(Cell[][] grid, int r, int c, Cell[][] newGrid, List<Integer[]> updatedCoordinates, String type){        
		List<Integer[]> eligibleDestinations = makeEligible(grid, r, c, updatedCoordinates, type);//new ArrayList<Integer[]>(); 
		/*
		if(type.equals("FishCell")){                                                                                                             
			eligibleDestinations = getNeighbors(grid, r, c, "EmptyCell", updatedCoordinates);                                                        
		}                                                                                                                                        
		else{                                                                                                                                    
			eligibleDestinations = getNeighbors(grid, r, c, "FishCell", updatedCoordinates);                                                         
			if(eligibleDestinations.isEmpty()){                                                                                                      
				eligibleDestinations = getNeighbors(grid, r, c, "EmptyCell", updatedCoordinates);                                                        
			}                                                                                                                                        
		}
		*/                                                                                                                                        
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
	protected String getNeighborsState(List<Cell> neighbors) {
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
