package crossword.browse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import crossword.board.Crossword;
import crossword.exceptions.noCrosswordFoundToLoadException;

/**
 * 
 * @author krzysztof
 * 
 */
public interface Reader {

	/**
	 * 
	 * @param path
	 *            - path to file
	 * @return list of crosswords from directory
	 * @throws noCrosswordFoundToLoadException 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public LinkedList<Crossword> getAllCws(String path)
			throws  noCrosswordFoundToLoadException, FileNotFoundException, IOException;
}
