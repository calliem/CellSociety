

import java.util.ArrayList;
import java.util.Collections;


public class FullNeighbor extends Neighbor{
	
	private Boundary myBounds;
	
	public FullNeighbor(Boundary bounds){
		myBounds = bounds;
	}

	@Override
	public ArrayList<Cell> getNeighbors(Cell[][] grid, int row, int col) {
		ArrayList<Cell> list = new ArrayList<Cell>();
		for(int dr = -1; dr <= 1; dr++){
			for(int dc = -1; dc <= 1; dc++){
				if(dr != 0 || dc != 0){
					int nextRow = row + dr;
					int nextCol = col + dc;
					list.add(myBounds.findCell(grid, nextRow, nextCol));
					/*
					if(myBounds.inBounds(grid, nextRow, nextCol))
					list.add(grid[nextRow][nextCol]);
					*/
				}
			}
		}
		list.removeAll(Collections.singleton(null));
		return list;
	}

}
