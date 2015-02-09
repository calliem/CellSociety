package neighbor;
import java.util.List;

import boundary.Boundary;
import cell.Cell;


public abstract class Neighbor {
	
	private Boundary myBounds;
	
	public Neighbor(Boundary b){
		myBounds = b;
	}
	
	public abstract List<Integer[]> getNeighbors(Cell[][] grid, int row, int col, int scalar);
	
	public List<Integer[]> getNeighbors(Cell[][] grid, int row, int col){
		return getNeighbors(grid, row, col, 1);
	}
	
	protected Boundary getBounds(){
		return myBounds;
	}
}
