
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


class Cell extends Rectangle{
	private CellState myState;
	
	public Cell(String state){
		myState = new CellState(state);
		setFill(myState.getColor());
	}
	
	public CellState getState(){
		return myState;
	}
}

