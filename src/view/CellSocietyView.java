package view;
import cellsociety.Strings;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import cell.Cell;
/**
 * Codes for the visuals and UI of the simulation
 * @author Callie Mao
 *
 */
public class CellSocietyView {

	private Stage myStage;
	private Scene myScene;
	private Button myPlayButton;
	private Button myPauseButton;
	private Button myStepButton;
	private Button myXMLButton;
	private Button mySpeedupButton;
	private Button mySlowdownButton;
	private TextField mySpeedTextField;
	private Text myErrorMsg;
	private HBox myTitleBox;
	private GridPane myRoot;
	private ResourceBundle myResources;
	private ChartView myChartView;
	
	private AbstractGrid mySimGrid;
	private Group myDefaultGrid;

	private static final Font TITLE_FONT = Font.font("Helvetica", FontWeight.NORMAL, 32);

	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final int GRID_SIZE = 400;

	// using Reflection makes us have a ton of throw errors. Is that okay?

	public CellSocietyView(Stage s) throws ParserConfigurationException, SAXException,
			IOException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, ClassNotFoundException,
			NoSuchMethodException, SecurityException {

		myRoot = new GridPane();
		myStage = s;
		myDefaultGrid = new Group(new Rectangle(0, 0, GRID_SIZE, GRID_SIZE));
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "english");

		initializeButtons();
		disableInitialButtons();
		configureUI();
		setupGameScene();
	}

	public Button getPlayElement() {
		return this.myPlayButton;
	}

	public Button getPauseElement() {
		return this.myPauseButton;
	}

	public Button getStepElement() {
		return this.myStepButton;
	}

	public Button getXMLElement() {
		return this.myXMLButton;
	}

	public Button getSpeedupElement() {
		return this.mySpeedupButton;
	}

	public Button getSlowdownElement() {
		return this.mySlowdownButton;
	}


	public void openDialogBox(String message) {

		Stage stage = new Stage();
		HBox root = new HBox();
		root.setAlignment(Pos.CENTER);
		Text text = new Text(message);
		root.getChildren().add(text);

		Scene scene = new Scene(root, 300, 100);

		stage.setTitle("Error");
		stage.setScene(scene);
		stage.show();

	}

	public void setupInitialGrid(Cell[][] cells, String shape) {
		myDefaultGrid.getChildren().clear();
		if (mySimGrid != null)
			mySimGrid.getGrid().getChildren().clear();
		mySimGrid = GridFactory.createGrid(shape, GRID_SIZE, cells);
		myRoot.add(mySimGrid.getGrid(), 1, 1);
	}

	public void updateSimGrid(Cell[][] cellGrid) {
		mySimGrid.update(cellGrid);
	}

	public void generateTitle(String s) {
		Text title = new Text(s);
		title.setFont(TITLE_FONT);
		myTitleBox.getChildren().clear();
		myTitleBox.getChildren().add(title);
	}

	public void displayFrameRate(int frameRate) {
		mySpeedTextField.setText(frameRate + " " + myResources.getString("FramesLabel"));
	}

	public File displayXMLChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open XML File");
		fileChooser.getExtensionFilters().add(
				new FileChooser.ExtensionFilter("XML Files", "*.xml"));
		return fileChooser.showOpenDialog(myStage);
	}

	public void setButtonsPause(boolean disable) {
		myPlayButton.setDisable(disable);
		myPauseButton.setDisable(!disable);
		myStepButton.setDisable(disable);
		myXMLButton.setDisable(disable);
		mySpeedupButton.setDisable(disable);
		mySlowdownButton.setDisable(disable);
	}

	public void updateChartLines(Cell[][] cells, int numFrames, String[] names) {
		myChartView.updateChartLines(cells, numFrames, names);
	}

	public void generateChartLines(String[] cellNames) {
		myChartView.generateChartLines(cellNames);
	}

	private XYChart<Number, Number> initializeChart() {
		myChartView = new ChartView();
		return myChartView.getChart();
	}

	private void configureUI() {
		myRoot.setAlignment(Pos.CENTER);
		myRoot.setHgap(10);
		myRoot.setVgap(10);
		myRoot.add(createTitle(), 1, 0);
		myRoot.add(myDefaultGrid, 1, 1);
		myRoot.add(makeSimulationButtons(), 1, 2);
		myRoot.add(makeFrameControlButtons(), 1, 3);
		myRoot.add(makeSpeedDisplay(), 1, 4);
		myRoot.add(createErrorLocation(), 1, 5);
		myRoot.add(initializeChart(), 2, 1);
		myRoot.getColumnConstraints().add(new ColumnConstraints(50));
	}

	/**
	 * 
	 */
	private void setupGameScene() {
		myScene = new Scene(myRoot);
		myStage.setTitle(myResources.getString("Title"));
		myStage.setScene(myScene);
		myStage.show();
	}

	private void initializeButtons() {
		myPlayButton = new Button(myResources.getString(Strings.PLAY_COMMAND));
		myPauseButton = new Button(myResources.getString(Strings.PAUSE_COMMAND));
		myStepButton = new Button(myResources.getString(Strings.STEP_COMMAND));
		myXMLButton = new Button(myResources.getString(Strings.XML_COMMAND));
		mySpeedupButton = new Button(myResources.getString(Strings.SPEEDUP_COMMAND));
		mySlowdownButton = new Button(myResources.getString(Strings.SLOWDOWN_COMMAND));
	}

	/**
	 * 
	 */
	private void disableInitialButtons() {
		myPlayButton.setDisable(true);
		myPauseButton.setDisable(true);
		myStepButton.setDisable(true);
		mySpeedupButton.setDisable(true);
		mySlowdownButton.setDisable(true);
	}

	/**
	 * @return
	 */
	private HBox createTitle() {
		myTitleBox = new HBox(10);
		myTitleBox.setAlignment(Pos.CENTER);
		myTitleBox.setPadding(new Insets(15, 25, 5, 25));
		generateTitle(myResources.getString("Title"));
		return myTitleBox;
	}

	/**
	 * @param topRow
	 */
	private HBox makeSimulationButtons() {
		HBox topRow = new HBox(10);
		topRow.setAlignment(Pos.CENTER);
		topRow.getChildren().add(myPlayButton);
		topRow.getChildren().add(myPauseButton);
		topRow.getChildren().add(myStepButton);
		topRow.getChildren().add(myXMLButton);
		topRow.setPadding(new Insets(0, 25, 5, 25));
		return topRow;
	}

	private HBox makeFrameControlButtons() {
		HBox middleRow = new HBox(10);
		middleRow.setAlignment(Pos.CENTER);
		middleRow.getChildren().add(mySpeedupButton);
		middleRow.getChildren().add(mySlowdownButton);
		middleRow.setPadding(new Insets(0, 25, 5, 25));
		return middleRow;
	}

	/**
	 * @param speedLabel
	 * @param speedText
	 * @param middleRow
	 */
	private HBox makeSpeedDisplay() {
		HBox displayRow = new HBox(10);
		Label speedLabel = new Label(myResources.getString("SpeedLabel"));
		mySpeedTextField = new TextField();
		mySpeedTextField.setEditable(false);
		displayRow.setAlignment(Pos.CENTER);
		displayRow.getChildren().add(speedLabel);
		displayRow.getChildren().add(mySpeedTextField);
		displayRow.setPadding(new Insets(0, 25, 5, 25));
		return displayRow;
	}

	/**
	 * 
	 */
	private HBox createErrorLocation() {
		HBox bottomRow = new HBox(10);
		myErrorMsg = new Text();
		bottomRow.setAlignment(Pos.TOP_CENTER);
		bottomRow.getChildren().add(myErrorMsg);
		bottomRow.setPadding(new Insets(0, 25, 15, 25));
		return bottomRow;
	}

}
