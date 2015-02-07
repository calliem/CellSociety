import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
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
	private int myNumFrames = 0;

	//Remove this
	private int count = 0;
	
	public CellSociety(Stage s) throws ParserConfigurationException,
			SAXException, IOException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException,
			NoSuchMethodException, SecurityException {

		myParser = new XMLParser();
		myView = new CellSocietyView(s);
		configureListeners();
	}

	public Cell createCellInstance(Map<String, String> cellParams)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, ClassNotFoundException,
			NoSuchMethodException, SecurityException, InvocationTargetException {
		Class<?> className = Class.forName(cellParams.get("state"));
		//System.out.println("ClassName:  " + className.toString());
		Constructor<?> constructor = className.getConstructor(Map.class);
		//System.out.println(constructor);
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
		//	for (String string : cellParams.keySet()) {
				int[] locations = stringToIntArray(cellParams.get("loc"));
				for (int j = 0; j < locations.length; j++) {
					int row = locations[j] / numCols;
					int col = locations[j] % numCols;
					Cell cell = createCellInstance(cellParams);
					myCells[row][col] = cell;
			//	}
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
	private void retrieveParserInfo() throws ClassNotFoundException,
			NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		myFrameRate = Integer.parseInt(myParser.getInitParamMap().get("fps"));
		String className = myParser.getInitParamMap().get("simName")
				+ "Controller";
		Class<?> currentClass = Class.forName(className);
		//System.out.println("class" + currentClass);
		Constructor<?> constructor = currentClass.getConstructor(Map.class);
		//System.out.println(constructor);
		myController = (SimController) constructor.newInstance(myParser
				.getSimParamMap());
	}

	private void configureListeners() throws IOException {
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
		//System.out.println("Count: "+ count);
		count++;
		myView.updateSimGrid(myCells);
		
		String[] cellNames = new String[myParser.getCellParamList().size()];
		List<HashMap<String, String>> cellParams = myParser.getCellParamList();
		for (int i = 0; i < cellParams.size(); i++) {
			Map<String, String> cur = cellParams.get(i);
			cellNames[i] = cur.get("name");
		}
		
		myView.updateChartLines(myCells, myNumFrames, cellNames);
		myNumFrames++;
	}

	private void resumeAnimation() {
		myView.setButtonsPause(true);
		myTimeline.play();

	}

	private void pauseAnimation() {
		myView.setButtonsPause(false);
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
		} catch (ClassNotFoundException | NoSuchMethodException
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
		
		String[] cellNames = new String[myParser.getCellParamList().size()];
		List<HashMap<String, String>> cellParams = myParser.getCellParamList();
		for (int i = 0; i < cellParams.size(); i++) {
			Map<String, String> cur = cellParams.get(i);
			for (String s : cur.keySet()) {
				System.out.println(s + " : " + cur.get(s));
			}
			cellNames[i] = cur.get("name");
		}
		
		System.out.println(Arrays.toString(cellNames));
		
		myView.generateChartLines(cellNames);
		myView.updateChartLines(myCells, myNumFrames, cellNames);
		myView.openDialogBox("HI");
		
	}

	private int[] stringToIntArray(String string) {
		//System.out.println("STRING:" + string);
		String[] split = string.split(" ");
		int[] intArray = new int[split.length];
		// checks to ensure that it is not the first location parameter passed
		// in from the XML document (it is empty and will be made automatically)
		if (!string.equals("")) {
			for (int i = 0; i < split.length; i++) {
				//System.out.println("SPLIT:" + split[i]);
				//System.out.println("split length:" + split[i].length());
				if (split[i] != ""){
					intArray[i] = Integer.parseInt(split[i]);
				}
			}
		}
		return intArray;
	}

}