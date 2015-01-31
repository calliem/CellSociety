import javafx.scene.paint.Color;

//Where do we put random static methods we 
//want to use throughout


public class RedCell extends Cell{

	public RedCell() {
		super();
	}
	//add on map parameter
	public RedCell(Color color) {
		super();
		setFill(color);
	}
	
	public String toString(){
		return "RedCell";
	}
}
