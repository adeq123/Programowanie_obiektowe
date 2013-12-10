package crossword.exceptions;

public class noCrosswordFoundToLoadException extends Exception {

	private static final long serialVersionUID = 1L; // default serial version

	/**
	 * Constructor
	 */
	public noCrosswordFoundToLoadException() {
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 *            - information about exception
	 */
	public noCrosswordFoundToLoadException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 *            - cause of exception
	 */
	public noCrosswordFoundToLoadException(Throwable cause) {
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
	public noCrosswordFoundToLoadException(String message, Throwable cause) {
		super(message, cause);
	}
}