import java.util.HashMap;

import javafx.scene.paint.Color;


public class EmptyCell extends Cell{
	
	public EmptyCell() {
		super();
		setFill(Color.WHITE);
	}
	
	public EmptyCell(Color color) {
		super();
		setFill(color);
	}
	
	//for debugging 
	public String toString(){
		return "EmptyCell";
	}
		

}
