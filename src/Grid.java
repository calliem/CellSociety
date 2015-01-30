/*import java.util.ArrayList;


public class Grid {

	private Cell[][] myCells;
	private int myWidth;
	private int myHeight;
	
	public Grid(int width, int height) {	
		myWidth = width;
		myHeight = height;
		myCells = new Cell[width][height];
	}
	
	/**
	 * Given a set of coordinates, returns the list of cells that are
	 * adjacent to the specific cell.
	 * 
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @return An ArrayList of Cell objects containing the neighbors
	 
	
	public ArrayList<Cell> getNeighbors(int x, int y) {
		
		ArrayList<Cell> cells = new ArrayList<Cell>();
	
		if (!inBounds(x, y))
			return cells;
		
		int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
		int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
		
		for (int i = 0; i < dx.length; i++) {
			if (inBounds(x + dx[i], y + dy[i]))
				cells.add(myCells[x + dx[i]][y + dy[i]]);
		}
		
		return cells;
	}

	/**
	 * Sets a specific Cell inside the Grid
	 * 
	 * @param c The Cell to set in the Grid
	 * @param x The x-coordinate of the Cell
	 * @param y The y-coordinate of the Cell
	 */
	public void setCell(Cell c, int x, int y) {
		myCells[x][y] = c;
	}
	
	private boolean inBounds(int x, int y) {
		return (x >= 0 && y >= 0 &&	x < myWidth && y < myHeight);
	}
	
}
*/