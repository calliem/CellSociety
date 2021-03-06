package boundary;

import cell.Cell;
import cellsociety.Coordinate;




public class ToroidalBoundary extends Boundary{

	@Override
	public Coordinate findCell(Cell[][] grid, int row, int col) {
		if(row < 0){
			row = grid.length - 1;//|| row >= grid.length || col < 0 || col >= grid[0].length){
		}
		else if(row >= grid.length){
			row = 0;
		}
		if(col < 0){
			col = grid[0].length - 1;
		}
		else if(col >= grid[0].length){
			col = 0;
		}
		return new Coordinate(row, col);
	}

}
