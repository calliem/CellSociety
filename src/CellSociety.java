import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;


public class CellSociety {
	
	private Group myRoot;
	private SimController myController;
	private Cell[][] myGrid;
	
	public CellSociety(SimController simController){
		myController = simController;
	}
	
	/**
	 * Create the game's scene
	 */
	public Scene init(Stage s, int width, int height){
		Scene scene = new Scene(myRoot, width, height);
		return scene;
	}
	
	public KeyFrame start (int frameRate){
		return new KeyFrame(Duration.millis(1000/frameRate), e -> updateSimulation());
	}
	
	public void updateSimulation(){
			myController.runOneSim(myGrid);
	}
}
