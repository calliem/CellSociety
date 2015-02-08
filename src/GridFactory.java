public class GridFactory {

	public static Grid createGrid(String shape, double gridSize, Cell[][] cells) {

		switch (shape) {
		case "square":
			return new SquareGrid(gridSize, cells);
		case "triangle":
			return new TriangleGrid(gridSize, cells);
		case "hexagon":
			return new HexagonGrid(gridSize, cells);
		default:
			return null;
		}
	}

}