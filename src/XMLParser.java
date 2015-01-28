
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
import java.util.HashMap;
import java.util.Map;
 
public class XMLParser {
	private NodeList myNodeList;
	private NodeList myGridSetupList;
	//private Map<String, Integer> myNodeMap; //using a map prevents the use of if/switch statements

	/*public XMLParser(File xmlFile) throws ParserConfigurationException,
        SAXException, IOException {

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(xmlFile);
    
    //input data into nodes
    myNodeList = doc.getDocumentElement().getChildNodes();
	}*/
	
	public void parseXMLFile(File xmlFile) throws ParserConfigurationException,
    SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(xmlFile);
	    
	    //input data into nodes
	    myNodeList = doc.getDocumentElement().getChildNodes();
	    parseInitialTags();
	}
	
	public void parseInitialTags(){
		for (int i = 0; i < myNodeList.getLength(); i++) {
			//<gridSetup tag>
             Node node = myNodeList.item(i);
             if (node instanceof Element) {
                 myGridSetupList = node.getChildNodes();                  
             }
		}
		setupGrid();
	}
	
	public Map<String, Integer> setupGrid(){
		Map<String, Integer> grid = new HashMap<String, Integer>();
		  for (int j = 0; j < myGridSetupList.getLength(); j++) {
              Node cNode = myGridSetupList.item(j);
              if (cNode instanceof Element) {
                String content = cNode.getTextContent();
                switch (cNode.getNodeName()) {
                  case "simName":
                    System.out.println(content);
                    break;
                  case "author":
                 	 System.out.println(cNode.getNodeValue());
                    break;           
                }
          }
      }
		return grid;
	}
}
		//}
//		System.out.println(myGridSetupList.getLength());
/*
		for (int i = 0 ; i < myGridSetupList.getLength(); i++){
			String test = myGridSetupList.item(i).getChildNodes().item(0).getNodeValue();
			System.out.println(test);
		}*/
	//}
    
 //}
	

   
 
