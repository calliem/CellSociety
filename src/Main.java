



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	CellSociety mySimulation;
	
	@Override
	public void start(Stage s) throws Exception {
		mySimulation = new CellSociety(s);
		
		/*
		int cellSize = 10;
		
		Cell[][] cells = new Cell[cellSize][cellSize];
		
		for (int i = 0; i < cellSize; i++) {
			for (int j = 0; j < cellSize; j++) {
				cells[i][j] = new Cell();
			}
		}
		
		TriangleGrid grid = new TriangleGrid(CellSocietyView.GRID_SIZE, cells);
		Scene scene = new Scene(grid.getGroup());
		s.setScene(scene);
		s.show();
		cells[5][5].setColor(Color.YELLOW);
		grid.update(cells);
		*/
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

