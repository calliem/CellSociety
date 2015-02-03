

import java.util.HashMap;

import javafx.scene.paint.Color;


public class StateMap {
	private static HashMap<String, Color> myMap = new HashMap<String, Color>();
	
	
	
	public static Color get(String s){
		populateMap();
		return myMap.get(s);
	}
	
	private static void populateMap(){
		myMap.put("live", Color.BLACK);
		myMap.put("dead", Color.WHITE);
		myMap.put("tree", Color.GREEN);
		myMap.put("fire", Color.ORANGE);
		myMap.put("ash", Color.GREY);
		myMap.put("fish", Color.GREEN);
		myMap.put("shark", Color.BLUE);
		myMap.put("empty", Color.WHITE);
		myMap.put("blue", Color.BLUE);
		myMap.put("red", Color.RED);
	}
}
