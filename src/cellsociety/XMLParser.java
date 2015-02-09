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

/** 
 * This class reads and parses through tags and values from a given XML file 
 * @author Callie Mao
 *
 */
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

		// input data into nodes
		myNodeList = doc.getDocumentElement().getChildNodes();
		parseInitialTags();
	}

	/**
	 * Parses <initParam> <cellParam> <simParam> and other first-level tags and stores them in instance variable lists
	 */
	public void parseInitialTags() {
		for (int i = 0; i < myNodeList.getLength(); i++) {	
			Node node = myNodeList.item(i);
			if (node instanceof Element) {
				switch (node.getNodeName()) {
				case Strings.INITIALIZATION_PARAMETERS:
					NodeList initParamList = node.getChildNodes();
					myInitParam = makeParamMap(initParamList);
					break;
				case Strings.CELL_PARAMETERS:
					NodeList cellList = node.getChildNodes();
					myCellParamList = makeCellParamList(cellList);
					break;
				case Strings.SIMULATION_PARAMETERS:
					NodeList simParamList = node.getChildNodes();
					mySimParam = makeParamMap(simParamList);
				}
			}
		}
	}

	/**
	 * Creates a Map of parameters for a given list of nodes. 
	 * @param paramList
	 * @return a map of subnodes
	 */
	private Map<String, String> makeParamMap(NodeList paramList) {
		Map<String, String> paramMap = new HashMap<String, String>();
		for (int j = 0; j < paramList.getLength(); j++) {
			Node node = paramList.item(j);
			if (node instanceof Element) {
				String paramName = node.getNodeName();
				String content = node.getTextContent();
				paramMap.put(paramName, content);
			}
		}
		return paramMap;
	}
	
	/**
	 * Make a list of a cell's parameters and store it in a map. The key (the XML tag) will map to the value (the value within the XML tag)
	 * @param paramList
	 * @return
	 */
	private List<HashMap<String, String>> makeCellParamList(NodeList paramList) {
		List<HashMap<String, String>> cellStates = new ArrayList<HashMap<String, String>>();
		for (int j = 0; j < paramList.getLength(); j++) {
			HashMap<String, String> cellParamMap = new HashMap<String, String>();
			Node node = paramList.item(j);
			if (node instanceof Element) {
				Element element = (Element) node;
				parseCellAttributes(cellParamMap, element);
				parseChildNodes(cellParamMap, node); // if the cell has more properties (subnodes)			 
				cellStates.add(cellParamMap);
			}
		}
		return cellStates;
	}
	
	/**
	 * Parses through the attributes (essential values: state, color, and name) of a given element and stores them in cellParamMap
	 * @param cellParamMap
	 * @param element
	 */
	private void parseCellAttributes (HashMap<String, String> cellParamMap, Element element){
		String stateName = element.getAttribute(Strings.CELL_STATE);
		cellParamMap.put(Strings.CELL_STATE, stateName);
		String color = element.getAttribute(Strings.CELL_COLOR);
		cellParamMap.put(Strings.CELL_COLOR, color);
		String name = element.getAttribute(Strings.CELL_NAME);
		cellParamMap.put(Strings.CELL_NAME, name);
	}
	
	/**
	 * Parses the child nodes of a given node (if the node has any child nodes)
	 * @param cellParamMap
	 * @param node
	 */
	private void parseChildNodes(HashMap<String, String> cellParamMap, Node node){
		if (node.hasChildNodes()) {
			NodeList nodelist = node.getChildNodes();
			for (int i = 0; i < nodelist.getLength(); i++) {
				Node subNode = nodelist.item(i);
				if (subNode instanceof Element) {
					cellParamMap.put(subNode.getNodeName(), subNode.getTextContent());
				}
			}
		}
	}

	public Map<String, String> getSimParamMap() {
		return mySimParam;
	}

	public Map<String, String> getInitParamMap() {
		return myInitParam;
	}

	public List<HashMap<String, String>> getCellParamList() {
		return myCellParamList;
	}

}
