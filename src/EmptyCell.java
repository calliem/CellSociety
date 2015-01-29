import javafx.scene.paint.Color;


public class EmptyCell extends Cell{
	
	public EmptyCell(int width, int height) {
		super(width, height);
		setFill(Color.valueOf("black"));


		// TODO Auto-generated constructor stub
	}

	public String toString(){
		return "emptycell tostring";
	}
}
