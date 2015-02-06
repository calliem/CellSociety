


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;




public abstract class SimController {

	protected Boundary myBoundary = new FiniteBoundary();
	protected Neighbor myNeighbor = new FullNeighbor(myBoundary);
	
	/*
	public SimController(){	
	}
	*/
	
	public Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Cell[][] newGrid = new Cell[grid.length][grid[0].length];
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[0].length; c++){
				Cell curCell = grid[r][c];
				ArrayList<Cell> neighbors = myNeighbor.getNeighbors(grid, r, c);
				String neighborsState = getNeighborsState(neighbors);
				Cell newCell = newState(newGrid, curCell, neighborsState, r, c);
				//newState(grid)
				newGrid[r][c] = newCell;
			}
		}
		return newGrid;
	}
	/*
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
	*/
	
	/*
	protected boolean inBounds(Cell[][] grid, int nextRow, int nextCol) {
		if(nextRow < 0 || nextRow >= grid.length || nextCol < 0 || nextCol >= grid[0].length){
			return false;
		}
		return true;
	}
	*/

	public static Cell makeCell(String s) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		return (Cell) Class.forName("Cell").getConstructor(String.class).newInstance(s);
	}
	
	protected  abstract String getNeighborsState(ArrayList<Cell> neighbors);

	protected abstract Cell newState(Cell[][] newGrid, Cell cell, String hoodState, int r, int c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException;

	protected boolean contains(ArrayList<Integer[]> updatedCoordinates,
			Integer[] curCoordinates) {
		int[] coords = new int[curCoordinates.length];
		for(int i = 0; i < curCoordinates.length; i++){
			coords[i] = curCoordinates[i];
		}
		for(int j = 0; j < updatedCoordinates.size(); j++){
			int count = 0;
			for(int k = 0; k < coords.length; k++){
				if(coords[k] == updatedCoordinates.get(j)[k]){
					count++;
				}
			}
			if(count == coords.length){
				return true;
			}
		}
		return false;
	}
}	
