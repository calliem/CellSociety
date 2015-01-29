import javafx.scene.paint.Color;


public class CellState {
	
	String myState;
	Color myColor; 
	int[] myLocations;
	
	public CellState(String stateName, Color color, int[] location){
		myState = stateName;
		myColor = color;
		myLocations = location;		
	}
	
	public String getState(){
		return myState;
	}

	
	public Color getColor(){
		return myColor;
	}

}
