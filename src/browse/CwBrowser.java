package browse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import board.Crossword;
import board.Strategy;
import dictionary.CwEntry;
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
	//Strategy s;
	//EasytStrategy e; // Easy strategy used to generate crosswords
	//HardStrategy h; // Hard strategy used to generate crosswords

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
		//e = new EasytStrategy();
		//h = new HardStrategy();
	}

	/**
	 * Setter
	 * 
	 * @param s - new strategy
	 */
	public void setStrategy(Strategy s){
	//	this.s = s;
	}
	
	/**
	 * Getter
	 * 
	 * @return actual strategy
	 */
	//public Strategy getStrategy(){
	//	return s; 
	//}
	
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
	public void generateCrossword(int height, int width, Strategy s)
			throws wrongCrosswordDimensionsException,
			noPossibilityToGenerateCrosswordException {
		Crossword cw = new Crossword(height, width, actualDatabase,s);
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
		actualCrossword = crosswordsList.get(actualIndexOfCrossword);
		return crosswordsList.get(actualIndexOfCrossword);
	}
	
	public void browseCrosswords() {
		Iterator<Crossword> iter = crosswordsList.iterator();
		Crossword cw;
		while (iter.hasNext()) {
			cw = iter.next();
			for (int i = 0; i < cw.getBoard().getHeight(); i++) {
				for (int j = 0; j < cw.getBoard().getWidth(); j++) {
					if (cw.getBoard().getCell(i, j).content != null) {
						System.out.print(cw.getBoard().getCell(i, j).content);
					} else
						System.out.print(".");
				}
				System.out.println();
			}

			System.out.println();

			Iterator<CwEntry> it = cw.getEntries().iterator();
			CwEntry c;
			while (it.hasNext()) {
				c = it.next();
				System.out.println(c.getWord());
				System.out.println(c.getClue());
			}

			System.out.println();
		}
	}

	public static void main(String[] args)
			throws noPossibilityToGenerateCrosswordException {
		CwBrowser cwbrowser = new CwBrowser(
				"/home/krzysztof/workspace/Programowanie_obiektowe/krzyzowki");
		// try {
		// cwbrowser.generateCrossword(10, 20, "cwdb.txt");
		// cwbrowser.generateCrossword(12, 20, "cwdb.txt");
		// cwbrowser.generateCrossword(11, 20, "cwdb.txt");
		// } catch (wrongCrosswordDimensionsException e) {
		// e.printStackTrace();
		// }

		try {
			cwbrowser.saveCrosswords();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * try { cwbrowser.loadCrosswords(); } catch (NumberFormatException e) {
		 * e.printStackTrace(); } catch (FileNotFoundException e) {
		 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
		 */

		cwbrowser.browseCrosswords();

		System.out.println("KONIEC");
	}
}
