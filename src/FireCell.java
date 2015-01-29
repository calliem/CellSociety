import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;


public class FireCell extends Cell{
	
	private Color myColor;

	public FireCell(int width, int height, List<Cell> neighbors, Map<String,String> parameters) {
		super(width, height, neighbors);
		//we can randomize this first and worry about exact coordinates later
		//setX(Integer.parseInt(parameters.get("xCoord"));
		//setY(Integer.parseInt(parameters.get("yCoord"));
		myColor = Color.valueOf(parameters.get("color"));


	}
}
