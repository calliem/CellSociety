
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class SimpleController extends Controller{
	
	@Override
	public Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Cell[][] newGrid = new Cell[grid.length][grid[0].length];
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[0].length; c++){
				Cell curCell = grid[r][c];
				List<Cell> neighbors = myNeighbor.getNeighbors(grid, r, c);
				String neighborsState = getNeighborsState(neighbors);
				Cell newCell = newState(newGrid, curCell, neighborsState, r, c);
			//	newCell.setColor(myParser.get();
				//newState(grid)
				newGrid[r][c] = newCell;
			}
		}
		return newGrid;
	}
	
	protected  abstract String getNeighborsState(List<Cell> neighbors);

	protected abstract Cell newState(Cell[][] newGrid, Cell cell, String neighborsState, int r, int c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException;
	
}	
