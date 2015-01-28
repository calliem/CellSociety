import javafx.scene.paint.Color;


public class CellState {
	private String myLabel;
	private Color myColor;
	
	
	public CellState(String state) {
		myColor = StateMap.get(state);
	}
	
	
	public Color getColor(){
		return myColor;
	}

	public String toString(){
		return myLabel;
	}
}
