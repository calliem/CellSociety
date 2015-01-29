import javafx.scene.paint.Color;

public class FireCell extends Cell{
	
	//private Color myColor;

	public FireCell() {
		super();
		setFill(Color.valueOf("red"));

	}
	public String toString(){
		return "FireCell";
	}
}
