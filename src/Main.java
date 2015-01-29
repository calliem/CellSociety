import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	CellSociety mySimulation;
	
	@Override
	public void start(Stage s) throws Exception {

		mySimulation = new CellSociety(s);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

