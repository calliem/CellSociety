import javafx.scene.paint.Color;


public class LiveCell extends Cell{
	
	public LiveCell() {
		super();
		//pass mycellparameters earlier on as well
		
		//put parameters in cell as well
		
		//use parameters to setfill
		setFill(Color.valueOf("red"));
		// TODO Auto-generated constructor stub
	}

	public String toString(){
		return "LiveCell";
	}
}
