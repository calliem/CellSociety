import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle{
	
	private Color myColor;
	private List<Cell> myNeighbors;
	
	public Cell(int width, int height, Map<String,String> parameters, List<Cell> neighbors) {
		super(width, height);
		//we can randomize this first and worry about exact coordinates later
		//setX(Integer.parseInt(parameters.get("xCoord"));
		//setY(Integer.parseInt(parameters.get("yCoord"));
		myColor = Color.valueOf(parameters.get("color"));
		myNeighbors = neighbors;
	}
	
	public List<Cell> getNeighbors(){
		return myNeighbors;
	}
	
	//is color here synoymous with state/type? if so, find some way to depict this through the xml
	public void setColor(Color color){
		setFill(color);
	}
	
}