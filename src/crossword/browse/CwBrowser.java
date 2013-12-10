package crossword.browse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import crossword.board.Crossword;
import crossword.dictionary.InteliCwDB;
import crossword.exceptions.noCrosswordFoundToLoadException;
import crossword.exceptions.noPossibilityToGenerateCrosswordException;
import crossword.exceptions.wrongCrosswordDimensionsException;
import crossword.strategy.Strategy;

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
	 * Constructor
	 */
	public CwBrowser() {
		cwreader = new CwReader();
		cwwriter = new CwWriter();
		actualCrossword = null;
		actualIndexOfCrossword = 0;
		actualDatabase = null;
		crosswordsList = new LinkedList<Crossword>();
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
	public void generateCrossword(int height, int width, Strategy s) throws wrongCrosswordDimensionsException, noPossibilityToGenerateCrosswordException {
		Crossword cw = new Crossword(height, width, actualDatabase, s);
		cw.generate(s);
		actualCrossword = cw;
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
	 * @throws noCrosswordFoundToLoadException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void loadCrosswords() throws noCrosswordFoundToLoadException, FileNotFoundException, IOException {
		LinkedList<Crossword> newCrosswordsList;
		newCrosswordsList = cwreader.getAllCws(path);
		crosswordsList.removeAll(crosswordsList);
		crosswordsList = newCrosswordsList;
		actualIndexOfCrossword = 0;
		actualCrossword = crosswordsList.get(0);
	}

	/**
	 * Returns the next crossword from list
	 * 
	 * @return crossword
	 */
	public Crossword nextCrossword() {
		actualIndexOfCrossword++;
		if (actualIndexOfCrossword >= crosswordsList.size()) {
			actualIndexOfCrossword = actualIndexOfCrossword % crosswordsList.size();
		}
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
		if (actualIndexOfCrossword < 0) {
			actualIndexOfCrossword = actualIndexOfCrossword + crosswordsList.size();
		}
		actualCrossword = crosswordsList.get(actualIndexOfCrossword);
		return crosswordsList.get(actualIndexOfCrossword);
	}
}