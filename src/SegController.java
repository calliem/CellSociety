import java.util.ArrayList;
import java.util.Random;


public class SegController extends SimController{
	double myHappyFraction;
	ArrayList<String> myRandList;
	
	public SegController(float happyFraction){
		myHappyFraction = happyFraction;
	}
	@Override
	public Cell[][] runOneSim(Cell[][] grid) {
		makeRandomList(grid);
		return super.runOneSim(grid);
	}
	
	@Override
	protected String getNeighborsState(ArrayList<Cell> neighbors) {
		double blueCount = 0;
		double redCount = 0;
		for(Cell c: neighbors){
			if(c.getState().toString().equals("blue")){
				blueCount++;
			}
			if(c.getState().toString().equals("red")){
				redCount++;
			}
		}
		return selectNeighborsState(blueCount, redCount);
	}

	private String selectNeighborsState(double bC, double rC) {
		double total = bC + rC;
		if(total == 0){
			return "none";
		}
		boolean blueHappy = bC/total > myHappyFraction;
		boolean redHappy = rC/total > myHappyFraction;
		if(blueHappy){
			if(redHappy){
				return "both";
			}
			return "blue";
		}
		return "red";
	}
	
	@Override
	protected Cell newState(CellState cellState, String neighborsState) {
		//handle current empty cell
		if(cellState.toString().equals("empty")){
			return populateEmpty();
		}
		//if its happy with what it is stay.
		if(cellState.toString().equals(neighborsState) || neighborsState.equals("both")){
			return new Cell(neighborsState);
		}
		return new Cell("empty");
	}
	
	private void makeRandomList(Cell[][] grid) {
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[0].length; c++){
				CellState curState = grid[r][c].getState();
				if(!curState.toString().equals("empty")){
					if(!getNeighborsState(getNeighbors(grid, r, c)).equals(curState.toString())){
						myRandList.add(curState.toString());
					}
				}
			}
		}
	}
	
	private Cell populateEmpty() {
		String randomString = myRandList.get(new Random().nextInt(myRandList.size()));
		return new Cell(randomString);
	}

}
