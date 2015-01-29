import java.util.ArrayList;


public class WaTorController extends SimController{

	public Cell[][] runOneSim(Cell[][] grid) {
		Cell[][] newGrid = new Cell[grid.length][grid[0].length];
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[0].length; c++){
				Cell curCell = grid[r][c];
				//CellState cs = curCell.getState();
				if(!curCell.toString().equals("empty") && !deadShark(curCell)){
					updateDestination(grid, r, c, newGrid);
					updateSource(grid, r, c, newGrid);
					newGrid[r][c] = new Cell("empty");
				}
			}
		}
		return newGrid;
		
	}
	private void updateDestination(Cell[][] grid, int r, int c, Cell[][] newGrid) {
		ArrayList<Cell> neighbors = getNeighbors(grid, r, c);
		String state = grid[r][c].toString();
		switch (state){
		case "shark":
			updateFromShark(grid, r, c, newGrid, neighbors);
		default:
			updateFromFish(grid, r, c, newGrid, neighbors);
		}
		
	}
	
	private void updateSource(Cell[][] grid, int r, int c, Cell[][] newGrid){
		
	}
	
	private void updateFromShark(Cell[][] grid, int r, int c, Cell[][] newGrid,
			ArrayList<Cell> neighbors) {
		// TODO Auto-generated method stub
		//Make list of fish
		//pick random fish spot
		//put SharkCell(reproduce--, maxEnergy) at corresponding newGrid spot
		//if no fish, make list of empty cells
		//pick random empty cell
		//put SharkCell(reproduce--, energy--) at corresponding newGrid spot
		//
		
		
	}
	
	private void updateFromFish(Cell[][] grid, int r, int c, Cell[][] newGrid,
			ArrayList<Cell> neighbors) {
		// TODO Auto-generated method stub
		//Make list of random empty cell
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
