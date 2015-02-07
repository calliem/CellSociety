
public class XMLException extends RuntimeException {
	
	/**
     * Create an exception based on an issue in our code.
     */
    public XMLException (String message){
    	super(message);	
    	//somehow return the message so that the error box can ind it and use it
    }
	
    
    //get the xmlexception message so that when you catch you can do this 
    
    
    
    
    
    
    /*
	public XMLException (String message, Object ... values) {
        super(String.format(message, values));
    }
    
    /**
     * Create an exception based on a caught exception with a different message.
     *
    public XMLException (Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     *
    public XMLException (Throwable exception) {
        super(exception);
    }*/

	
}
