
import javafx.scene.paint.Color;

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
    private Map<String, String> myCellParam = new HashMap<String, String>();
    private List<CellState> myCellStateList = new ArrayList<CellState>(); //state name maps to state color
	
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
     			case "simParam":
                    NodeList simParamList = node.getChildNodes(); //use instance instead of global?                  
     				mySimParam = makeParamMap(simParamList);
     				//somehow pass this in just once later  on instead of calling makeParamMap each time
                    //maybe just use one myParamList variable and continuously override it?
     				break;
     			case "cellParam":
     				NodeList cellParamList = node.getChildNodes();
     				myCellParam = makeParamMap(cellParamList);
     				break;
     			case "cellStates":
     				NodeList cellStatesList = node.getChildNodes();
     				myCellStateList = makeCellStateList(cellStatesList);
     				break;
            	}
             }
		}
	}
	
	//should these just be void? they create a new temporary map which doesn't seem necessary
	private Map<String, String> makeParamMap(NodeList paramList){
		Map<String, String> paramMap = new HashMap<String, String>();
		System.out.println(paramList.getLength());
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
	
	private List<CellState> makeCellStateList(NodeList paramList){
		List<CellState> cellStates = new ArrayList<CellState>();
		for (int j = 0; j < paramList.getLength(); j++) {		
			//<cell> tag
			Node node = paramList.item(j);
			if (node instanceof Element && node.getNodeName().equals("cell")) { //in case other parameters are added
                Element element = (Element) node;
				String stateName = element.getAttribute("state");
				System.out.println("stname" + stateName);
				Color color = Color.valueOf(element.getAttribute("color"));
				int[] locations = stringToIntArray(element.getTextContent());
				CellState newCell = new CellState(stateName, color, locations); //is this considered "bad" design?
				cellStates.add(newCell);
				//cellsociety will take in statename and location. the cell must be be set to the specific color and then never changed again (no set methods)
			}
		}
		
		//for debugging
		System.out.println("print cellStateList");
		for (CellState state : cellStates){
			System.out.println("statename: " + state.toString());
			System.out.println("color: " + state.getColor().toString());
			//System.out.println("locations: " + state.getState());
			System.out.println("----------");

		}		
		return cellStates;
	}
				
		//changes a string inputted as integers with spaces		
	private int[] stringToIntArray(String string){
		String[] split = string.split(" ");
		for (String s: split){
			System.out.println(s);
		}
	    int[] intArray = new int[split.length];
	    for (int i = 0; i < split.length; i++) {
	        intArray[i] = Integer.parseInt(split[i]);
	    }
	    return intArray;	
	}
				
		
	public Map<String, String> getSimParamMap(){
		return mySimParam;
	}
	
	public Map<String, String> getCellParamMap(){
		return myCellParam;
	}
	
	public List<CellState> getCellStateList(){
		return myCellStateList;
	}
		
	
	//REMOVE #TEXT NODES

}




   
 
