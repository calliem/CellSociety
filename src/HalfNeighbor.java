import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HalfNeighbor extends Neighbor{

	protected Boundary myBounds;

	public HalfNeighbor(Boundary bounds){
		myBounds = bounds;
	}

	@Override
	public List<Integer[]> getNeighbors(Cell[][] grid, int r, int c, int scalar){
		List<Integer[]> list = new ArrayList<Integer[]>();
		for(int s = 1; s <= scalar; s++){
			for(int d = -1; d <= 1; d++){
				//addToList(grid, r, c, d);
				list.add(myBounds.findCell(grid, r + d*s , c));
				list.add(myBounds.findCell(grid, r, c + d*s));
			}
		}
		list.removeAll(Collections.singleton(null));
		return list;
	}

}
