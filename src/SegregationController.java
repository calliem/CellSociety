import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;


public class SegregationController extends SimController{
	double myHappyFraction;
	private int numEmpty = 0;
	private int iterCount = 0;
	//private Queue<Integer> iterCount = new LinkedList<Integer>();
	private ArrayList<Integer> randomCount = new ArrayList<Integer>();
	ArrayList<String> myRandList = new ArrayList<String>();
	
	public SegregationController(Map<String, String> parameters){
		myHappyFraction = Double.parseDouble(parameters.get("probHappy"));
	}
	
	@Override
	public Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		numEmpty = 0;
		iterCount = 0;
		makeRandomList(grid);
		return super.runOneSim(grid);
	}
	
	@Override
	protected String getNeighborsState(ArrayList<Cell> neighbors) {
		double blueCount = 0;
		double redCount = 0;
		for(Cell c: neighbors){
			if(c.toString().equals("BlueCell")){
				blueCount++;
			}
			if(c.toString().equals("RedCell")){
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
			return "BlueCell";
		}
		return "RedCell";
	}
	
	@Override
	protected Cell newState(Cell cell, String neighborsState) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		//handle current empty cell
		if(cell.toString().equals("EmptyCell")){
			iterCount++;
			System.out.println("$$$"+ iterCount+"^^^"+numEmpty+"((("+ randomCount+"$$$");
			if(shouldPopulate()){
				return populateEmpty();
			}
			
		}
		//if its happy with what it is stay.
		if(cell.toString().equals(neighborsState) || neighborsState.equals("both")){
			return makeCell(cell.toString());
		}
		if(neighborsState.equals("none")){
			System.out.println("WHYYY");
		}
		return makeCell("EmptyCell");
	}
	
	private boolean shouldPopulate() {
		return (randomCount.contains(iterCount));

	}

	private void makeRandomList(Cell[][] grid) {
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[0].length; c++){
				String curString = grid[r][c].toString();
				if(!curString.toString().equals("EmptyCell")){
					if(!((getNeighborsState(getNeighbors(grid, r, c)).equals(curString)) || (getNeighborsState(getNeighbors(grid, r, c)).equals("both")))){
						//System.out.println(getNeighborsState(getNeighbors(grid, r, c))+"222222"+curString);
						myRandList.add(curString);
					}
				}
				else{
					numEmpty++;
				}
			}
		}
		populateRandomCountQueue();
	}
	
	private void populateRandomCountQueue() {
		for(int i = 0; i < numEmpty; i++){
			randomCount.add(new Random().nextInt(numEmpty)+1);
		}
	}

	private Cell populateEmpty() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		if(!myRandList.isEmpty()){
			String randomString = myRandList.get(new Random().nextInt(myRandList.size()));
			myRandList.remove(randomString);
			return makeCell(randomString);
		}
		return makeCell("EmptyCell");
	}

}
