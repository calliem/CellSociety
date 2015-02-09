package neighbor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import boundary.Boundary;
import cell.Cell;

public class HexagonalNeighbor extends HalfNeighbor {

	public HexagonalNeighbor(Boundary bounds) {
		super(bounds);
	}

	@Override
	public List<Integer[]> getNeighbors(Cell[][] grid, int r, int c, int scalar) {
		List<Integer[]> list = new ArrayList<Integer[]>();
		list.addAll(super.getNeighbors(grid, r, c, scalar));
		if (r % 2 == 0) {
			list.addAll(makeCorners(grid, r, c, -1, scalar));
		} else {
			list.addAll(makeCorners(grid, r, c, 1, scalar));
		}
		return list;
	}

	private List<Integer[]> makeCorners(Cell[][] grid, int r, int c, int d, int scalar) {
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		for (int s = 1; s <= scalar; s++) {
			list.add(myBounds.findCell(grid, r - d * s, c + d * s));
			list.add(myBounds.findCell(grid, r + d * s, c + d * s));
			list.removeAll(Collections.singleton(null));
		}
		return list;
	}

}
