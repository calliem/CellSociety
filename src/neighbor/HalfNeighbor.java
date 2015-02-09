package neighbor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import boundary.Boundary;
import cell.Cell;

public class HalfNeighbor extends Neighbor {

	public HalfNeighbor(Boundary bounds) {
		super(bounds);
	}

	@Override
	public List<Integer[]> getNeighbors(Cell[][] grid, int r, int c, int scalar) {
		List<Integer[]> list = new ArrayList<Integer[]>();
		for (int s = 1; s <= scalar; s++) {
			for (int d = -1; d <= 1; d++) {
				list.add(getBounds().findCell(grid, r + d * s, c));
				list.add(getBounds().findCell(grid, r, c + d * s));
			}
		}
		list.removeAll(Collections.singleton(null));
		return list;
	}

}
