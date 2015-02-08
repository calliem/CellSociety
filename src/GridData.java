import java.util.List;



// This is used to package Data to be passed around as parameters more efficiently
public class GridData {

	private Cell[][] myGrid;
	private int myRow;
	private int myCol;
	private Cell[][] myNewGrid;
	private List<Integer[]> myList;

	public GridData(Cell[][] grid, int row, int col, Cell[][] newGrid, List<Integer[]> list){
		myGrid = grid;
		myRow = row;
		myCol = col;
		myNewGrid = newGrid;
		myList = list;

	}

	public Cell[][] getGrid() {
		return myGrid;
	}


	public int getRow() {
		return myRow;
	}

	public int getCol() {
		return myCol;
	}

	public Cell[][] getNewGrid() {
		return myNewGrid;
	}


	public List<Integer[]> getList() {
		return myList;
	}

}
