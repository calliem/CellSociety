
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class HexagonalNeighbor extends HalfNeighbor {


	
	public HexagonalNeighbor(Boundary bounds) {
		//myBounds = bounds;
		super(bounds);
	}
	
	@Override
	public ArrayList<Cell> getNeighbors(Cell[][] grid, int r, int c){
		ArrayList<Cell> list = new ArrayList<Cell>();
		list.addAll(super.getNeighbors(grid, r, c));
		if(r % 2 == 0) {
			list.addAll(makeCorners(grid, r, c, -1));
		}
		else{
			list.addAll(makeCorners(grid, r, c, 1));
		}
		return list;
	}

	private Collection<? extends Cell> makeCorners(Cell[][] grid, int r, int c, int d) {
		ArrayList<Cell> list = new ArrayList<Cell>();
		list.add(myBounds.makeCell(grid, r-d, c+d));
		list.add(myBounds.makeCell(grid, r+d, c+d));
		list.removeAll(Collections.singleton(null));
		return list;
	}

}
