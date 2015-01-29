import javafx.scene.paint.Color;


public class CellState {
<<<<<<< HEAD
	
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
=======
	private String myLabel;
	private Color myColor;
	
	
	public CellState(String state) {
		myColor = StateMap.get(state);
	}
	
>>>>>>> 2248ad8ddc79ce1cb07478e7a624f431890bf07d
	
	public Color getColor(){
		return myColor;
	}
<<<<<<< HEAD
	
	public int[] getLocations(){
		return myLocations;
=======

	public String toString(){
		return myLabel;
>>>>>>> 2248ad8ddc79ce1cb07478e7a624f431890bf07d
	}
}
