package cellsociety;

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

import controller.Controller;
import cell.Cell;
import view.CellSocietyView;
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

	public CellSociety(Stage s) throws ParserConfigurationException, SAXException,
			IOException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, ClassNotFoundException,
			NoSuchMethodException, SecurityException {
		myParser = new XMLParser();
		myView = new CellSocietyView(s);
		configureListeners();
	}

	public Cell createCellInstance(Map<String, String> cellParams)
			throws InvocationTargetException, IllegalAccessException,
			InstantiationException {
		String state = cellParams.get(Strings.CELL_STATE);
		Constructor<?> constructor = null;
		try {
			if (state.equals(Strings.SHARK_CELL) | state.equals(Strings.FISH_CELL)) {
				Class<?> className = Class.forName(Strings.CELL_PACKAGE + state);
				constructor = className.getConstructor(Map.class);
			} else
				constructor = Class.forName(Strings.CELL_PACKAGE + state).getConstructor(
						Map.class);
		} catch (ClassNotFoundException e) {
			throw new XMLParserException(Strings.INVALID_CELL_STATE_ERROR, state);
		} catch (IllegalArgumentException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return (Cell) constructor.newInstance(cellParams);
	}

	public Cell[][] createCellArray() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, SecurityException,
			InvocationTargetException, NoSuchMethodException {
		Map<String, String> map = myParser.getInitParamMap();

		int numCols = Integer.parseInt(map.get(Strings.COLUMNS));
		int numRows = Integer.parseInt(map.get(Strings.ROWS));

		myCells = new Cell[numRows][numCols];
		List<HashMap<String, String>> cellStates = myParser.getCellParamList();
		if (cellStates.size() > 1) {
			if (cellStates.get(1).get(Strings.CELL_LOCATION) == null
					&& cellStates.get(1).get(Strings.CELL_LOCATION_PROBABILITY) != null) {
				populateProbabilities(cellStates, numCols, numRows);
			} else if (cellStates.get(1).get(Strings.CELL_LOCATION) != null
					&& cellStates.get(1).get(Strings.CELL_LOCATION_PROBABILITY) == null) {
				populateLocations(cellStates, numCols, numRows);
			} else
				throw new XMLParserException(Strings.NO_CELL_LOCATION_ERROR);
		}
		populateRemainingState(cellStates, numCols, numRows);
		return myCells;
	}

	/**
	 * Instantiates cells for all states except for the first one (which will be
	 * done in the populateRemainingState method) based on inputted probabilities
	 * 
	 * @param cellStates
	 * @param numCols and numRows (along with probability), determine the number of cells to be randomly filled in
	 * @param numRows
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private void populateProbabilities(List<HashMap<String, String>> cellStates,
			int numCols, int numRows) throws InvocationTargetException,
			IllegalAccessException, InstantiationException {
		for (int i = 1; i < cellStates.size(); i++) {
			HashMap<String, String> cellParams = cellStates.get(i);

			double prob = Double.parseDouble(cellParams
					.get(Strings.CELL_LOCATION_PROBABILITY));
			int numCells = (int) (numRows * numCols * prob);
			//specify number of cells to be randomly selected
			for (int j = 0; j < numCells; j++) { 
				int randomX;
				int randomY;
				do {
					int randomLoc = myRandom.nextInt(numRows * numCols);
					randomX = randomLoc / numCols;
					randomY = randomLoc % numCols;
				} while (myCells[randomX][randomY] != null);
				Cell cell = createCellInstance(cellParams);
				myCells[randomX][randomY] = cell;
			}
		}
	}


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
	 * @throws ClassNotFoundException
	 */
	private void populateLocations(List<HashMap<String, String>> cellStates, int numCols,
			int numRows) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, NoSuchMethodException, SecurityException,
			InvocationTargetException {
		for (int i = 1; i < cellStates.size(); i++) {
			HashMap<String, String> cellParams = cellStates.get(i);
			int[] locations = stringToIntArray(cellParams.get(Strings.CELL_LOCATION));
			List<Integer> invalidLocs = new ArrayList<Integer>();
			if (locations != null) {
				for (int j = 0; j < locations.length; j++) {
					if (locations[j] > numCols * numRows) {
						invalidLocs.add(locations[j]);
					}
					int row = locations[j] / numCols;
					int col = locations[j] % numCols;
					Cell cell = createCellInstance(cellParams);
					try {
						myCells[row][col] = cell;
					} catch (ArrayIndexOutOfBoundsException e) {
						throw new XMLParserException(
								Strings.INVALID_CELL_LOCATIONS_ERROR, invalidLocs);
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
	 * @throws ClassNotFoundException
	 */
	private void populateRemainingState(List<HashMap<String, String>> cellStates,
			int numCols, int numRows) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, NoSuchMethodException,
			SecurityException, InvocationTargetException {
		for (int x = 0; x < numRows; x++) {
			for (int y = 0; y < numCols; y++) {
				if (myCells[x][y] == null) {
					HashMap<String, String> remainingCellParams = cellStates.get(0);
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
	private void retrieveParserInfo() throws NoSuchMethodException,
			InstantiationException, IllegalAccessException, InvocationTargetException {
		myFrameRate = Integer.parseInt(myParser.getInitParamMap().get(
				Strings.FRAMES_PER_SECOND));

		String simName = myParser.getInitParamMap().get(Strings.SIMULATION_NAME);
		String className = simName + Strings.CONTROLLER;
		try {
			Class<?> currentClass = Class.forName(Strings.CONTROLLER_PACKAGE + className);
			Constructor<?> constructor = currentClass.getConstructor(Map.class);
			myController = (Controller) constructor
					.newInstance(myParser.getSimParamMap());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new XMLParserException(Strings.INVALID_SIMULATION_NAME_ERROR, simName);
		}
	}

	private void configureListeners() throws IOException {
		myView.getPlayElement().setOnMouseClicked(e -> resumeAnimation());
		myView.getPauseElement().setOnMouseClicked(e -> pauseAnimation());
		myView.getStepElement().setOnMouseClicked(e -> updateGrid());
		myView.getXMLElement().setOnMouseClicked(e -> readNewXML());
		myView.getSpeedupElement().setOnMouseClicked(e -> increaseFrameRate());
		myView.getSlowdownElement().setOnMouseClicked(e -> decreaseFrameRate());
	}

	private KeyFrame start(int frameRate) {
		return new KeyFrame(Duration.millis(1000 / frameRate), e -> updateGrid());
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
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		updateColors();
		myView.updateSimGrid(myCells);

		String[] cellNames = new String[myParser.getCellParamList().size()];
		List<HashMap<String, String>> cellParams = myParser.getCellParamList();
		for (int i = 0; i < cellParams.size(); i++) {
			Map<String, String> cur = cellParams.get(i);
			cellNames[i] = cur.get(Strings.CELL_NAME);
		}

		myView.updateChartLines(myCells, myNumFrames, cellNames);
		myNumFrames++;
	}

	private void updateColors() {
		for (int i = 0; i < myCells.length; i++) {
			for (int j = 0; j < myCells[i].length; j++) {
				String cellName = myCells[i][j].toString();
				List<HashMap<String, String>> cellList = myParser.getCellParamList();
				for (HashMap<String, String> params : cellList) {
					if (params.get(Strings.CELL_NAME).equals(cellName)) {
						myCells[i][j].setColor(Color.valueOf(params
								.get(Strings.CELL_COLOR)));
					}
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
		try {
			myParser.parseXMLFile(newFile);
			retrieveParserInfo();
			myCells = createCellArray();
		} catch (IllegalArgumentException | SecurityException
				| ParserConfigurationException | SAXException | IOException
				| NoSuchMethodException | InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			myView.openDialogBox(Strings.GENERAL_XML_FILE_ERROR); 													// message
			e.printStackTrace();
			return;
		} catch (XMLParserException e) {
			myView.openDialogBox(e.getMessage()); // specific error message
			return;
		}
		setupAnimation();
		myView.setupInitialGrid(myCells,
				myParser.getInitParamMap().get(Strings.CELL_SHAPE));
		pauseAnimation();
		myFrameRate = Integer.parseInt(myParser.getInitParamMap().get(
				Strings.FRAMES_PER_SECOND));
		myView.displayFrameRate(myFrameRate);

		String[] cellNames = new String[myParser.getCellParamList().size()];
		String[] cellColors = new String[myParser.getCellParamList().size()];
		List<HashMap<String, String>> cellParams = myParser.getCellParamList();
		for (int i = 0; i < cellParams.size(); i++) {
			Map<String, String> cur = cellParams.get(i);
			cellNames[i] = cur.get(Strings.CELL_NAME);
			cellColors[i] = cur.get(Strings.CELL_COLOR);
		}

		myNumFrames = 0;
		myView.generateChartLines(cellNames);
		myView.updateChartLines(myCells, myNumFrames, cellNames);

	}

	private int[] stringToIntArray(String string) {
		string = string.replaceAll(Strings.WHITESPACE_STRING, Strings.SPACE_STRING);
		String[] split = string.split(Strings.SPACE_STRING);
		if (!string.equals(Strings.SPACE_STRING) && !string.equals(Strings.EMPTY_STRING)) {
			int[] intArray = new int[split.length];
			for (int i = 0; i < split.length; i++) {
				if (!split[i].equals(Strings.EMPTY_STRING)
						&& !split[i].equals(Strings.SPACE_STRING)) {
					intArray[i] = Integer.parseInt(split[i]);
				}
			}
			return intArray;
		}

		else
			return null;

	}
}
