import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class TriangleGrid extends Grid {

	private Group myGrid;
	private Shape[][] myShapes;
	private double myHSpacing;
	private double myVSpacing;

	public TriangleGrid(double gridSize, Cell[][] cells) {
		// TODO Auto-generated constructor stub
		myGrid = new Group();
		myShapes = new Polygon[cells.length][cells[0].length];
		myHSpacing = gridSize / (0.5 * cells.length + 0.5);
		myVSpacing = gridSize / cells[0].length;
		
		update(cells);
	
	}

	@Override
	public void update(Cell[][] cells) {
		// TODO Auto-generated method stub
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				Shape shape = generateTriangle(i, j);
				Cell cell = cells[i][j];
				shape.setFill(cells[i][j].getColor());
				myGrid.getChildren().add(shape);
				shape.setOnMouseEntered(e -> setRed(shape));
				shape.setOnMouseExited(e -> setBack(shape, cell));
				myShapes[i][j] = shape;
			}
		}
		
	}
	
	public Group getGroup() {
		return myGrid;
	}
	
	private void setBack(Shape s, Cell c) {
		// TODO Auto-generated method stub
		s.setFill(c.getColor());
	}

	private void setRed(Shape s) {
		s.setFill(Color.RED);
	}
	
	private Shape generateTriangle(int i, int j) {
				
		double iDouble = (double) i;
		double jDouble = (double) j;
		double[] points;
		
		if ((i + j) % 2 == 0) {
			points = new double[] {jDouble / 2 * myHSpacing, iDouble * myVSpacing,
				(jDouble + 1) / 2 * myHSpacing, (iDouble + 1) * myVSpacing, 
				(jDouble + 2) / 2 * myHSpacing, iDouble * myVSpacing};
		}
		else {
			points = new double[] {(jDouble) / 2 * myHSpacing, (iDouble + 1) * myVSpacing,
				(jDouble + 1) / 2 * myHSpacing, iDouble * myVSpacing,
				(jDouble + 2) / 2 * myHSpacing, (iDouble + 1) * myVSpacing};
		}
		
		return new Polygon(points);
		
	}
}
