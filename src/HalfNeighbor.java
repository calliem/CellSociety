

import java.util.ArrayList;
import java.util.Collections;


public class HalfNeighbor extends Neighbor {

	protected Boundary myBounds;
	
	public HalfNeighbor(Boundary bounds){
		myBounds = bounds;
	}
	
	@Override
	public ArrayList<Cell> getNeighbors(Cell[][] grid, int r, int c){
		ArrayList<Cell> list = new ArrayList<Cell>();
		for(int d = -1; d <= 1; d++){
			//addToList(grid, r, c, d);
			list.add(myBounds.findCell(grid, r+d, c));
			list.add(myBounds.findCell(grid, r, c+d));
			/*
			if(myBounds.inBounds(grid, r+d, c)){
				list.add(grid[r+d][c]);
			}
			if(myBounds.inBounds(grid, r, c+d)){
				list.add(grid[r][c+d]);
			}
			*/
		}
		list.removeAll(Collections.singleton(null));
		return list;
	}
}
