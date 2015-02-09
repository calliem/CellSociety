public class FiniteBoundary extends Boundary{

	@Override
	public Coordinate findCell(Cell[][] grid, int row, int col) {
		if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length){
			return null;
		}
		return new Coordinate(row, col);
	}

}
