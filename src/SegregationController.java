


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;


public class SegregationController extends SimController{
	double myHappyFraction;
	private int numEmpty = 0;
	//private int newEmpty = 0;
	private int iterCount = 0;
	private int bound;
	//private Queue<Integer> iterCount = new LinkedList<Integer>();
	private ArrayList<Integer> randomCount;
	private ArrayList<String> myRandList;
	private ArrayList<Integer[]> myEmptyList;
	private ArrayList<Integer[]> updatedList;
	private Boundary myBoundary = new FiniteBoundary();
	private Neighbor myNeighbor = new FullNeighbor(myBoundary);
	//private Neighbor 
	//private ArrayList<String> myRandomList;

	public SegregationController(Map<String, String> parameters){
		myHappyFraction = Double.parseDouble(parameters.get("probHappy"));
	}

	@Override
	public Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		numEmpty = 0;
		iterCount = 0;
		updatedList = new ArrayList<Integer[]>();
		//newEmpty = 0;
		//count how many empty cells there are and make a list of unhappy nonempty cells
		makeRandomList(grid);
		makeEmptyList(grid);
		return super.runOneSim(grid);

	}

	private void makeEmptyList(Cell[][] grid) {
		myEmptyList = new ArrayList<Integer[]>();
		//randomCount = new ArrayList<Integer>();
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[0].length; c++){
				String curString = grid[r][c].toString();
				if(curString.toString().equals("EmptyCell")){
					Integer[] coords = {r,c};
					myEmptyList.add(coords);
				}
			}
		}
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
		if(redHappy){
			return "RedCell";
		}
		return "none";
	}

	@Override
	protected Cell newState(Cell[][] newGrid, Cell cell, String neighborsState, int r, int c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		
		Integer[] curCoord = {r,c};
		//if its happy with what it is stay.
		//!updatedList.isEmpty() && 
		if(!contains(updatedList, curCoord)){
			Random random = new Random();
			if(cell.toString().equals(neighborsState) || neighborsState.equals("both")){
				return makeCell(cell.toString());
			}
			//if unhappy, populate an empty cell and add current to emptyList
			else if(!cell.toString().equals("EmptyCell")){
				Integer[] randCoord = myEmptyList.remove(random.nextInt(myEmptyList.size()));
				newGrid[randCoord[0]][randCoord[1]] = makeCell(cell.toString());
				updatedList.add(randCoord);
				Integer[] newCoord = {r,c};
				myEmptyList.add(newCoord);
				//return makeCell("EmptyCell");
			}
			return makeCell("EmptyCell");
		}
		return newGrid[r][c];
	}

	private boolean contains(ArrayList<Integer[]> updatedCoordinates,
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

	private boolean shouldPopulate() {
		return (randomCount.contains(iterCount));

	}

	private void makeRandomList(Cell[][] grid) {
		myRandList = new ArrayList<String>();
		randomCount = new ArrayList<Integer>();
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[0].length; c++){
				String curString = grid[r][c].toString();
				if(!curString.toString().equals("EmptyCell")){
					if(((getNeighborsState(myNeighbor.getNeighbors(grid, r, c)).equals(curString)) || (getNeighborsState(myNeighbor.getNeighbors(grid, r, c)).equals("both")))){
						//System.out.println(getNeighborsState(getNeighbors(grid, r, c))+"222222"+curString);

					}
					else{
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
		if(bound == 0){
			bound = myRandList.size() + 1;
		}
		for(int i = 0; i < myRandList.size(); i++){
			//randomCount.add(new Random().nextInt(numEmpty)+1);
			//randomCount.add(uniqueRandom(Math.max(myRandList.size()+1, numEmpty),randomCount));
			randomCount.add(uniqueRandom(bound,randomCount));
		}
	}

	private int uniqueRandom(int max, ArrayList<Integer> rC) {
		Random random = new Random();
		int possibleRandom = random.nextInt(max);
		while(rC.contains(possibleRandom)){
			possibleRandom = random.nextInt(max);
		}
		return possibleRandom;
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