
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CellSociety{

	private CellSocietyView myView;
	private XMLParser myParser;
	private int myFrameRate;
	private SimController myController;
	private Timeline myTimeline;
	private Cell[][] myCells;
	
	public CellSociety(Stage s) throws ParserConfigurationException, SAXException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {
		
        myParser = new XMLParser();
		myParser.parseXMLFile(new File("src/life.xml")); //this should only be called when you click uploadXML
		myView = new CellSocietyView(s, myParser);
		configureListeners();
		
		// or however it's decided
		
		myCells = myView.createCellArray();
		myController = new LifeController();
		myView.updateSimGrid(myCells);
		
		/* get FrameRate and initial settings */
		myFrameRate = 100;	// actually get from XMLParser
		//parser is instantiated in the cellsocietyview. it will have to be moved here if it is to setup the framrate here. 
		
		//is starting this here a bit weird?
		
		KeyFrame frame = start(myFrameRate);
		myTimeline = new Timeline();
		myTimeline.setCycleCount(Animation.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
	}
	
	private void configureListeners() {
		myView.getPlayElement().setOnMouseClicked(e -> resumeAnimation());
		myView.getPauseElement().setOnMouseClicked(e -> pauseAnimation());
		myView.getStepElement().setOnMouseClicked(e -> stepAnimation());
		myView.getXMLElement().setOnMouseClicked(e -> readNewXML());
	}
	
	public KeyFrame start(int frameRate) {
		return new KeyFrame(Duration.millis(1000 / frameRate),
				e -> updateCellStates());
	}
	
	private void updateCellStates() {
		
		try {
			myCells = myController.runOneSim(myCells);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myView.updateSimGrid(myCells);
	}
		
	private void resumeAnimation() {
		myView.getPlayElement().setDisable(true);		// ideally abstracted to view later
		myView.getPauseElement().setDisable(false);		// ideally abstracted to view later
		myView.getStepElement().setDisable(true);		// ideally abstracted to view later
		myTimeline.play();
		
	}
	
	private void pauseAnimation() {
		myView.getPlayElement().setDisable(false);		// ideally abstracted to view later
		myView.getPauseElement().setDisable(true);		// ideally abstracted to view later
		myView.getStepElement().setDisable(false);		// ideally abstracted to view later
		myTimeline.pause();
	}
	
	private void stepAnimation() {
		
	}
	
	private void readNewXML() {
		
	}
	

}