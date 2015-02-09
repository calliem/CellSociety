import java.util.List;


public abstract class Neighbor {
	/*
	private Boundary myBounds;
	
	public ShapeNeighbors(Boundary bounds){
		myBounds = bounds;
	}
	*/
	
	public abstract List<Integer[]> getNeighbors(Cell[][] grid, int row, int col, int scalar);
	
	public List<Integer[]> getNeighbors(Cell[][] grid, int row, int col){
		return getNeighbors(grid, row, col, 1);
	}
}
