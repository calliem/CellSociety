package neighbor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import boundary.Boundary;
import cell.Cell;
import cellsociety.Coordinate;

public class HexagonalNeighbor extends HalfNeighbor {

	public HexagonalNeighbor(Boundary bounds) {
		super(bounds);
	}

	@Override

	public List<Coordinate> getNeighbors(Cell[][] grid, int r, int c, int scalar){
		List<Coordinate> list = new ArrayList<Coordinate>();

		list.addAll(super.getNeighbors(grid, r, c, scalar));
		if (r % 2 == 0) {
			list.addAll(makeCorners(grid, r, c, -1, scalar));
		} else {
			list.addAll(makeCorners(grid, r, c, 1, scalar));
		}
		return list;
	}


	private Collection<? extends Coordinate> makeCorners(Cell[][] grid, int r, int c, int d, int scalar) {
		List<Coordinate> list = new ArrayList<Coordinate>();
		for(int s = 1; s <= scalar; s++){
			list.add(myBounds.findCell(grid, r - d*s, c + d*s));
			list.add(myBounds.findCell(grid, r + d*s, c + d*s));
			list.removeAll(Collections.singleton(null));
		}
		return list;
	}

}
