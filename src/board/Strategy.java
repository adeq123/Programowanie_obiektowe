package board;

import dictionary.CwEntry;

/**
 * 
 * @author krzysztof
 * 
 */
public abstract class Strategy {

	/**
	 * Abstract method that finds CwEntry
	 * 
	 * @param cw
	 *            - Crossowrd
	 * @return found CwEntry
	 */
	public abstract CwEntry findEntry(Crossword cw);

	/**
	 * Updates board
	 * 
	 * @param b
	 *            - Board
	 * @param e
	 *            - CwEntry to add to board
	 */
	public abstract void updateBoard(Board b, CwEntry e);
}
