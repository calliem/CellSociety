import java.util.ArrayList;


public abstract class SimController {
	
	public Cell[][] runOneSim(Cell[][] grid) {
		Cell[][] newGrid = new Cell[grid.length][grid[0].length];
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[0].length; c++){
				CellState cs = grid[r][c].getState();
				ArrayList<Cell> neighbors = getNeighbors(grid, r, c);
				String neighborsState = getNeighborsState(neighbors);
				Cell newCell = newState(cs, neighborsState);
				newGrid[r][c] = newCell;
			}
		}
		return newGrid;
	}

	protected ArrayList<Cell> getNeighbors(Cell[][] grid, int row, int col) {
		ArrayList<Cell> list = new ArrayList<Cell>();
		for(int dr = -1; dr <= 1; dr++){
			for(int dc = -1; dc <= 1; dc++){
				if(dr != 0 || dc != 0){
					int nextRow = row + dr;
					int nextCol = col + dc;
					if(inBounds(grid, nextRow, nextCol))
					list.add(grid[nextRow][nextCol]);
				}
			}
		}
		return list;
	}
	
	protected boolean inBounds(Cell[][] grid, int nextRow, int nextCol) {
		if(nextRow < 0 || nextRow >= grid.length || nextCol < 0 || nextCol >= grid[0].length){
			return false;
		}
		return true;
	}

	protected  abstract String getNeighborsState(ArrayList<Cell> neighbors);

	protected abstract Cell newState(CellState cellState, String hoodState);

}	
