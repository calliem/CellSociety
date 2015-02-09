public class GridFactory {

	public static AbstractGrid createGrid(String shape, double gridSize, Cell[][] cells) {

		switch (shape) {
		case "square":
			return new SquareGrid(gridSize, cells);
		case "triangle":
			return new TriangleGrid(gridSize, cells);
		default:
			return null;
		}
	}

}