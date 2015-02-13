// This entire file is part of my masterpiece.
// Callie Mao

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
 * 
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
		myNodeList = doc.getDocumentElement().getChildNodes();
		parseInitialTags();
	}

	/**
	 * Parses <initParam> <cellParam> <simParam> and other first-level tags and
	 * stores them in respective instance variable lists
	 */
	public void parseInitialTags() {
		for (int i = 0; i < myNodeList.getLength(); i++) {
			Node node = myNodeList.item(i);
			if (node instanceof Element) {
				NodeList paramList = node.getChildNodes();
				switch (node.getNodeName()) {
				case Strings.INITIALIZATION_PARAMETERS:
					myInitParam = makeParamMap(paramList);
					break;
				case Strings.CELL_PARAMETERS:
					myCellParamList = makeListOfParamMaps(paramList);
					break;
				case Strings.SIMULATION_PARAMETERS:
					mySimParam = makeParamMap(paramList);
				}
			}
		}
	}

	/**
	 * Creates a HashMap of parameters for a given list of nodes.
	 * 
	 * @param paramList
	 * @return paramMap (a map of node names and node values)
	 */
	private HashMap<String, String> makeParamMap(NodeList paramList) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		for (int j = 0; j < paramList.getLength(); j++) {
			Node subnode = paramList.item(j);
			if (subnode instanceof Element) {
				paramMap.put(subnode.getNodeName(), subnode.getTextContent());
			}
		}

		return paramMap;
	}

	/**
	 * Makes a list of HashMaps containing properties within 2-levels of
	 * subnodes.
	 * 
	 * @param NodeList
	 * @return cellStates (A list of maps containing the properties (subnodes of cells) of
	 *         cells (subnodes))
	 */
	private List<HashMap<String, String>> makeListOfParamMaps(NodeList paramList) {
		List<HashMap<String, String>> cellStates = new ArrayList<HashMap<String, String>>();
		for (int j = 0; j < paramList.getLength(); j++) {
			Node subNode = paramList.item(j);
			if (subNode instanceof Element) {
				HashMap<String, String> cellStateMap = makeParamMap(subNode
						.getChildNodes());
				cellStates.add(cellStateMap);
			}
		}
		return cellStates;
	}

	/** The below methods allow CellSociety to access specific instance variables.
	* The maps are separated to reduce the number properties that can be 
	* accessed from any specific method(creation of grid, creation of cells, etc.)
	*/ 
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
