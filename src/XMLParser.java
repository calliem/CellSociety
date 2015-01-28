
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
import java.util.Map;
 
public class XMLParser {
	private NodeList myNodeList;
	private NodeList myNodeList2;
	private NodeList myGridSetupList;
	//private Map<String, Integer> myNodeMap; //using a map prevents the use of if/switch statements

	public XMLParser(File xmlFile) throws ParserConfigurationException,
        SAXException, IOException {

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(xmlFile);
    
    //input data into nodes
    myNodeList = doc.getDocumentElement().getChildNodes();
   // parseInitialObjects();
	}
	
	public void parseInitialTags(){
		for (int i = 0; i < myNodeList.getLength(); i++) {
			//<gridSetup tag>
             Node node = myNodeList.item(i);
             if (node instanceof Element) {
                 NodeList childNodes = node.getChildNodes();    
                 for (int j = 0; j < childNodes.getLength(); j++) {
                     Node cNode = childNodes.item(j);

                     //Identifying the child tag of gridSetup encountered. 
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
             }
		}
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
	

   
 
