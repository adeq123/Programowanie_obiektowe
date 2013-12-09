package crossword.browse;

import java.io.IOException;
import java.util.LinkedList;

import crossword.board.Crossword;

/**
 * 
 * @author krzysztof
 * 
 */
public interface Writer {

	/**
	 * 
	 * @param path
	 *            - path to file
	 * @param crosswordsList
	 *            - list of crosswords to write to file
	 * @throws IOException
	 */
	public void write(String path, LinkedList<Crossword> crosswordsList)
			throws IOException;

	/**
	 * Creatures unique ID
	 * 
	 * @return value of unique ID
	 */
	public long getUniqueID();
}
