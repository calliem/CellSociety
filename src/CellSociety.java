
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CellSociety {

	private CellSocietyView myView;
	private XMLParser myParser;
	private int myFrameRate;
	private SimController myController;
	
	public CellSociety(Stage s) throws ParserConfigurationException, SAXException, IOException {
		
		myView = new CellSocietyView(s);
		myParser = new XMLParser();
		configureListeners();
		
		// or however it's decided
		myController = new LifeController();
		
		/* get FrameRate and initial settings */
		myFrameRate = 100;	// actually get from XMLParser
		
		KeyFrame frame = start(myFrameRate);
		Timeline animation = new Timeline();
		animation.setCycleCount(Animation.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		
	}
	
	private void configureListeners() {
		myView.getPlayElement().setOnMouseClicked(e -> resumeAnimation());
		myView.getPauseElement().setOnMouseClicked(e -> pauseAnimation());
		myView.getStepElement().setOnMouseClicked(e -> stepAnimation());
		myView.getXMLElement().setOnMouseClicked(e -> readNewXML());
	}
	
	public KeyFrame start(int frameRate) {
		return new KeyFrame(Duration.millis(1000 / frameRate),
				e -> updateGrid());
	}
	
	// May be in a SimController subclass
	private void updateGrid() {
		
	}
	
	private void resumeAnimation() {
		myView.getPlayElement().setDisable(true);		// ideally abstracted to view later
		myView.getPauseElement().setDisable(false);		// ideally abstracted to view later
		myView.getStepElement().setDisable(true);		// ideally abstracted to view later
	}
	
	private void pauseAnimation() {
		myView.getPlayElement().setDisable(false);		// ideally abstracted to view later
		myView.getPauseElement().setDisable(true);		// ideally abstracted to view later
		myView.getStepElement().setDisable(false);		// ideally abstracted to view later
	}
	
	private void stepAnimation() {
		
	}
	
	private void readNewXML() {
		
	}
	
}