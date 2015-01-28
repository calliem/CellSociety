
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

	/*
	
	private void makeGridParamMap(){
		myGridParamMap = new HashMap<String, String>();
		for (int j = 0; j < myGridParamList.getLength(); j++) {
			Node gridParamNode = myGridParamList.item(j);
           //   if (cNode instanceof Element) {
			String paramName = gridParamNode.getNodeName();
			String content = gridParamNode.getTextContent();
			myGridParamMap.put(paramName,content);
		}
   
		System.out.println("print gridparammap");
		for (String string : myGridParamMap.keySet()){
			System.out.print(string + ": ");
			System.out.println(myGridParamMap.get(string));
		}
	}
	
	private void makeCellParamMap(){
		myCellParamMap = new HashMap<String, String>();
		for (int j = 0; j < myCellParamList.getLength(); j++) {
			Node cellParamNode = myCellParamList.item(j);
           //   if (cNode instanceof Element) {
			String paramName = cellParamNode.getNodeName();
			String content = cellParamNode.getTextContent();
			myCellParamMap.put(paramName,content);
		}
		
		System.out.println("print cellparammap");
		for (String string : myCellParamMap.keySet()){
			System.out.print(string + ": ");
			System.out.println(myCellParamMap.get(string));
		}
	}
	
	
	
	
}
	

//http://stackoverflow.com/questions/978810/how-to-strip-whitespace-only-text-nodes-from-a-dom-before-serialization
/*try {
    DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

    Document doc = builder.parse(input);
    doc.getDocumentElement().normalize();   //added in since the edit
    NodeList nodd = doc.getElementsByTagName("grandparent");
    for (int x = 0; x < nodd.getLength(); x++){
        Node node = nodd.item(x);
        NodeList nodes = node.getChildNodes();
        for(int y = 0; y < nodes.getLength(); y++){
            Node n = nodes.item(y);
            System.out.println(n.getNodeName());
        }
    }
}*/

/*File fXmlFile = new File(filepath);
DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
Document doc = dBuilder.parse(fXmlFile);
doc.getDocumentElement().normalize();
NodeList nList = doc.getElementsByTagName("info");*/
	

   
 
