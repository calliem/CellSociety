import java.util.HashMap;
import java.util.Map;


import javafx.scene.paint.Color;


public class EmptyCell extends Cell{
	
	public EmptyCell() {
		super();
	}
	//add on map parameter
	public EmptyCell(Color color) {
		super();
		setFill(color);
	}
	
	//for debugging 
	public String toString(){
		return "EmptyCell";
	}
		

}
