package view;
import cell.Cell;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class TriangleGrid extends AbstractGrid {

	public TriangleGrid(double gridSize, Cell[][] cells) {
		// TODO Auto-generated constructor stub
		super(gridSize, cells);

	}

	@Override
	
	protected Shape[][] populateGrid(double gridSize, Cell[][] cells) {
		
		Shape[][] triangles = new Polygon[cells.length][cells[0].length];
		double hSpacing = gridSize / (0.5 * cells.length);
		double vSpacing = gridSize / cells[0].length;
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {
				Shape shape = generateTriangle(i, j, hSpacing, vSpacing);
				shape.setFill(cells[i][j].getColor());
				triangles[i][j] = shape;
			}
		}
		
		return triangles;
		
	}
		
	private Shape generateTriangle(int i, int j, double hSpacing, double vSpacing) {
				
		double iDouble = (double) i;
		double jDouble = (double) j;
		double[] points;
		
		if ((i + j) % 2 == 0) {
			points = new double[] {jDouble / 2 * hSpacing, iDouble * vSpacing,
				(jDouble + 1) / 2 * hSpacing, (iDouble + 1) * vSpacing, 
				(jDouble + 2) / 2 * hSpacing, iDouble * vSpacing};
		}
		else {
			points = new double[] {(jDouble) / 2 * hSpacing, (iDouble + 1) * vSpacing,
				(jDouble + 1) / 2 * hSpacing, iDouble * vSpacing,
				(jDouble + 2) / 2 * hSpacing, (iDouble + 1) * vSpacing};
		}
		
		return new Polygon(points);
		
	}
}
