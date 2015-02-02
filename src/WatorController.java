import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class WatorController extends CardinalSimController{

	public Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Cell[][] newGrid = new Cell[grid.length][grid[0].length];
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[0].length; c++){
				//Check cell for deadShark, handle deadShark
				
				//else if empty, updateSource
				//else updateDestination
				
				Cell curCell = grid[r][c];
				if(deadShark(curCell)){
					//newGrid[r][c] = isReproducing(curCell);
					newGrid[r][c] = ((SharkCell) curCell).reproducingResult();
				}
				else if(curCell.toString().equals("EmptyCell")){
					newGrid[r][c] = makeCell("EmptyCell");
				}
				else{
					updateDestination(grid, r, c, newGrid);
				}
				//CellState cs = curCell.getState();
				/*
				if(!curCell.toString().equals("EmptyCell") && !deadShark(curCell)){
					updateDestination(grid, r, c, newGrid);
					updateSource(grid, r, c, newGrid);
					newGrid[r][c] = makeCell("EmptyCell");
				}
				*/
			}
		}
		return newGrid;
		
	}
	private Cell sharkResult(Cell curCell) {
		// TODO Auto-generated method stub
		return null;
	}
	private void updateDestination(Cell[][] grid, int r, int c, Cell[][] newGrid) {
		//ArrayList<Cell> neighbors = getNeighbors(grid, r, c);
		String state = grid[r][c].toString();
		switch (state){
		case "shark":
			updateFromShark(grid, r, c, newGrid);//, neighbors);
		default:
			updateFromFish(grid, r, c, newGrid);//, neighbors);
		}
		
	}
	
	private void updateSource(Cell[][] grid, int r, int c, Cell[][] newGrid){
		
	}
	
	private void updateFromShark(Cell[][] grid, int r, int c, Cell[][] newGrid){//,ArrayList<Cell> neighbors) {
		ArrayList<Integer[]> eligibleDestinations = getNeighbors(grid, r, c, "FishCell");
		if(eligibleDestinations.isEmpty()){
			eligibleDestinations = getNeighbors(grid, r, c, "EmptyCell");
		}
		if(eligibleDestinations.isEmpty()){
			newGrid[r][c] = ageOneChronon((SharkCell) grid[r][c]);
			//newGrid[r][c].setReproduce(newGrid[r][c].getReproduce - 1);
			//newGrid[r][c].setEnergy(newGrid[r][c].getEnergy - 1);
		}
		else{
			newGrid[getDestination(eligibleDestinations)[0]][getDestination(eligibleDestinations)[1]] = ageOneChronon(grid[r][c]);
			newGrid[r][c] = updateSource(isReproducing(grid[r][c]));
		}
		
		// TODO Auto-generated method stub
		//Make list of fish
		//pick random fish spot
		//put SharkCell(reproduce--, maxEnergy) at corresponding newGrid spot
		//if no fish, make list of empty cells
		//pick random empty cell
		//put SharkCell(reproduce--, energy--) at corresponding newGrid spot
		//
		
		
	}
	
	private ArrayList<Integer[]> getNeighbors(Cell[][] grid, int r, int c,
			String string) {
		ArrayList<Cell> list = getNeighbors(grid,r, c);
		for(Cell cell: list){
			if(!cell instanceOf ){
				
			}
		}
	}
	private void updateFromFish(Cell[][] grid, int r, int c, Cell[][] newGrid){//,ArrayList<Cell> neighbors) {
		// TODO Auto-generated method stub
		//Make list of random empty cell
		ArrayList<Integer[]> eligibleDestinations = getNeighbors(grid, r, c, "EmptyCell");
		
		if(eligibleDestinations.isEmpty()){
			newGrid[r][c] = grid[r][c];//ageOneChronon((SharkCell) grid[r][c]);
			//newGrid[r][c].setReproduce(newGrid[r][c].getReproduce - 1);
			//newGrid[r][c].setEnergy(newGrid[r][c].getEnergy - 1);
		}
		else{
			newGrid[getDestination(eligibleDestinations)[0]][getDestination(eligibleDestinations)[1]] = ageOneChronon(grid[r][c]);
			newGrid[r][c] = updateSource(isReproducing(grid[r][c]));
		}
		//put FishCell(reproduce--) at corresponding newGrid spot
		
	}
	
	/** 
	 * Check's if shark is out of energy
	 * @param cell
	 * @return
	 */
	private boolean deadShark(Cell cell) {
		if(cell instanceof SharkCell){
			if(((SharkCell) cell).getEnergy() == 0){
				return true;
			}
		}
		return false;
	}
	/*
	@Override
	protected HashMap<> getNeighborsMap(Cell[][] grid, int r, int c){
		ArrayList<Cell> list = new ArrayList<Cell>();
		for(int d = -1; d <= 1; d++){
			list.add(grid[r+d][c]);
			list.add(grid[r][c+d]);
		}
		return list;
	}
*/
	@Override
	protected String getNeighborsState(ArrayList<Cell> neighbors) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Cell newState(Cell cell, String hoodState) {
		// TODO Auto-generated method stub
		return null;
	}

}