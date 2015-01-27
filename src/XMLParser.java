
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
 
public class XMLParser {
	private NodeList myNodeList;
	private NodeList myGridSetupList;
	//private Map<String, Integer> myNodeMap; //using a map prevents the use of if/switch statements
	

	public XMLParser(File xmlFile) throws ParserConfigurationException,
        SAXException, IOException {

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(xmlFile);
    myNodeList = doc.getDocumentElement().getChildNodes();

   // parseInitialObjects();
	}
	
	//there is an error somewhere here
	public void parseInitialTags(){
		for (int i = 0; i < myNodeList.getLength(); i++) {
             Node node = myNodeList.item(i);
             if (node.getNodeType() == Node.ELEMENT_NODE) {
	             if (node.getNodeName() == "gridSetup"){
	            	 myGridSetupList = node.getChildNodes();
	             }
             }
		}
		System.out.println(myGridSetupList.getLength());

		for (int i = 0 ; i < myGridSetupList.getLength(); i++){
			String test = myGridSetupList.item(i).getNodeValue();
			System.out.println(test);
		}
	}
    
 }
	

   
 
