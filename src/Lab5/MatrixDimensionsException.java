package Lab5;

public class MatrixDimensionsException extends Exception {
	
	private static final long serialVersionUID = 1L;
	Matrix matrix;

	/**
	 * Constructor
	 */
	public MatrixDimensionsException() {
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 *            - information about exception
	 */
	public MatrixDimensionsException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 *            - cause of exception
	 */
	public MatrixDimensionsException(Throwable cause) {
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
	public MatrixDimensionsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Constructor
	 * 
	 * @param matrix
	 *            - matrix
	 */
	public MatrixDimensionsException(Matrix matrix) {
		this.matrix = matrix;
	}
}

