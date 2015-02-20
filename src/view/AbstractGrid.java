// This entire file is part of my masterpiece.
// Le Qi

package view;

import java.util.Arrays;
import java.util.stream.Stream;

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
		Arrays.stream(shapes).forEach(s -> Arrays.stream(s).forEach(m -> myGrid.getChildren().add(m)));
	}

	protected abstract Shape[][] populateGrid(double gridSize, Cell[][] cells);

}
