

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage s) throws Exception {
		// TODO Auto-generated method stub
		GameManager gm = new GameManager();
		gm.start(s);
	}
	
	public static void main(String[] args){
		launch(args);
	}
	
	
}
