import java.util.HashMap;

import javafx.scene.paint.Color;

public class LiveCell extends Cell{
	
	public LiveCell(){
		super();
		setFill(Color.BLACK);
	}
	
	public LiveCell(Color color) {
		super();
		setFill(color);
	}

	public String toString(){
		return "LiveCell";
	}
	
}
