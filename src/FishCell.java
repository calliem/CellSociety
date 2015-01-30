import javafx.scene.paint.Color;

public class FishCell extends Cell{
	
	// private int reproductionAge; this should be passed into the controller
	
	private int myAge;
	
	public FishCell(int age){
		super();
		myAge = age;
	}

	public FishCell(Color color) {
		super();
		setFill(color);
		myAge = 0;
	}
	
	public int getAge(){
		return myAge;
	}
}
