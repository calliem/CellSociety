package cellsociety;

/**
 * This class represents an error in parsing XML files
 * @author Callie Mao
 *
 */
public class XMLParserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Create an exception based on an issue in the XML Parser.
	 */
	public XMLParserException(String message, Object... values) {
		super(String.format(message, values));
	}
}