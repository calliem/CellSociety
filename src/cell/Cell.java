package cell;

import java.util.Map;

import cellsociety.Strings;
import javafx.scene.paint.Color;

public class Cell {

	private String myName;
	private Color myColor;

	public Cell(String name) {
		myName = name;
	}

	public Cell(Map<String, String> params) {
		myName = params.get(Strings.CELL_NAME);
		myColor = Color.valueOf(params.get(Strings.CELL_COLOR));
	}

	public Color getColor() {
		return myColor;
	}

	@Override
	public String toString() {
		return myName;
	}

	public void setColor(Color color) {
		myColor = color;
	}

}
