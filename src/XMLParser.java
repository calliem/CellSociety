
//REMOVE #TEXT NODES
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

    private Map<String, String> myGridParamMap;
    private Map<String, String> myCellParamMap;
	
	public void parseXMLFile(File xmlFile) throws ParserConfigurationException,
    SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(xmlFile);
	    doc.getDocumentElement().normalize();
	    
	    //input data into nodes
	    myNodeList = doc.getDocumentElement().getChildNodes();
	    parseInitialTags();
	}
	
	public void parseInitialTags(){
		for (int i = 0; i < myNodeList.getLength(); i++) {
			//<gridParam tag>
             Node node = myNodeList.item(i);
             if (node instanceof Element) {  
            	switch (node.getNodeName()) {
     			case "gridParam":
                    NodeList gridParamList = node.getChildNodes(); //use instance instead of global?                  
     				myGridParamMap = makeParamMap(gridParamList);
     				//somehow pass this in just once later on instead of calling makeParamMap each time
                    //maybe just use one myParamList variable and continuously override it?
     				break;
     			case "cellParam":
     				NodeList cellParamList = node.getChildNodes();
     				myCellParamMap = makeParamMap(cellParamList);
     				break;  
            	}
             }
		}
	}
	
	private Map<String, String> makeParamMap(NodeList paramList){
		Map<String, String> paramMap = new HashMap<String, String>();
		for (int j = 0; j < paramList.getLength(); j++) {
			Node node = paramList.item(j);
           //   if (cNode instanceof Element) {
			String paramName = node.getNodeName();
			String content = node.getTextContent();
			paramMap.put(paramName,content);
		}
   
		//for debugging purposes
		System.out.println("print paramMap");
		for (String string : paramMap.keySet()){
			System.out.print(string + ": ");
			System.out.println(paramMap.get(string));
		}		
		return paramMap;
	}
		
	public Map<String, String> getGridParamMap(){
		return myGridParamMap;
	}
	
	public Map<String, String> getCellParamMap(){
		return myCellParamMap;
	}
		
}

	


   
 
