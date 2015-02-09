package view;
import cell.Cell;
import javafx.scene.Group;
import javafx.scene.shape.Shape;

public abstract class AbstractGrid {

	private Group myGrid;
	private Shape[][] myShapes;
	
	public AbstractGrid(double gridSize, Cell[][] cells) {
		myGrid = new Group();
		myShapes = populateGrid(gridSize, cells);
		displayGrid(myShapes);
	}
	
	public Group getGrid() {
		return myGrid;
	}
	
	public void update(Cell[][] cells) {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++)
				myShapes[i][j].setFill(cells[i][j].getColor());
		}
		displayGrid(myShapes);
	}
	
	private void displayGrid(Shape[][] shapes) {
		myGrid.getChildren().clear();
		for (int i = 0; i < shapes.length; i++) {
			for (int j = 0; j < shapes[0].length; j++) {
				myGrid.getChildren().add(shapes[i][j]);
			}
		}
	}
	
	protected abstract Shape[][] populateGrid(double gridSize, Cell[][] cells);
	
}
