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
    private List<HashMap<String, String>> myCellParamList = new ArrayList<HashMap<String, String>>(); //state name maps to state color
    //why don't i just add all of this to the hashmap of mycellparam and get the values that i need? 
	    
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
			//<initParam> <cellParam> <simParam> tags
             Node node = myNodeList.item(i);
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
     				break;
            	}
             }
		}
	}
	
	//should these just be void? they create a new temporary map which doesn't seem necessary
	private Map<String, String> makeParamMap(NodeList paramList){
		Map<String, String> paramMap = new HashMap<String, String>();
		for (int j = 0; j < paramList.getLength(); j++) {
			Node node = paramList.item(j);
			if (node instanceof Element){
				String paramName = node.getNodeName();
				String content = node.getTextContent();
				paramMap.put(paramName,content);
			}
			if (node.hasChildNodes()){
				NodeList nodeList = node.getChildNodes();
				for (int i = 0; i < nodeList.getLength(); i++){
					Node subNode = nodeList.item(i);
					if (subNode instanceof Element)
						System.out.println("hi");
				}
			}
		}
   
		//for debugging purposes
	/*	System.out.println("print paramMap");
		for (String string : paramMap.keySet()){
			System.out.print(string + ": ");
			System.out.println(paramMap.get(string));
		}		*/
		return paramMap;
	}
	
	private List<HashMap<String, String>> makeCellParamList(NodeList paramList){
		List<HashMap<String, String>> cellStates = new ArrayList<HashMap<String, String>>(); //is there any way to do map = hashmap inside of the arraylist?

		//for each cell
		for (int j = 0; j < paramList.getLength(); j++) {
			HashMap<String, String> cellParamMap = new HashMap<String, String>();
			Node node = paramList.item(j);
			if (node instanceof Element){
				//String paramName = node.getNodeName();
				Element element = (Element) node;
	                //every cell must have a state and a color and thus these have their own attributes
				String stateName = element.getAttribute("state"); //these strings are probably bad, although we can pass them in as constants
				cellParamMap.put("state", stateName); //this is hardcoded the 2nd time
				String color = element.getAttribute("color");
				cellParamMap.put("color", color);
			//if cell has more properties
			if (node.hasChildNodes()){
				NodeList nodelist = node.getChildNodes();
				for (int i = 0; i < nodelist.getLength(); i++){
					Node node2 = nodelist.item(i);
					if (node2 instanceof Element){
						System.out.println("lowerlevel: " + node2.getNodeName() + node2.getTextContent());
						cellParamMap.put(node2.getNodeName(), node2.getTextContent());
					}
				}
			}
			cellStates.add(cellParamMap);
			}
		}
		
		//for testing
	/*	System.out.println(cellStates.size());
		System.out.println("print cellStateList");
		for (int i = 0; i < cellStates.size(); i ++){
			for (String string: cellStates.get(i).keySet()){
				System.out.println(i + " " + string + " " + cellStates.get(i));
			System.out.println("----------");
			}
		}*/
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




   
 
