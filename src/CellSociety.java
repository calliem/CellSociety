
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CellSociety {

	private CellSocietyView myView;
	private XMLParser myParser;
	private int myFrameRate;
	private Controller myController;
	private Timeline myTimeline;
	private Cell[][] myCells;
	private int myNumFrames = 0;
	private Random myRandom = new Random();

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
			IllegalArgumentException, NoSuchMethodException, SecurityException,
			InvocationTargetException {
		String state = cellParams.get("state");
		Constructor<?> constructor;
		try {
			if (state.equals("SharkCell") | state.equals("FishCell")) {
				Class<?> className = Class.forName(state);
				constructor = className.getConstructor(Map.class);
			} else if (state.equals("Cell")) {
				constructor = Class.forName("Cell").getConstructor(Map.class);

			} else {
				// constructor =
				// Class.forName("Cell").getConstructor(Map.class); //doing this
				// here makes the code runnable still
				throw new XMLParserException("Invalid cell state: %s", state); // duplicated
			}
		} catch (ClassNotFoundException e) { // null pointer as well?
			System.out.println("I just threw an exception");
			throw new XMLParserException("Invalid cell state: %s", state);
		}
		// WRITE A CATCH NULL POINTER AND THAT MEANS THAT THE CELL STATE WAS
		// ENTERED INCORRECTLY
		// System.out.println("ClassName:  " + className.toString());
		// this is a duplicated if statement; it appears in the code in the
		// controllers i think

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
		System.out.println("sze" + cellStates.size());
		if (cellStates.size() > 1) { // if there are at least 2 different cell
			// types
			if (cellStates.get(1).get("loc") == null
					&& cellStates.get(1).get("locProbability") != null) {
				System.out.println("populateProbabilities");
				populateProbabilities(cellStates, numCols, numRows);
			} else if (cellStates.get(1).get("loc") != null
					&& cellStates.get(1).get("locProbability") == null) {
				populateLocations(cellStates, numCols, numRows);
			} else
				throw new XMLParserException(
						"Please specify cell locations or location probabilities");
			// is this actually thrown and caught? LILA
		}

		populateRemainingState(cellStates, numCols, numRows);

		return myCells;
	}

	// instantiates cells for all states except for the first one (which
	// will be automatically done below)
	private void populateProbabilities(
			List<HashMap<String, String>> cellStates, int numCols, int numRows)
					throws InstantiationException, IllegalAccessException,
					IllegalArgumentException, NoSuchMethodException, SecurityException,
					InvocationTargetException {
		for (int i = 1; i < cellStates.size(); i++) {
			HashMap<String, String> cellParams = cellStates.get(i);

			double prob = Double.parseDouble(cellParams.get("locProbability"));
			int numCells = (int) (numRows * numCols * prob); //number of cells to be randomly filled in

			for (int j = 0; j < numCells; j ++){ //specify number of cells to randomly select
				int randomX; int randomY;
				////System.out.println(numRows * numCols);
				////System.out.println(myRandom.nextInt(100));
				do{
					int randomLoc = myRandom.nextInt(numRows * numCols);
					randomX = randomLoc / numCols;
					randomY = randomLoc % numCols;
				} while (myCells[randomX][randomY] != null);
				Cell cell = createCellInstance(cellParams);
				myCells[randomX][randomY] = cell;
			}
		}
	}

	// instantiates cells for all states except for the first one (which
	// will be automatically done below)
	/**
	 * Populates a grid of cells with all given states except for the 1st.
	 * 
	 * @param cellStates
	 * @param numCols
	 * @param numRows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 */
	private void populateLocations(List<HashMap<String, String>> cellStates,
			int numCols, int numRows) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			NoSuchMethodException, SecurityException, InvocationTargetException {
		for (int i = 1; i < cellStates.size(); i++) { // this may be able to be
			// written outside of
			// this method
			HashMap<String, String> cellParams = cellStates.get(i);
			int[] locations = stringToIntArray(cellParams.get("loc"));
			List<Integer> invalidLocs = new ArrayList<Integer>();
			// boolean isInvalid = false;
			if (locations != null) {
				for (int j = 0; j < locations.length; j++) {
					//System.out.println(cellParams.get("state") + locations[j]);
					if (locations[j] > numCols * numRows){
						invalidLocs.add(locations[j]);
					}
					int row = locations[j] / numCols;
					int col = locations[j] % numCols;
					Cell cell = createCellInstance(cellParams);
					try {
						myCells[row][col] = cell;
					} catch (ArrayIndexOutOfBoundsException e) {
						throw new XMLParserException(
								"Invalid cell locations: %s", invalidLocs);
					}

				}
			}
		}
	}

	/**
	 * Populates all remaining empty cells in the grid with the first cell state
	 * given in the XML file
	 * 
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private void populateRemainingState(
			List<HashMap<String, String>> cellStates, int numCols, int numRows)
					throws InstantiationException, IllegalAccessException,
					IllegalArgumentException, NoSuchMethodException, SecurityException,
					InvocationTargetException {
		for (int x = 0; x < numRows; x++) {
			for (int y = 0; y < numCols; y++) {
				if (myCells[x][y] == null) {
					HashMap<String, String> remainingCellParams = cellStates
							.get(0);
					myCells[x][y] = createCellInstance(remainingCellParams);
				}
			}
		}
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
	IllegalAccessException, InvocationTargetException {
		//System.out.println("parser!");
		myFrameRate = Integer.parseInt(myParser.getInitParamMap().get("fps"));

		String simName = myParser.getInitParamMap().get("simName");
		String className = simName + "Controller";
		// Class<?> currentClass;
		try {
			Class<?> currentClass = Class.forName(className);
			Constructor<?> constructor = currentClass.getConstructor(Map.class);
			//System.out.println(constructor);
			myController = (Controller) constructor.newInstance(myParser
					.getSimParamMap());			
		} catch (ClassNotFoundException e) {
			//System.out.println("HALLO");
			throw new XMLParserException("Invalid simulation: %s", simName); //LILA
		}
	}
	
	private void configureListeners() throws IOException {
		myView.getPlayElement().setOnMouseClicked(e -> resumeAnimation());
		myView.getPauseElement().setOnMouseClicked(e -> pauseAnimation());
		myView.getStepElement().setOnMouseClicked(e -> updateGrid());
		myView.getXMLElement().setOnMouseClicked(e -> readNewXML());
		myView.getSpeedupElement().setOnMouseClicked(e -> increaseFrameRate());
		myView.getSlowdownElement().setOnMouseClicked(e -> decreaseFrameRate());
		// myView.setErrorText(); // XML Parser should call this when file
		// format
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

		//System.out.println("=========1) cell grid in updateGrid()===============");
		//System.out.println(myCells.length);
		//System.out.println(myCells[0].length);

		for (int i = 0; i < myCells.length; i++) {
			for (int j = 0; j < myCells[0].length; j++) {
				//System.out.println(myCells[i][j].toString());
			}
		}
		try {
			myCells = myController.runOneSim(myCells); // THIS IS MAKING
			// EVERYTHING NULL
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}

		//System.out.println("=========2) cell grid in updateGrid()===============");
		//System.out.println(myCells.length);
		//System.out.println(myCells[0].length);

		for (int i = 0; i < myCells.length; i++) {
			for (int j = 0; j < myCells[0].length; j++) {
				//System.out.println(myCells[i][j].toString());
			}
		}

		updateColors();
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

	// this is inefficient and sucks design-wise
	private void updateColors() {
		for (int i = 0; i < myCells.length; i++) {
			for (int j = 0; j < myCells[i].length; j++) {
				String cellName = myCells[i][j].toString();
				List<HashMap<String, String>> cellList = myParser
						.getCellParamList();
				for (HashMap<String, String> params : cellList) {
					if (params.get("name").equals(cellName))
						myCells[i][j].setColor(Color.valueOf(params
								.get("color")));
				}

			}
		}
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
		// test
		try {
			myParser.parseXMLFile(newFile);
			retrieveParserInfo();
			Cell.setCellSize(
					CellSocietyView.GRID_SIZE
					/ Integer.parseInt(myParser.getInitParamMap().get(
							"xRows")),
							CellSocietyView.GRID_SIZE
							/ Integer.parseInt(myParser.getInitParamMap().get(
									"yCols")));
			myCells = createCellArray();
		} catch (IllegalArgumentException | ClassNotFoundException
				| SecurityException | ParserConfigurationException
				| SAXException | IOException | NoSuchMethodException
				| InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
		} catch (XMLParserException e) {
			// System.out.println(e.getMessage()); // LILA this should not appear												// twice...?
			return; //test this LILA
		}
		// if make above into one catch, then it will not printout all of the
		// error messages but only the first one? LILA
		// move it to just make one try catch
		setupAnimation();
		myView.setupInitialGrid(myCells, myParser.getInitParamMap().get("shape"));
		pauseAnimation();
		myFrameRate = Integer.parseInt(myParser.getInitParamMap().get("fps"));
		myView.displayFrameRate(myFrameRate);

		String[] cellNames = new String[myParser.getCellParamList().size()];
		String[] cellColors = new String[myParser.getCellParamList().size()];
		List<HashMap<String, String>> cellParams = myParser.getCellParamList();
		for (int i = 0; i < cellParams.size(); i++) {
			Map<String, String> cur = cellParams.get(i);
			for (String s : cur.keySet()) {
				//System.out.println(s + " : " + cur.get(s));
			}
			cellNames[i] = cur.get("name");
			cellColors[i] = cur.get("color");
		}

		//System.out.println(Arrays.toString(cellNames));

		myNumFrames = 0;
		myView.generateChartLines(cellNames);
		myView.updateChartLines(myCells, myNumFrames, cellNames);

		String gridLines = myParser.getInitParamMap().get("gridLines");
		/*

		if (gridLines != null)
			myView.setGridGap(Integer.parseInt(gridLines));
		else
			myView.setGridGap(1);
		 */
	}

	private int[] stringToIntArray(String string) {
		string = string.replaceAll("\\s+", " ");
		String[] split = string.split(" ");
		if (!string.equals(" ") && !string.equals("")) {
			int[] intArray = new int[split.length];
			for (int i = 0; i < split.length; i++) {
				//System.out.println("split[i]" + split[i]);
				if (!split[i].equals("") && !split[i].equals(" ")) {
					//System.out.println("STR" + split[i]);
					intArray[i] = Integer.parseInt(split[i]);
				}
			}
			return intArray;
		}
		
	 else
		return null;

	}
}
