import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage s) throws Exception {

		CellSociety cs = new CellSociety(s);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

