package cellsociety;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class XMLParser {
	private NodeList myNodeList;
    private Map<String, String> mySimParam = new HashMap<String, String>();
    private Map<String, String> myInitParam = new HashMap<String, String>();
    private List<HashMap<String, String>> myCellParamList = new ArrayList<HashMap<String, String>>(); 
    
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	    
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
			//parses <initParam> <cellParam> <simParam> and other first-level tags
             Node node = myNodeList.item(i);
             System.out.println(node.getNodeName());
             if (node instanceof Element) {  
            	switch (node.getNodeName()) {
     			case "initParam":
                    NodeList initParamList = node.getChildNodes();              
     				myInitParam = makeParamMap(initParamList);
     				break;
     			case "cellParam":
     				NodeList cellList = node.getChildNodes();
     				myCellParamList = makeCellParamList(cellList);
     				break;
     			case "simParam":
     				NodeList simParamList = node.getChildNodes();
     				mySimParam = makeParamMap(simParamList);
            	}
             }
		}
	}
	
	private Map<String, String> makeParamMap(NodeList paramList){
		Map<String, String> paramMap = new HashMap<String, String>();
		for (int j = 0; j < paramList.getLength(); j++) {
			Node node = paramList.item(j);
			if (node instanceof Element){
				String paramName = node.getNodeName();
				String content = node.getTextContent();
				paramMap.put(paramName,content);
			}
		}
   
		return paramMap;
	}
	
	private List<HashMap<String, String>> makeCellParamList(NodeList paramList){
		List<HashMap<String, String>> cellStates = new ArrayList<HashMap<String, String>>();

		//for each cell
		for (int j = 0; j < paramList.getLength(); j++) {
			HashMap<String, String> cellParamMap = new HashMap<String, String>();
			Node node = paramList.item(j);
			if (node instanceof Element){
				Element element = (Element) node;
				//input essential (state + color) elements into the cellParamMap
				String stateName = element.getAttribute("state"); 
				cellParamMap.put("state", stateName); 
				String color = element.getAttribute("color");
				cellParamMap.put("color", color);
				String name = element.getAttribute("name");
				cellParamMap.put("name", name);
			//if the cell has more properties (lower level nodes)
			if (node.hasChildNodes()){
				NodeList nodelist = node.getChildNodes();
				for (int i = 0; i < nodelist.getLength(); i++){
					Node node2 = nodelist.item(i);
					if (node2 instanceof Element){
						cellParamMap.put(node2.getNodeName(), node2.getTextContent());
					}
				//	System.out.println(node2.getNodeName());
				}
			}
			cellStates.add(cellParamMap);
			}
		}
		return cellStates;
	}
		
	public Map<String, String> getSimParamMap(){
		return mySimParam;
	}
	
	public Map<String, String> getInitParamMap(){
		return myInitParam;
	}
	
	public List<HashMap<String, String>> getCellParamList(){
		return myCellParamList;
	}

}




   
 
