package cellsociety;


public class XMLParserException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Create an exception based on an issue in our code.
     */
    public XMLParserException (String message, Object ... values) {
        super(String.format(message, values));
    }
}