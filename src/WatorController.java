
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


public class WatorController extends CardinalSimController{

	public WatorController(Map<String, String> parameters){
	}

	public Cell[][] runOneSim(Cell[][] grid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Cell[][] newGrid = new Cell[grid.length][grid[0].length];
		ArrayList<Integer[]> updatedCoordinates = new ArrayList<Integer[]>();
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[0].length; c++){
				//Check cell for deadShark, handle deadShark

				//else if empty, updateSource
				//else updateDestination
				Integer[] curCoordinates = {r,c};
				if(!updatedCoordinates.contains(curCoordinates)){
					Cell curCell = grid[r][c];
					if(deadShark(curCell)){
						//newGrid[r][c] = isReproducing(curCell);
						newGrid[r][c] = ((SharkCell) curCell).reproducingResult();
					}
					else if(curCell.toString().equals("EmptyCell")){
						newGrid[r][c] = makeCell("EmptyCell");
					}
					else{
						updateDestination(grid, r, c, newGrid, updatedCoordinates);
					}
					//CellState cs = curCell.getState();
					/*
				if(!curCell.toString().equals("EmptyCell") && !deadShark(curCell)){
					updateDestination(grid, r, c, newGrid);
					updateSource(grid, r, c, newGrid);
					newGrid[r][c] = makeCell("EmptyCell");
				}
					 */
				}
			}
		}
		return newGrid;

	}

	/*
	private Cell sharkResult(Cell curCell) {
		// TODO Auto-generated method stub
		return null;
	}*/

	private void updateDestination(Cell[][] grid, int r, int c, Cell[][] newGrid, ArrayList<Integer[]> updatedCoordinates) {
		//ArrayList<Cell> neighbors = getNeighbors(grid, r, c);
		String state = grid[r][c].toString();
		switch (state){
		case "shark":
			updateFromShark(grid, r, c, newGrid, updatedCoordinates);//, neighbors);
		default:
			updateFromFish(grid, r, c, newGrid, updatedCoordinates);//, neighbors);
		}

	}

	/*
	private void updateSource(Cell[][] grid, int r, int c, Cell[][] newGrid){	
	}
	 */

	private void updateFromShark(Cell[][] grid, int r, int c, Cell[][] newGrid, ArrayList<Integer[]> updatedCoordinates){//,ArrayList<Cell> neighbors) {
		ArrayList<Integer[]> eligibleDestinations = getNeighbors(grid, r, c, "FishCell");
		if(eligibleDestinations.isEmpty()){
			eligibleDestinations = getNeighbors(grid, r, c, "EmptyCell");
		}
		if(eligibleDestinations.isEmpty()){
			newGrid[r][c] = ((SharkCell) grid[r][c]).ageOneChronon();
			//newGrid[r][c].setReproduce(newGrid[r][c].getReproduce - 1);
			//newGrid[r][c].setEnergy(newGrid[r][c].getEnergy - 1);
		}
		else{
			Integer[] currCoord = getDestination(eligibleDestinations);//[0],getDestination(eligibleDestinations)[1]};
			newGrid[currCoord[0]][currCoord[1]] = ((SharkCell) grid[r][c]).ageOneChronon();
			updatedCoordinates.add(currCoord);
			newGrid[r][c] = ((SharkCell) (grid[r][c])).reproducingResult();
		}

		// TODO Auto-generated method stub
		//Make list of fish
		//pick random fish spot
		//put SharkCell(reproduce--, maxEnergy) at corresponding newGrid spot
		//if no fish, make list of empty cells
		//pick random empty cell
		//put SharkCell(reproduce--, energy--) at corresponding newGrid spot
		//


	}
	private ArrayList<Integer[]> getNeighbors(Cell[][] grid, int r, int c, String s){
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		for(int d = -1; d <= 1; d += 2){
			//REFACTOR
			if(inBounds(grid, r+d, c)){
				//list.add(grid[r+d][c]);
				Integer[] currArr = new Integer[2];
				currArr[0] = r+d;
				currArr[1] = c;
				list.add(currArr);
			}
			if(inBounds(grid, r, c+d)){
				//list.add(grid[r][c+d]);
				Integer[] currArr = new Integer[2];
				currArr[0] = r;
				currArr[1] = c+d;	
				list.add(currArr);
			}
		}
		for(Integer[] i : list){
			if (!(grid[i[0]][i[1]].toString().equals(s))){
				list.remove(i);
			}
		}
		return list;
	}

	/*
	private Cell ageOneChronon(SharkCell sharkCell) {
		// TODO Auto-generated method stub
		return null;
	}
	private ArrayList<Integer[]> getNeighbors(Cell[][] grid, int r, int c,
			String string) {
		ArrayList<Cell> list = getNeighbors(grid,r, c);
		for(Cell cell: list){
			if(!cell instanceOf ){

			}
		}
	}
	 */
	private void updateFromFish(Cell[][] grid, int r, int c, Cell[][] newGrid, ArrayList<Integer[]> updatedCoordinates){//,ArrayList<Cell> neighbors) {
		// TODO Auto-generated method stub
		//Make list of random empty cell
		ArrayList<Integer[]> eligibleDestinations = getNeighbors(grid, r, c, "EmptyCell");

		if(eligibleDestinations.isEmpty()){
			newGrid[r][c] = grid[r][c];//ageOneChronon((SharkCell) grid[r][c]);
			//newGrid[r][c].setReproduce(newGrid[r][c].getReproduce - 1);
			//newGrid[r][c].setEnergy(newGrid[r][c].getEnergy - 1);
		}
		else{
			Integer[] dest = getDestination(eligibleDestinations);
			newGrid[dest[0]][dest[1]] = ((FishCell) (grid[r][c])).ageOneChronon();
			//Integer[] currCoord = {dest[0],dest[1]};
			updatedCoordinates.add(dest);
			newGrid[r][c] = ((FishCell) (grid[r][c])).reproducingResult();
			//System.out.println("!!!" + dest);
		}
		
		//put FishCell(reproduce--) at corresponding newGrid spot

	}

	private Integer[] getDestination(ArrayList<Integer[]> eligibleDestinations) {
		return eligibleDestinations.get(new Random().nextInt(eligibleDestinations.size()));
	}

	/** 
	 * Check's if shark is out of energy
	 * @param cell
	 * @return
	 */
	private boolean deadShark(Cell cell) {
		if(cell instanceof SharkCell){
			return ((SharkCell) cell).isDead();
		}
		return false;
	}
	/*
	@Override
	protected HashMap<> getNeighborsMap(Cell[][] grid, int r, int c){
		ArrayList<Cell> list = new ArrayList<Cell>();
		for(int d = -1; d <= 1; d++){
			list.add(grid[r+d][c]);
			list.add(grid[r][c+d]);
		}
		return list;
	}
	 */
	@Override
	protected String getNeighborsState(ArrayList<Cell> neighbors) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Cell newState(Cell cell, String hoodState) {
		// TODO Auto-generated method stub
		return null;
	}

}