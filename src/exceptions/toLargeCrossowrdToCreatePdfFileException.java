package exceptions;

public class toLargeCrossowrdToCreatePdfFileException extends Exception {

	private static final long serialVersionUID = 1L; //serial version
	
	/**
	 * Constructor
	 */
	public toLargeCrossowrdToCreatePdfFileException() {
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 *            - information about exception
	 */
	public toLargeCrossowrdToCreatePdfFileException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 *            - cause of exception
	 */
	public toLargeCrossowrdToCreatePdfFileException(Throwable cause) {
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
	public toLargeCrossowrdToCreatePdfFileException(String message, Throwable cause) {
		super(message, cause);
	}
}
