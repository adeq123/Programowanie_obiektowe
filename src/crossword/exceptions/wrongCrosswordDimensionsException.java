package crossword.exceptions;

public class wrongCrosswordDimensionsException extends Exception {

	private static final long serialVersionUID = 1L; // serial version

	/**
	 * Constructor
	 */
	public wrongCrosswordDimensionsException() {
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 *            - information about exception
	 */
	public wrongCrosswordDimensionsException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 *            - cause of exception
	 */
	public wrongCrosswordDimensionsException(Throwable cause) {
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
	public wrongCrosswordDimensionsException(String message, Throwable cause) {
		super(message, cause);
	}
}
