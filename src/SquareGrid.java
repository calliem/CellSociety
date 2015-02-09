import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SquareGrid extends Grid {

	private Group myGrid;
	private Shape[][] myShapes;
	private double mySpacing;
	
	public SquareGrid(double gridSize, Cell[][] cells) {
		
		myGrid = new Group();
		myShapes = new Rectangle[cells.length][cells[0].length];
		mySpacing = gridSize / cells.length;
		populateGrid(cells);
		
	}
	
	private void populateGrid(Cell[][] cells) {
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {
				Shape shape = new Rectangle(i * mySpacing, j * mySpacing, mySpacing, mySpacing);
				Cell cell = cells[i][j];
				shape.setFill(cells[i][j].getColor());
				myGrid.getChildren().add(shape);
				shape.setOnMouseEntered(e -> setRed(shape));
				shape.setOnMouseExited(e -> setBack(shape, cell));
				myShapes[i][j] = shape;
			}
		}
		
	}

	private void setBack(Shape s, Cell c) {
		// TODO Auto-generated method stub
		s.setFill(c.getColor());
	}

	private void setRed(Shape s) {
		s.setFill(Color.RED);
	}
	
	public Group getGroup() {
		return myGrid;
	}
		
	public void update(Cell[][] cells) {
		
		myGrid.getChildren().clear();
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				myShapes[i][j] = new Rectangle(i * mySpacing, j * mySpacing, mySpacing, mySpacing);
				myShapes[i][j].setFill(cells[i][j].getColor());
				myGrid.getChildren().add(myShapes[i][j]);
			}
		}
		
	}
	
			
}
