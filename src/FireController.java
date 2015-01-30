import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;


public class FireController extends SimController{

	//from XML
	private static int myProbCatch;// = 50;

	public FireController(int probCatch){
		super();
		myProbCatch = probCatch;
	}

	@Override
	protected ArrayList<Cell> getNeighbors(Cell[][] grid, int r, int c){
		ArrayList<Cell> list = new ArrayList<Cell>();
		for(int d = -1; d <= 1; d++){
			if(inBounds(grid, r+d, c+d)){
				list.add(grid[r+d][c]);
				list.add(grid[r][c+d]);
			}
		}
		return list;
	}

	@Override
	protected String getNeighborsState(ArrayList<Cell> neighbors) {
		for(Cell c: neighbors){
			if(c.toString().equals("FireCell")){
				if(new Random().nextInt(100) < myProbCatch){
					return "FireCell";
				}
				break;
			}
		}
		return "TreeCell";
	}
	/*
	@Override
	protected Cell newState(CellState cellState, String hoodState) {
		if(cellState.toString().equals("tree")){
			return new Cell(hoodState);
		}
		return new Cell("empty");
	}
	 */
	@Override
	protected Cell newState(Cell cell, String hoodState)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		if(cell.toString().equals("TreeCell")){
			return makeCell(hoodState);
		}
		return makeCell("EmptyCell");
	}

}
