package browse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import exceptions.noCrosswordFoundToLoadException;

import board.Crossword;

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
