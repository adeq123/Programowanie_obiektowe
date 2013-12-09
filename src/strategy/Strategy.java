package strategy;

import board.Board;
import board.Crossword;
import dictionary.CwEntry;
import exceptions.noPossibilityToGenerateCrosswordException;

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
	 *            - Crossword
	 * @return found CwEntry
	 * @throws noPossibilityToGenerateCrosswordException 
	 */
	public abstract CwEntry findEntry(Crossword cw) throws noPossibilityToGenerateCrosswordException;

	/**
	 * Updates board
	 * 
	 * @param b
	 *            - Board
	 * @param e
	 *            - CwEntry to add to board
	 */
	public abstract void updateBoard(Board b, CwEntry e);
	
	/**
	 * returns the name of strategy
	 */
	public abstract String toString();
}
