import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

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
	private GridPane mySimGrid;
    private ResourceBundle myResources;
	
    
    private static final Font ERROR_FONT = Font.font("Arial", FontWeight.NORMAL, 12);
    private static final Font TITLE_FONT = Font.font("Helvetica", FontWeight.NORMAL, 32);


    public static final String DEFAULT_RESOURCE_PACKAGE = "";
	public static final int GRID_SIZE = 500;

	// using Reflection makes us have a ton of throw errors. Is that okay?

	public CellSocietyView(Stage s) throws ParserConfigurationException,
			SAXException, IOException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException,
			NoSuchMethodException, SecurityException {

		myRoot = new GridPane();
		myStage = s;
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "english");
		
		initializeButtons();
		disableInitialButtons();
		generateGrid();
		setBlankGrid();
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

	public void updateSimGrid(Cell[][] cellGrid) {
		mySimGrid.getChildren().clear();
		for (int i = 0; i < cellGrid.length; i++) {
			for (int j = 0; j < cellGrid[0].length; j++)
				mySimGrid.add(cellGrid[i][j].getShape(), j, i);
		}
	}

	public void setErrorText() {
		myErrorMsg.setFont(ERROR_FONT);
		myErrorMsg.setFill(Color.RED);
		myErrorMsg.setText(myResources.getString("ErrorMessage"));
	}

	public void setBlankGrid() {
		mySimGrid.getChildren().clear();
		Rectangle r = new Rectangle();
		r.setWidth(GRID_SIZE);
		r.setHeight(GRID_SIZE);
		mySimGrid.add(r, 0, 0);
	}

	public void generateTitle(String s) {
		Text title = new Text(s);
		title.setFont(TITLE_FONT);
		myTitleBox.getChildren().clear();
		myTitleBox.getChildren().add(title);
	}

	/**
	 * 
	 */
	public void displayFrameRate(int frameRate) {
		mySpeedTextField.setText(frameRate + " "
				+ myResources.getString("FramesLabel"));
	}
	
	public File displayXMLChooser() {
		// TODO Auto-generated method stub
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open XML File");
		fileChooser.getExtensionFilters().add(
				new FileChooser.ExtensionFilter("XML Files", "*.xml"));
		return fileChooser.showOpenDialog(myStage);
	}
	
	private void configureUI() {
		myRoot.setAlignment(Pos.CENTER);
		myRoot.setHgap(10);
		myRoot.setVgap(10);
		myRoot.add(createTitle(), 0, 0);
		myRoot.add(mySimGrid, 0, 1);
		myRoot.add(makeSimulationButtons(), 0, 2);
		myRoot.add(makeFrameControlButtons(), 0, 3);
		myRoot.add(makeSpeedDisplay(), 0, 4);
		myRoot.add(createErrorLocation(), 0, 5);
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
		myPlayButton = new Button(myResources.getString("PlayCommand"));
		myPauseButton = new Button(myResources.getString("PauseCommand"));
		myStepButton = new Button(myResources.getString("StepCommand"));
		myXMLButton = new Button(myResources.getString("XMLCommand"));
		mySpeedupButton = new Button(myResources.getString("SpeedupCommand"));
		mySlowdownButton = new Button(myResources.getString("SlowdownCommand"));
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
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 */
	private void generateGrid() throws ParserConfigurationException,
			SAXException, IOException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException,
			NoSuchMethodException, SecurityException {
		mySimGrid = new GridPane();
		mySimGrid.setHgap(1);
		mySimGrid.setVgap(1);
		mySimGrid.setPadding(new Insets(0, 25, 5, 25));
		mySimGrid.setAlignment(Pos.CENTER);

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

	public void changeResumeButtonPermissions() {
		myPlayButton.setDisable(true);
		myPauseButton.setDisable(false);
		myStepButton.setDisable(true);
		myXMLButton.setDisable(true);
		mySpeedupButton.setDisable(true);
		mySlowdownButton.setDisable(true);		
	}

	public void changePauseButtonPermissions() {
		myPlayButton.setDisable(false);
		myPauseButton.setDisable(true);
		myStepButton.setDisable(false);
		myXMLButton.setDisable(false);
		mySpeedupButton.setDisable(false);
		mySlowdownButton.setDisable(false);		
	}

}
