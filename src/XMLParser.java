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
     				
     				//for debugging purposes
     				/*
     				System.out.println("print paramMap ============================");
     				System.out.println("print paramMap ============================");
     				for (String string : mySimParam.keySet()){
     					System.out.print(string + ": ");
     					System.out.println(mySimParam.get(string));
     				}		
     				System.out.println("print paramMap ============================");
     				break;*/
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
			//for future extensions, if any.
		/*	if (node.hasChildNodes()){
				NodeList nodeList = node.getChildNodes();
				for (int i = 0; i < nodeList.getLength(); i++){
					Node subNode = nodeList.item(i);
					if (subNode instanceof Element)
						System.out.println("hi"); 
				}
			}*/
		}
   
		return paramMap;
	}
	
	private List<HashMap<String, String>> makeCellParamList(NodeList paramList){
		List<HashMap<String, String>> cellStates = new ArrayList<HashMap<String, String>>(); //is there any way to do map = hashmap inside of the arraylist?

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
			//if the cell has more properties (lower level nodes)
			if (node.hasChildNodes()){
				NodeList nodelist = node.getChildNodes();
				for (int i = 0; i < nodelist.getLength(); i++){
					Node node2 = nodelist.item(i);
					if (node2 instanceof Element){
				//		System.out.println("lowerlevel: " + node2.getNodeName() + node2.getTextContent());
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




   
 
