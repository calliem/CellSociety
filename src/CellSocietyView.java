import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.*;

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
	private Text myErrorMsg;
	private GridPane myRoot;
	private GridPane mySimGrid;
	
	private static final int GRID_SIZE = 100;
	
	//using Reflection makes us have a ton of throw errors. Is that okay?
	
	public CellSocietyView(Stage s, Cell[][] initialCellArray, int frameRate) throws ParserConfigurationException, SAXException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {
		
		myRoot = new GridPane();
		myStage = s;
		
		initializeButtons();
		generateGrid();
		setBlankGrid();
		configureUI(frameRate);
				
		myScene = new Scene(myRoot);
		myStage.setTitle("CellSociety");		
		myStage.setScene(myScene);
		myStage.show();
	}
	
	public Stage getStage() {
		return myStage;
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
	
	public void updateSimGrid(Cell[][] cellGrid) {
		mySimGrid.getChildren().clear();
		for (int i = 0; i < cellGrid.length; i++) {
			for (int j = 0; j < cellGrid[0].length; j++)
				mySimGrid.add(cellGrid[i][j], j, i);
		}
	}
	
	public void setErrorText() {
		myErrorMsg.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
		myErrorMsg.setFill(Color.RED);
		myErrorMsg.setText("XML File uploaded is not valid.");
	}
	
	public void setBlankGrid() {
		mySimGrid.getChildren().clear();
		Rectangle r = new Rectangle();
		r.setWidth(GRID_SIZE);
		r.setHeight(GRID_SIZE);
		mySimGrid.add(r, 0, 0);
	}
	
	private void configureUI(int frameRate) {
		myRoot.setAlignment(Pos.CENTER);
		myRoot.setHgap(10);
		myRoot.setVgap(10);
		myRoot.add(createTitle(), 0, 0);
		myRoot.add(mySimGrid, 0, 1);
		myRoot.add(makeButtons(), 0, 2);
		myRoot.add(makeSpeed(frameRate), 0, 3);
		myRoot.add(createErrorLocation(), 0, 4);
	}
	
	/**
	 * 
	 */
	private void initializeButtons() {
		myPlayButton = new Button("Play");
		myPauseButton = new Button("Pause");
		myPauseButton.setDisable(true);
		myStepButton = new Button("Step");
		myXMLButton = new Button("Upload XML");
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
			SAXException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {
		mySimGrid = new GridPane();
		mySimGrid.setHgap(1);
		mySimGrid.setVgap(1);
        mySimGrid.setPadding(new Insets(0, 25, 5, 25));
        mySimGrid.setAlignment(Pos.CENTER);
        
        //NOTE: the parser may not belong in this class, but this is an example of how the XMLParser
        //will update the other classes. Unsure right now whether specifically searching for the
        //string "yCols" is bad design, although we can ask when we meet with our TA
    }
	
	/*
	for (int i = 0; i < xRows; i++) {
		for (int j = 0; j < yCols; j++) {
			//substitute these with Cell class after the Cell class is completed/updated
			Rectangle r = new Rectangle(10, 10, Color.CYAN);
			String hi = "hi";
			Cell cell = createCellInstance(hi);
			myGrid.add(r, i, j);
			
		}
	}*/

	//param should be a map or a hashmap?
		

	/**
	 * @return
	 */
	private HBox createTitle() {
		HBox titleBox = new HBox(10);		
		titleBox.setAlignment(Pos.CENTER);
		Text title = new Text("Cell Society");		
        title.setFont(Font.font("Helvetica", FontWeight.NORMAL, 32));
        titleBox.setPadding(new Insets(15, 25, 5, 25));
		titleBox.getChildren().add(title);
		return titleBox;
	}

	/**
	 * @param topRow
	 */
	private HBox makeButtons() {
		HBox topRow = new HBox(10);
		topRow.setAlignment(Pos.CENTER);
		topRow.getChildren().add(myPlayButton);
		topRow.getChildren().add(myPauseButton);
		topRow.getChildren().add(myStepButton);
		topRow.getChildren().add(myXMLButton);
		topRow.setPadding(new Insets(0, 25, 5, 25));
		return topRow;
	}
	
	/**
	 * @param speedLabel
	 * @param speedText
	 * @param middleRow
	 */
	private HBox makeSpeed(int frameRate) {
		HBox middleRow = new HBox(10);
		Label speedLabel = new Label("Speed: ");
		TextField speedText = new TextField();
		speedText.setText(frameRate + " frame(s) per second");
		middleRow.setAlignment(Pos.CENTER);
		middleRow.getChildren().add(speedLabel);
		middleRow.getChildren().add(speedText);
		middleRow.setPadding(new Insets(0, 25, 5, 25));
		return middleRow;
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

	public File displayXMLChooser() {
		// TODO Auto-generated method stub
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open XML File");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
		return fileChooser.showOpenDialog(myStage);
	}
		
}
