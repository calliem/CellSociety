package neighbor;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import boundary.Boundary;
import cell.Cell;


public class FullNeighbor extends Neighbor{

	

	public FullNeighbor(Boundary bounds){
		super(bounds);
	}

	@Override
	public List<Integer[]> getNeighbors(Cell[][] grid, int row, int col, int scalar) {
		List<Integer[]> list = new ArrayList<Integer[]>();
		for(int s = 1; s <= scalar; s++){
			for(int dr = -1; dr <= 1; dr++){
				for(int dc = -1; dc <= 1; dc++){
					if(dr != 0 || dc != 0){
						int nextRow = row + dr*s;
						int nextCol = col + dc*s;
						list.add(getBounds().findCell(grid, nextRow, nextCol));
						/*
					if(myBounds.inBounds(grid, nextRow, nextCol))
					list.add(grid[nextRow][nextCol]);
						 */
					}
				}
			}
		}
		list.removeAll(Collections.singleton(null));
		return list;
	}


}
