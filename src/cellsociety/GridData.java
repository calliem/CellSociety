package cellsociety;

import java.util.List;

import cell.Cell;

/**
 * This class is used to package Data and to pass parameters around more efficiently
 * 
 */
public class GridData {

	private Cell[][] myGrid;
	private int myRow;
	private int myCol;
	private Cell[][] myNewGrid;
	private List<Coordinate> myList;


	public GridData(Cell[][] grid, int row, int col, Cell[][] newGrid,
			List<Coordinate> list) {

		myGrid = grid;
		myRow = row;
		myCol = col;
		myNewGrid = newGrid;
		myList = list;

	}

	public Cell[][] getGrid() {
		return myGrid;
	}

	public Cell curCell() {
		return myGrid[myRow][myCol];
	}

	/*
	public Cell newCell(){
		return myNewGrid[myRow][myCol];
	}
*/
	
	public Coordinate curCoord(){;
		return new Coordinate(myRow, myCol);

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



	public List<Coordinate> getList() {

		return myList;
	}

	public void updateLocation(Cell cell) {
		myNewGrid[myRow][myCol] = cell;
	}

	public boolean sameLocation(Coordinate newLocation) {
		return myRow == newLocation.getX() && myCol == newLocation.getY();
	}

}
