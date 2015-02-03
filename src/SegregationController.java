import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


public class SegregationController extends SimController{
	private double myHappyFraction;
	private ArrayList<Integer[]> myEmptyList;
	private ArrayList<Integer[]> updatedList;

	public SegregationController(Map<String, String> parameters){
		myHappyFraction = Double.parseDouble(parameters.get("probHappy"));
	}

	@Override
	public Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		updatedList = new ArrayList<Integer[]>();
		makeEmptyList(grid);
		return super.runOneSim(grid);

	}

	private void makeEmptyList(Cell[][] grid) {
		myEmptyList = new ArrayList<Integer[]>();
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
		if(!contains(updatedList, curCoord)){
			Random random = new Random();
			if(cell.toString().equals(neighborsState) || neighborsState.equals("both")){
				return makeCell(cell.toString());
			}
			else if(!cell.toString().equals("EmptyCell")){
				Integer[] randCoord = myEmptyList.remove(random.nextInt(myEmptyList.size()));
				newGrid[randCoord[0]][randCoord[1]] = makeCell(cell.toString());
				updatedList.add(randCoord);
				Integer[] newCoord = {r,c};
				myEmptyList.add(newCoord);
			}
			return makeCell("EmptyCell");
		}
		return newGrid[r][c];
	}
	
}