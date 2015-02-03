

import java.util.ArrayList;


public abstract class CardinalSimController extends SimController{

	@Override
	protected ArrayList<Cell> getNeighbors(Cell[][] grid, int r, int c){
		ArrayList<Cell> list = new ArrayList<Cell>();
		for(int d = -1; d <= 1; d++){
			
			//addToList(grid, r, c, d);
			if(inBounds(grid, r+d, c)){
				list.add(grid[r+d][c]);
			}
			if(inBounds(grid, r, c+d)){
				list.add(grid[r][c+d]);
			}
		}
		return list;
	}
/*
	private void addToList(Cell[][] grid, int r, int c) {
		for(int i = 0; i < 2; i++){
			if
		}
		
	}
	*/
}
