package cellsociety;

import javafx.scene.control.Button;

/**
 * This class contains a list of static String variables that are used throughout the program
 * @author Callie Mao
 *
 */

public class Strings {

	// initialization
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String INITIALIZATION_PARAMETERS = "initParam";
	public static final String CELL_PARAMETERS = "cellParam";
	public static final String SIMULATION_PARAMETERS = "simParam";
	public static final String SIMULATION_NAME = "simName";
	public static final String FRAMES_PER_SECOND = "fps";
	public static final String COLUMNS = "yCols";
	public static final String ROWS = "xRows";

	// cell properties
	public static final String CELL_STATE = "state";
	public static final String CELL_COLOR = "color";
	public static final String CELL = "Cell";
	public static final String CELL_LOCATION = "loc";
	public static final String CELL_LOCATION_PROBABILITY = "locProbability";
	public static final String CELL_NAME = "name";
	public static final String CELL_SHAPE = "shape";

	// cell subclass properties
	public static final String MAX_ENERGY = "maxEnergy";
	public static final String REPRODUCTION_AGE = "reproductionAge";

	// cell states
	public static final String SHARK_CELL = "SharkCell";
	public static final String FISH_CELL = "FishCell";
	public static final String EMPTY_CELL = "EmptyCell";
	public static final String FIRE_CELL = "FireCell";
	public static final String TREE_CELL = "TreeCell";
	public static final String LIVE_CELL = "LiveCell";
	public static final String BLUE_CELL = "BlueCell";
	public static final String RED_CELL = "RedCell";
	public static final String AGENT_CELL = "AgentCell";
	public static final String GROUND_CELL = "GroundCell";

	// error messages
	public static final String INVALID_CELL_LOCATIONS_ERROR = "Invalid cell locations: %s";
	public static final String GENERAL_XML_FILE_ERROR = "Error in XML file";
	public static final String INVALID_CELL_STATE_ERROR = "Invalid cell state: %s";
	public static final String INVALID_SIMULATION_NAME_ERROR = "Invalid simulation name: %s";
	public static final String NO_CELL_LOCATION_ERROR = "Please specify cell locations or location probabilities";

	// packages
	public static final String CELL_PACKAGE = "cell.";
	public static final String CONTROLLER_PACKAGE = "controller.";
	public static final String BOUNDARY_PACKAGE = "boundary.";
	public static final String NEIGHBOR_PACKAGE = "neighbor.";

	// simulation parameters
	public static final String CONTROLLER = "Controller";
	public static final String NEIGHBOR = "Neighbor";
	public static final String BOUNDARY = "Boundary";
	public static final String CATCH_PROBABILITY = "probCatch";
	public static final String HAPPY_PROBABILITY = "probHappy";
	public static final String NEIGHBOR_PARAMETER = "neighbor";
	public static final String BOUNDARY_PARAMETER = "boundary";
	public static final String STAY_ON = "stayOn";
	public static final String BORN_ON = "bornOn";

	// general strings
	public static final String SPACE_STRING = " ";
	public static final String EMPTY_STRING = "";
	public static final String WHITESPACE_STRING = "\\s+";
	public static final String NONE = "none";
	public static final String BOTH = "both";
	public static final String TWO = "two";
	
	public static final String PLAY_COMMAND = "PlayCommand";
	public static final String PAUSE_COMMAND = "PauseCommand";
	public static final String STEP_COMMAND = "StepCommand";
	public static final String XML_COMMAND = "XMLCommand";
	public static final String SPEEDUP_COMMAND = "SpeedupCommand";
	public static final String SLOWDOWN_COMMAND = "SlowdownCommand";

}
