import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class GameManager {

	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		XMLParser parser = new XMLParser();
		parser.parseXMLFile(new File("src/fire2.xml"));
		
	}
}
