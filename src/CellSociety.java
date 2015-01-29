
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javafx.stage.Stage;

public class CellSociety {

	CellSocietyView myView;
	
	public CellSociety(Stage s) throws ParserConfigurationException, SAXException, IOException {
		
		myView = new CellSocietyView(s);
		
	}

}