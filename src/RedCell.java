import javafx.scene.paint.Color;

//Where do we put random static methods we 
//want to use throughout


public class RedCell extends Cell{

	public RedCell(){
		super();
		setFill(Color.valueOf("red"));
	}
	
	public String toString(){
		return "RedCell";
	}
}
