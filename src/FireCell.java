import javafx.scene.paint.Color;

public class FireCell extends Cell{
	
	public FireCell(){
		super();
		//test below. color is not a static variable and needs to be updated every time for this to work. This isn't ideal so we'll have to find alternatives
		//i think this issue probably applies to every cell but not sure why it's only noticeable for firecell right now
		setFill(Color.valueOf("orange"));
	}

	public FireCell(Color color) {
		super();
		setFill(color);
	}
	public String toString(){
		return "FireCell";
	}
}
