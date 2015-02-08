
import java.util.ArrayList;
import java.util.Collections;


public class OldHalfNeighbor extends ScalableNeighbor {

protected Boundary myBounds;
	
	public OldHalfNeighbor(Boundary bounds){
		myBounds = bounds;
	}
	
	@Override
	public ArrayList<Cell> getNeighbors(Cell[][] grid, int r, int c){
		ArrayList<Cell> list = new ArrayList<Cell>();
		for(int d = -1; d <= 1; d++){
			//addToList(grid, r, c, d);
			list.add(myBounds.findCell(grid, r+d, c));
			list.add(myBounds.findCell(grid, r, c+d));
		}
		list.removeAll(Collections.singleton(null));
		return list;
	}
}
