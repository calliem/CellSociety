//callie_branch
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.application.Application;

import javafx.scene.paint.Color;

import javafx.stage.FileChooser;

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
			myParser.parseXMLFile(new File("src/fire.xml")); //this should only be called when you click uploadXML

			myFrameRate = Integer.parseInt(myParser.getInitParamMap().get("fps"));	// actually get from XMLParser
			myInitCellArray = createCellArray();
			
			myView = new CellSocietyView(s, myInitCellArray, myFrameRate);
			configureListeners();
			
			String className = myParser.getInitParamMap().get("simName") + "Controller";
			Class<?> currentClass = Class.forName(className);
			System.out.println(currentClass.toString());
			Constructor<?> constructor = currentClass.getConstructor(Map.class); //how to use reflection on a map?
			System.out.println(constructor);
			myController = (SimController) constructor.newInstance(myParser.getSimParamMap());
			
			
			// or however it's decided
		//	myController = new FireController(myParser.getSimParamMap());
			myView.updateSimGrid(myInitCellArray);

			/* get FrameRate and initial settings */
			//parser is instantiated in the cellsocietyview. it will have to be moved here if it is to setup the framrate here. 		
		
			KeyFrame frame = start(myFrameRate); //should this be from the parser?
			myTimeline = new Timeline();
			myTimeline.setCycleCount(Animation.INDEFINITE);
			myTimeline.getKeyFrames().add(frame);
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
	

	//this method is not used at all
	private void readNewXML(File file) throws ParserConfigurationException, SAXException, IOException {
		myParser.parseXMLFile(new File("src/fire.xml")); //this should only be called when you click uploadXML
	}
	
	public Cell[][] createCellArray() throws InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, SecurityException, InvocationTargetException{
		Map<String, String> map = myParser.getInitParamMap();
		
		int numCols = Integer.parseInt(map.get("yCols"));
		int numRows = Integer.parseInt(map.get("xRows"));
		
	//	Cell[][] cellArray = new Cell[numRows][numCols];
		myInitCellArray = new Cell[numRows][numCols];
		
		List<HashMap<String, String>> cellStates = myParser.getCellParamList();
	//	Map<String, String> cellParams = myParser.getCellParamList(); //this should just be an empty value for those without it
		//here Map = Map instead of Map = Hashmap. Is that okay or should it be changed (in the XMLParser class)?
		
		//instantiates cells for all states except for the first one (which will be automatically done)
		
		//iterate through the list of cell states
		for (int i = 1; i < cellStates.size(); i ++){
			HashMap<String, String> cellParams = cellStates.get(i);
			for (String string: cellParams.keySet()){
				Color color = Color.valueOf(cellParams.get("color"));
				int[] locations = stringToIntArray(cellParams.get("loc"));
				for (int j = 0; j < locations.length; j++){ 
					int row = locations[j] / numCols;
					int col = locations[j] % numCols;
				//	System.out.println("stateName " + cellParams.get("state") + " location: " + cellParams.get("loc") + " num: " + j + " row: " + row + " col: " + col);
					Cell cell = createCellInstance(cellParams.get("state"), color, cellParams); //this is probably wrong because it creates the instance with the same color multiple times
					myInitCellArray[row][col] = cell;
				}

				System.out.println("color" + color);
				System.out.println(i + " " + string + " " + cellStates.get(i));
			System.out.println("----------");
			}
		}
		//sets all remaining cells

		for (int x = 0; x < numRows; x ++){
			for (int y = 0; y < numCols; y++){
				if (myInitCellArray[x][y] == null){
					HashMap<String, String> remainingCellParams = cellStates.get(1);
					Color remainingColor = Color.valueOf(remainingCellParams.get("color"));
					String remainingState = remainingCellParams.get("state");
					
					myInitCellArray[x][y] = createCellInstance(remainingState, remainingColor, remainingCellParams); //this cellParams hashmap needs to be fixed
				}
			}
		}
		return myInitCellArray;
	}
	
	private int[] stringToIntArray(String string){
		String[] split = string.split(" ");
		for (String s: split){
			System.out.println(s);
		}
	    int[] intArray = new int[split.length];
	    if (!string.equals("")){ //checks to ensure that it is not the first location parameter passed in from the XML document (it is empty and will be made automatically)
	    	for (int i = 0; i < split.length; i++) {
	    		intArray[i] = Integer.parseInt(split[i]);
	    	}
	    }
	    return intArray;	
	}
	
	public Cell createCellInstance(String cellState, Color color, Map<String, String> cellParams) throws InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, SecurityException, InvocationTargetException{
        Class<?> className = Class.forName(cellState);
    //    System.out.println("ClassName:  " + className.toString());
        Constructor<?> constructor;
        if (cellParams.size() == 0)
        	 constructor = className.getConstructor(Color.class);           
        else
        	constructor = className.getConstructor(Color.class);       
	//	System.out.println(constructor);
		return (Cell) constructor.newInstance(color);
	}
	

}