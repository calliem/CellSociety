package view;
import cell.Cell;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SquareGrid extends AbstractGrid {
	
	public SquareGrid(double gridSize, Cell[][] cells) {
		super(gridSize, cells);
	}
	
	protected Shape[][] populateGrid(double gridSize, Cell[][] cells) {
		
		double spacing = gridSize / cells.length;

		Shape[][] rectangles = new Rectangle[cells.length][cells[0].length];
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {
				Shape shape = new Rectangle(j * spacing, i * spacing, spacing, spacing);
				shape.setFill(cells[i][j].getColor());
				rectangles[i][j] = shape;
			}
		}
		
		return rectangles;
		
	}
			
}
