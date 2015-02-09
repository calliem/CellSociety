

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FullNeighbor extends Neighbor{

	private Boundary myBounds;

	public FullNeighbor(Boundary bounds){
		myBounds = bounds;
	}

	@Override
	public List<Coordinate> getNeighbors(Cell[][] grid, int row, int col, int scalar) {
		List<Coordinate> list = new ArrayList<Coordinate>();
		for(int s = 1; s <= scalar; s++){
			for(int dr = -1; dr <= 1; dr++){
				for(int dc = -1; dc <= 1; dc++){
					if(dr != 0 || dc != 0){
						int nextRow = row + dr*s;
						int nextCol = col + dc*s;
						list.add(myBounds.findCell(grid, nextRow, nextCol));
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

	public Boundary getBoundary(){
		return myBounds;
	}

}
