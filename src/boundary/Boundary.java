package boundary;

import cell.Cell;
import cellsociety.Coordinate;

public abstract class Boundary {

	
	public abstract Coordinate findCell(Cell[][] grid, int row, int col);
	

}
