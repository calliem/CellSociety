package boundary;

import cell.Cell;

public abstract class Boundary {

	public abstract Integer[] findCell(Cell[][] grid, int row, int col);

}
