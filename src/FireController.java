import java.util.ArrayList;
import java.util.Random;


public class FireController extends SimController{
	
	int myProbCatch;
	
	public FireController(int probCatch){
		super();
		myProbCatch = probCatch;
	}
	
	@Override
	protected ArrayList<Cell> getNeighbors(Cell[][] grid, int r, int c){
		ArrayList<Cell> list = new ArrayList<Cell>();
		for(int d = -1; d <= 1; d++){
			list.add(grid[r+d][c]);
			list.add(grid[r][c+d]);
		}
		return list;
	}
	
	@Override
	protected String getNeighborsState(ArrayList<Cell> neighbors) {
		for(Cell c: neighbors){
			if(c.getState().toString().equals("fire")){
				if(new Random().nextInt(100) < myProbCatch){
					return "fire";
				}
				break;
			}
		}
		return "tree";
	}

	@Override
	protected Cell newState(CellState cellState, String hoodState) {
		if(cellState.toString().equals("tree")){
			return new Cell(hoodState);
		}
		return new Cell("empty");
	}

}
