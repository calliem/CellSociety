
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CellSociety{

	private CellSocietyView myView;
	private XMLParser myParser;
	private int myFrameRate;
	private SimController myController;
	private Timeline myTimeline;
	private Cell[][] myInitCellArray;
	
	public CellSociety(Stage s) throws ParserConfigurationException, SAXException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {
		
        myParser = new XMLParser();
		myParser.parseXMLFile(new File("src/life.xml")); //this should only be called when you click uploadXML

		myFrameRate = 2;	// actually get from XMLParser
		myInitCellArray = createDummyArray();
		// myInitCellArray = createCellArray();
		
		myView = new CellSocietyView(s, myInitCellArray, myFrameRate);
		configureListeners();
		
		// or however it's decided
		myController = new LifeController();
		
		/* get FrameRate and initial settings */
		//parser is instantiated in the cellsocietyview. it will have to be moved here if it is to setup the framrate here. 
		
		//is starting this here a bit weird?
		
		KeyFrame frame = start(myFrameRate);
		myTimeline = new Timeline();
		myTimeline.setCycleCount(Animation.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
	}
	
	private Cell[][] createDummyArray() {
		
		Cell[][] gridCell = new Cell[10][10];
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				gridCell[i][j] = new EmptyCell(Color.WHITE);
			}
		}
		
		gridCell[2][0] = new LiveCell(Color.BLACK);
		gridCell[2][1] = new LiveCell(Color.BLACK);
		gridCell[2][2] = new LiveCell(Color.BLACK);
		gridCell[1][2] = new LiveCell(Color.BLACK);
		gridCell[0][1] = new LiveCell(Color.BLACK);
		
		return gridCell;
	}
	
	private void configureListeners() throws IOException {
		myView.getPlayElement().setOnMouseClicked(e -> resumeAnimation());
		myView.getPauseElement().setOnMouseClicked(e -> pauseAnimation());
		myView.getStepElement().setOnMouseClicked(e -> stepAnimation());
		//myView.getXMLElement().setOnMouseClicked(e -> readNewXML());
		myView.setErrorText(); // XML Parser should call this when file format incorrect.
	}
	
	public KeyFrame start(int frameRate) {
		return new KeyFrame(Duration.millis(1000 / frameRate),
				e -> updateGrid());
	}

	// May be in a SimController subclass
	private void updateGrid() {
		
		try {
			myInitCellArray = myController.runOneSim(myInitCellArray);
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
		myView.updateSimGrid(myInitCellArray);
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
		myTimeline.play();
		myTimeline.pause();
	}
	
	private void readNewXML() throws ParserConfigurationException, SAXException, IOException {
		myParser.parseXMLFile(new File("src/life.xml")); //this should only be called when you click uploadXML
	}
	
	public Cell[][] createCellArray() throws InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, SecurityException, InvocationTargetException{
		Map<String, String> map = myParser.getSimParamMap();
		//use this for setting initial FPS/speed and stuff. Can be used in CellSociety instead; or will have to find a way to utilize it in both
		
		int numCols = Integer.parseInt(map.get("yCols"));
		int numRows = Integer.parseInt(map.get("xRows"));
		
	//	Cell[][] cellArray = new Cell[numRows][numCols];
		myInitCellArray = new Cell[numRows][numCols];
		
		List<CellState> cellStates = myParser.getCellStateList();
		Map<String, String> cellParams = myParser.getCellParamMap(); //this should just be an empty value for those without it
		//here Map = Map instead of Map = Hashmap. Is that okay or should it be changed (in the XMLParser class)?
		
		//instantiates cells for all states except for the first one (which will be automatically done)
		for (int i = 1; i < cellStates.size(); i++){ 
			CellState state = cellStates.get(i);
			String stateName = state.toString();
			System.out.println("stateName " + stateName);
			int[] locations = state.getLocations();
			for (int j = 0; j < locations.length; j++){ 
				int row = locations[j] / numCols;
				int col = locations[j] % numCols;
				System.out.println("stateName " + stateName + " location: " + locations[j] + " num: " + j + " row: " + row + " col: " + col);
				Cell cell = createCellInstance(stateName, state.getColor(), cellParams);
				myInitCellArray[row][col] = cell;
			}
		}
		
		//sets all remaining cells
		//this doesn't actually work yet 
		for (int x = 0; x < numRows; x ++){
			for (int y = 0; y < numCols; y++){
				if (myInitCellArray[x][y] == null){
					CellState remainingState = cellStates.get(0); 
					myInitCellArray[x][y] = createCellInstance(remainingState.toString(), remainingState.getColor(), cellParams); //this cellParams hashmap needs to be fixed
				}
			}
		}
		return myInitCellArray;
	}
	
	public Cell createCellInstance(String cellState, Color color, Map<String, String> cellParams) throws InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, SecurityException, InvocationTargetException{
        Class<?> className = Class.forName(cellState);
        System.out.println("ClassName:  " + className.toString());
        Constructor<?> constructor;
        if (cellParams.size() == 0)
        	 constructor = className.getConstructor(Color.class);           
        else
        	constructor = className.getConstructor(Color.class, Map.class);       
		System.out.println(constructor);
		return (Cell) constructor.newInstance(color);
	}
	

}