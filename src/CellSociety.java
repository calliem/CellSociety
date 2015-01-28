
import javafx.application.Application;
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

public class CellSociety extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
				
		stage.setTitle("CellSociety");
		
		HBox titleBox = new HBox(10);		
		titleBox.setAlignment(Pos.CENTER);
		Text title = new Text("Cell Society");		
        title.setFont(Font.font("Helvetica", FontWeight.NORMAL, 32));
        titleBox.setPadding(new Insets(15, 25, 05, 25));
		titleBox.getChildren().add(title);
        
		GridPane mainGrid = new GridPane();
		mainGrid.setHgap(10);
		mainGrid.setVgap(10);
		
		GridPane simGrid = new GridPane();
		mainGrid.setAlignment(Pos.CENTER);
		simGrid.setHgap(1);
		simGrid.setVgap(1);
        simGrid.setPadding(new Insets(0, 25, 5, 25));

		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				Rectangle r = new Rectangle(5, 5, Color.CYAN);
				simGrid.add(r, i, j);
			}
		}
		
		mainGrid.add(titleBox, 0, 0);
		mainGrid.add(simGrid, 0, 1);
		
		Button playButton = new Button("Play");
		Button pauseButton = new Button("Pause");
		Button stepButton = new Button("Step");
		
		Label speedLabel = new Label("Speed: ");
		TextField speedText = new TextField();
		Button xmlButton = new Button("Upload XML");
		
		Text errorMsg = new Text("[Error message]");
		errorMsg.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
		errorMsg.setFill(Color.RED);
		
		HBox topRow = new HBox(10);
		HBox middleRow = new HBox(10);
		HBox bottomRow = new HBox(10);
		
		topRow.setAlignment(Pos.CENTER);
		topRow.getChildren().add(playButton);
		topRow.getChildren().add(pauseButton);
		topRow.getChildren().add(stepButton);
		topRow.getChildren().add(xmlButton);
		topRow.setPadding(new Insets(0, 25, 5, 25));
		
		middleRow.setAlignment(Pos.CENTER);
		middleRow.getChildren().add(speedLabel);
		middleRow.getChildren().add(speedText);
		middleRow.setPadding(new Insets(0, 25, 5, 25));
		
		bottomRow.setAlignment(Pos.TOP_CENTER);
		bottomRow.getChildren().add(errorMsg);
		bottomRow.setPadding(new Insets(0, 25, 15, 25));

		mainGrid.add(topRow, 0, 2);
		mainGrid.add(middleRow, 0, 3);
		mainGrid.add(bottomRow, 0, 4);
		
		Scene scene = new Scene(mainGrid);
		stage.setScene(scene);
		stage.show();
	}

}