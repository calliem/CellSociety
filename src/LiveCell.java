import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class LiveCell extends Cell{
	
	public LiveCell(){
		super();
	}
	
	public LiveCell(Map<String, String> params) {
		super();
		setFill(Color.valueOf(params.get("color")));
	}

	public String toString(){
		return "LiveCell";
	}
	
}
