package crossword.exceptions;

public class noPossibilityToGenerateCrosswordException extends Exception {

	private static final long serialVersionUID = 1L; //serial version

	/**
	 * Constructor
	 */
	public noPossibilityToGenerateCrosswordException() {
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 *            - information about exception
	 */
	public noPossibilityToGenerateCrosswordException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 *            - cause of exception
	 */
	public noPossibilityToGenerateCrosswordException(Throwable cause) {
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
	public noPossibilityToGenerateCrosswordException(String message, Throwable cause) {
		super(message, cause);
	}
}
