import javafx.scene.paint.Color;


public class BlueCell extends Cell{

	public BlueCell() {
		super();
	}
	//add on map parameter
	public BlueCell(Color color) {
		super();
		setFill(color);
	}
	
	public String toString(){
		return "BlueCell";
	}
}
