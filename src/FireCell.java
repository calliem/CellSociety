import javafx.scene.paint.Color;

public class FireCell extends Cell{
	
	private static Color myColor;
	
	public FireCell(){
		super();
		setFill(myColor); //could we add this to the superclass somehow or make a new method to setfill that way we don't have duplicated code?
		
		
		//test below. color is not a static variable and needs to be updated every time for this to work. This isn't ideal so we'll have to find alternatives
		//i think this issue probably applies to every cell but not sure why it's only noticeable for firecell right now
	//	setFill(Color.valueOf("orange"));
	}

	public FireCell(Color color) {
	
		super();
		myColor = color;
		setFill(myColor);
		//super();
	//	setFill(color);
	}
	public String toString(){
		return "FireCell";
	}
}
