import java.io.File;
import java.io.IOException;

import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class GameManager {
	private CellSociety myCellSociety;
	private SimController myController;
	
	public void start(Stage s) throws ParserConfigurationException, SAXException, IOException{
		XMLParser parser = new XMLParser(new File("src/fire2.xml"));
		parser.parseInitialTags();
		
		//get simulation data from parser
		SimInit sim = parser.getSetupData();

		
		simSetup(sim);
		
		myCellSociety = new CellSociety();
		
	}
	
	public void simSetup(FireInit){
		
	}
	/*
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		XMLParser parser = new XMLParser(new File("src/fire2.xml"));
		parser.parseInitialTags();
		
	}
	*/
}
