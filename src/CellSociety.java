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
import javafx.stage.Stage;
import javafx.util.Duration;

public class CellSociety {

	private CellSocietyView myView;
	private XMLParser myParser;
	private int myFrameRate;
	private SimController myController;
	private Timeline myTimeline;
	private Cell[][] myCells;
	
	private int count=0;
	
	public CellSociety(Stage s) throws ParserConfigurationException,
			SAXException, IOException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException,
			NoSuchMethodException, SecurityException {

		myParser = new XMLParser();
		myView = new CellSocietyView(s);
		
		configureListeners();
		//technically catch the errors here?
	}

	public Cell createCellInstance(Map<String, String> cellParams)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, ClassNotFoundException,
			NoSuchMethodException, SecurityException, InvocationTargetException {
		Class<?> className = Class.forName(cellParams.get("state"));
		//System.out.println("ClassName:  " + className.toString());
			Constructor<?> constructor = className.getConstructor(Map.class);
	//	System.out.println(constructor);
		return (Cell) constructor.newInstance(cellParams);
	}

	public Cell[][] createCellArray() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			ClassNotFoundException, NoSuchMethodException, SecurityException,
			InvocationTargetException {
		Map<String, String> map = myParser.getInitParamMap();

		int numCols = Integer.parseInt(map.get("yCols"));
		int numRows = Integer.parseInt(map.get("xRows"));

		myCells = new Cell[numRows][numCols];
		List<HashMap<String, String>> cellStates = myParser.getCellParamList();

		// instantiates cells for all states except for the first one (which
		// will be automatically done below)
		for (int i = 1; i < cellStates.size(); i++) {
			HashMap<String, String> cellParams = cellStates.get(i);
			for (String string : cellParams.keySet()) {
				int[] locations = stringToIntArray(cellParams.get("loc"));
				for (int j = 0; j < locations.length; j++) {
					int row = locations[j] / numCols;
					int col = locations[j] % numCols;
					Cell cell = createCellInstance(cellParams);
					myCells[row][col] = cell;
				}
			}
		}
				
		// sets all remaining cells (1st cell tag from the XML file)
		for (int x = 0; x < numRows; x++) {
			for (int y = 0; y < numCols; y++) {
				if (myCells[x][y] == null) {

					HashMap<String, String> remainingCellParams = cellStates
							.get(0);
					myCells[x][y] = createCellInstance(remainingCellParams);
				}
			}
		}
		return myCells;
	}

	private void setupAnimation() {
		KeyFrame frame = start(myFrameRate);
		myTimeline = new Timeline();
		myTimeline.setCycleCount(Animation.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
	}

	/**
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void retrieveParserInfo() throws NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		myFrameRate = Integer.parseInt(myParser.getInitParamMap().get("fps"));
		String className = myParser.getInitParamMap().get("simName")
				+ "Controller";
	//	Class<?> currentClass = null;
//		try{
		Class<?> currentClass = Class.forName(className);
	//	}catch (ClassNotFoundException e){
			System.out.println("ClassNotFoundException");
		//	throw new XMLException("Get from resource file: XMLSimNameError");
		//} //throw and then remove the throw classnotfoundexception
		System.out.println("currentclass" + currentClass);
		Constructor<?> constructor = currentClass.getConstructor(Map.class);
		System.out.println(constructor);
		myController = (SimController) constructor.newInstance(myParser
				.getSimParamMap());
	}

	private void configureListeners() throws IOException, ClassNotFoundException {
		myView.getPlayElement().setOnMouseClicked(e -> resumeAnimation());
		myView.getPauseElement().setOnMouseClicked(e -> pauseAnimation());
		myView.getStepElement().setOnMouseClicked(e -> updateGrid());
		myView.getXMLElement().setOnMouseClicked(e -> readNewXML());
		myView.getSpeedupElement().setOnMouseClicked(e -> increaseFrameRate());
		myView.getSlowdownElement().setOnMouseClicked(e -> decreaseFrameRate());
		// myView.setErrorText(); // XML Parser should call this when file format
	}

	private KeyFrame start(int frameRate) {
		return new KeyFrame(Duration.millis(1000 / frameRate),
				e -> updateGrid());
	}

	private void increaseFrameRate() {
		myFrameRate++;
		reframeTimeline();
	}

	private void decreaseFrameRate() {
		if (myFrameRate > 1)
			myFrameRate--;
		reframeTimeline();
		
	}
	
	private void reframeTimeline() {
		myView.displayFrameRate(myFrameRate);
		myTimeline.getKeyFrames().clear();
		myTimeline.getKeyFrames().add(start(myFrameRate));
	}

	private void updateGrid() {

		try {
			myCells = myController.runOneSim(myCells);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		count++;
		myView.updateSimGrid(myCells);
	}

	private void resumeAnimation() {
		myView.changeResumeButtonPermissions();
		myTimeline.play();
	}

	private void pauseAnimation() {
		myView.changePauseButtonPermissions();
		myTimeline.stop();
	}

	private void readNewXML() {
		
		File newFile = myView.displayXMLChooser();
		try {
			myParser.parseXMLFile(newFile);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		try {
			retrieveParserInfo();
		} catch (NoSuchMethodException | ClassNotFoundException //remove this classnotfoundexception here
				| InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		Cell.setCellSize(
				CellSocietyView.GRID_SIZE
						/ Integer.parseInt(myParser.getInitParamMap().get(
								"xRows")),
				CellSocietyView.GRID_SIZE
						/ Integer.parseInt(myParser.getInitParamMap().get(
								"yCols")));
		try {
			myCells = createCellArray();
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | ClassNotFoundException
				| NoSuchMethodException | SecurityException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		
		setupAnimation();
		myView.updateSimGrid(myCells);
		pauseAnimation();
		myFrameRate = Integer.parseInt(myParser.getInitParamMap().get("fps"));
		myView.displayFrameRate(myFrameRate);
	}
	


	private int[] stringToIntArray(String string) {
		String[] split = string.split(" ");
		int[] intArray = new int[split.length];
		// checks to ensure that it is not the first location parameter passed
		// in from the XML document (it is empty and will be made automatically)
		if (!string.equals("")) {
			for (int i = 0; i < split.length; i++) {
				intArray[i] = Integer.parseInt(split[i]);
			}
		}
		return intArray;
	}

}