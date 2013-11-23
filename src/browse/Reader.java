package browse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

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
	 * @return list of crossword from directory with files including crosswords
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public LinkedList<Crossword> getAllCws(String path)
			throws FileNotFoundException, IOException;
}
