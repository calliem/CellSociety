import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;


public class FireCell extends Cell{
	
	private Color myColor;

	public FireCell(int width, int height, List<Cell> neighbors, Map<String,String> parameters) {
		super(width, height, neighbors);
		myColor = Color.valueOf(parameters.get("color"));


	}
}
