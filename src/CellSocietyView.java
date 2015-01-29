import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class CellSocietyView {
	
	private Scene myScene;
	private Button myPlayButton;
	private Button myPauseButton;
	private Button myStepButton;
	private Button myXMLButton;
	private GridPane myRoot;
	private GridPane myGrid;
	
	private static final int CELL_SIZE = 10;
	
	//using Reflection makes us have a ton of throw errors. Is that okay?
	
	public CellSocietyView(Stage s) throws ParserConfigurationException, SAXException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {
		
		myRoot = new GridPane();
		
		initializeButtons();
		generateGrid();
		configureUI();
		
		myScene = new Scene(myRoot);
		s.setTitle("CellSociety");		
		s.setScene(myScene);
		s.show();
		
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
	
	private void configureUI() {
		myRoot.setAlignment(Pos.CENTER);
		myRoot.setHgap(10);
		myRoot.setVgap(10);
		myRoot.add(createTitle(), 0, 0);
		myRoot.add(myGrid, 0, 1);
		myRoot.add(makeButtons(), 0, 2);
		myRoot.add(makeSpeed(), 0, 3);
		myRoot.add(createErrorLocation(), 0, 4);
	}
	
	/**
	 * 
	 */
	private void initializeButtons() {
		myPlayButton = new Button("Play");
		myPauseButton = new Button("Pause");
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
		myGrid = new GridPane();
		myGrid.setHgap(1);
		myGrid.setVgap(1);
        myGrid.setPadding(new Insets(0, 25, 5, 25));
        myGrid.setAlignment(Pos.CENTER);
        
        //NOTE: the parser may not belong in this class, but this is an example of how the XMLParser
        //will update the other classes. Unsure right now whether specifically searching for the
        //string "yCols" is bad design, although we can ask when we meet with our TA
        
        XMLParser parser = new XMLParser();
		parser.parseXMLFile(new File("src/life.xml"));
		Map<String, String> map = parser.getGridParamMap();
		
		//how to use cols and rows here?
		
		int numCols = Integer.parseInt(map.get("yCols"));
		int numRows = Integer.parseInt(map.get("xRows"));
	//	List<CellState> list = getCellStateList();
		int temp = 0;

		List<CellState> cellStates = parser.getCellStateList();
		for (int i = 0; i < cellStates.size(); i++){
			CellState state = cellStates.get(i);
			String stateName = state.toString();
			int[] locations = state.getLocations();
			for (int j = 0; j < locations.length; j++){
				int row = locations[j] / numRows;
				int col = (locations[j] - 1) % numCols;
				Cell cell = createCellInstance(stateName);
				myGrid.add(cell, row, col);
			}
		}
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

		public Cell createCellInstance(String cellState) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException{
            Class<?> className = Class.forName(cellState);
            System.out.println(className.toString());
			Constructor<?> constructor = className.getConstructor(Integer.TYPE, Integer.TYPE);
			System.out.println(constructor);
			return (Cell) constructor.newInstance(CELL_SIZE, CELL_SIZE);
		}

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
	private HBox makeSpeed() {
		HBox middleRow = new HBox(10);
		Label speedLabel = new Label("Speed: ");
		TextField speedText = new TextField();
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
		Text errorMsg = new Text("[Error message]");
		errorMsg.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
		errorMsg.setFill(Color.RED);
		bottomRow.setAlignment(Pos.TOP_CENTER);
		bottomRow.getChildren().add(errorMsg);
		bottomRow.setPadding(new Insets(0, 25, 15, 25));
		return bottomRow;
	}
}
