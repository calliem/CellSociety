

import java.util.ArrayList;


public abstract class Neighbor {
	/*
	private Boundary myBounds;
	
	public ShapeNeighbors(Boundary bounds){
		myBounds = bounds;
	}
	*/
	
	public abstract ArrayList<Cell> getNeighbors(Cell[][] grid, int row, int col);
}
