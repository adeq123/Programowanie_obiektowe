package Lab5;

/**
 * 
 * @author krzysztof
 *
 */
public class UnproperSequenceOfCharsException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public UnproperSequenceOfCharsException() {
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 *            - information about exception
	 */
	public UnproperSequenceOfCharsException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 *            - cause of exception
	 */
	public UnproperSequenceOfCharsException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 *            - information about exception
	 * @param cause
	 *            - cause of exception
	 */
	public UnproperSequenceOfCharsException(String message, Throwable cause) {
		super(message, cause);
	}
}
