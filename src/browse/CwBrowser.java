package browse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import board.Crossword;
import board.EasytStrategy;
import dictionary.InteliCwDB;
import exceptions.noPossibilityToGenerateCrosswordException;
import exceptions.wrongCrosswordDimensionsException;

/**
 * 
 * @author krzysztof
 * 
 */
public class CwBrowser {
	String path; // path to files
	public CwReader cwreader; // to file reader
	public CwWriter cwwriter; // to file writer
	public Crossword actualCrossword; // actual crossword
	public int actualIndexOfCrossword; // actual index of crossword
	public InteliCwDB actualDatabase; // actual database
	public LinkedList<Crossword> crosswordsList; // list of crosswords
	EasytStrategy s; // Strategy used to generate crosswords

	/**
	 * Constructor
	 */
	public CwBrowser() {
		cwreader = new CwReader();
		cwwriter = new CwWriter();
		actualCrossword = null;
		actualIndexOfCrossword = 0;
		actualDatabase = null;
		crosswordsList = new LinkedList<Crossword>();
		s = new EasytStrategy();
	}

	/**
	 * Setter
	 * 
	 * @param actual
	 *            - actual path
	 */
	public void setPath(String actual) {
		this.path = actual;
	}

	/**
	 * Getter
	 * 
	 * @return actual path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Getter
	 * 
	 * @return actual database
	 */
	public InteliCwDB getDatabase() {
		return actualDatabase;
	}

	/**
	 * Setter
	 * 
	 * @param actual
	 *            - actual database
	 */
	public void setDatabase(InteliCwDB actual) {
		this.actualDatabase = actual;
	}

	/**
	 * Getter
	 * 
	 * @return actual crossword
	 */
	public Crossword getCrossword() {
		return actualCrossword;
	}

	/**
	 * Setter
	 * 
	 * @param actual
	 *            - new actual crossword
	 */
	public void setCrossword(Crossword actual) {
		this.actualCrossword = actual;
	}

	/**
	 * Constructor
	 * 
	 * @param path
	 *            - path to directory
	 */
	public CwBrowser(String path) {
		this.path = path;
	}

	/**
	 * Generates crossword
	 * 
	 * @param height
	 *            - crossword's height
	 * @param width
	 *            - crossword's width
	 * @throws noPossibilityToGenerateCrosswordException
	 * @throws wrongCrosswordDimensionsException
	 */
	public void generateCrossword(int height, int width)
			throws wrongCrosswordDimensionsException,
			noPossibilityToGenerateCrosswordException {
		Crossword cw = new Crossword(height, width, actualDatabase);
		actualCrossword = cw;
		cw.generate(s);
	}

	/**
	 * Saves crosswords in specified directory
	 * 
	 * @throws IOException
	 */
	public void saveCrosswords() throws IOException {
		cwwriter.write(path, crosswordsList);
	}

	/**
	 * Saves crossword to file
	 * 
	 * @param crossword
	 *            - crossword to save
	 * @throws IOException
	 */
	public void saveCrosswords(Crossword crossword) throws IOException {
		LinkedList<Crossword> list = new LinkedList<Crossword>();
		list.add(crossword);
		cwwriter.write(path, list);
	}

	/**
	 * Loads crosswords from file
	 * 
	 * @throws NumberFormatException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void loadCrosswords() throws NumberFormatException,
			FileNotFoundException, IOException {
		actualCrossword = null;
		crosswordsList.removeAll(crosswordsList);
		actualIndexOfCrossword = 0;
		crosswordsList = cwreader.getAllCws(path);
		if (crosswordsList.size() > 0) {
			actualCrossword = crosswordsList.get(0);
		}
	}

	/**
	 * Returns the next crossword from list
	 * 
	 * @return crossword
	 */
	public Crossword nextCrossword() {
		actualIndexOfCrossword++;
		if (actualIndexOfCrossword >= crosswordsList.size())
			actualIndexOfCrossword = actualIndexOfCrossword
					% crosswordsList.size();
		System.out.println(actualIndexOfCrossword);
		actualCrossword = crosswordsList.get(actualIndexOfCrossword);
		return crosswordsList.get(actualIndexOfCrossword);
	}

	/**
	 * Returns the previous crossword from list
	 * 
	 * @return crossword
	 */
	public Crossword previousCrossword() {
		actualIndexOfCrossword--;
		if (actualIndexOfCrossword < 0)
			actualIndexOfCrossword = actualIndexOfCrossword
					+ crosswordsList.size();
		System.out.println(actualIndexOfCrossword);
		actualCrossword = crosswordsList.get(actualIndexOfCrossword);
		return crosswordsList.get(actualIndexOfCrossword);
	}
}
