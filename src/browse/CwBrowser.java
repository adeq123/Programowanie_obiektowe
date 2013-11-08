package browse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import board.ConcretStrategy;
import board.Crossword;
import dictionary.CwEntry;
import exceptions.noPossibilityToGenerateCrosswordException;
import exceptions.wrongCrosswordDimensionsException;

/**
 * 
 * @author krzysztof
 * 
 */
public class CwBrowser {
	String path; // path to files
	public CwReader cwreader = new CwReader(); // to file reader
	public CwWriter cwwriter = new CwWriter(); // to file writer
	public LinkedList<Crossword> crosswordsList = new LinkedList<Crossword>(); // list
																				// of
																				// crosswords

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
	 * 
	 * @param height
	 *            - crossword's height
	 * @param width
	 *            - crossword's width
	 * @param dictionaryFilename
	 *            - name of directory
	 * @throws noPossibilityToGenerateCrosswordException
	 * @throws wrongCrosswordDimensionsException
	 */
	public void generateCrossword(int height, int width,
			String dictionaryFilename)
			throws noPossibilityToGenerateCrosswordException,
			wrongCrosswordDimensionsException {
		Crossword cw = new Crossword(height, width, dictionaryFilename);
		ConcretStrategy s = new ConcretStrategy();
		cw.generate(s);
		crosswordsList.add(cw);
	}

	/**
	 * Saves crosswords in specified directory
	 * 
	 * @throws IOException
	 * 
	 */
	public void saveCrosswords() throws IOException {
		cwwriter.write(path, crosswordsList);
	}

	public void loadCrosswords() throws NumberFormatException, FileNotFoundException, IOException {
		crosswordsList = cwreader.getAllCws(path);
	}

	public void browseCrosswords() {
		System.out.println("SSSSSSSSSS");
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
		try {
			cwbrowser.generateCrossword(10, 20, "cwdb.txt");
			cwbrowser.generateCrossword(12, 20, "cwdb.txt");
			cwbrowser.generateCrossword(11, 20, "cwdb.txt");
		} catch (wrongCrosswordDimensionsException e) {
			e.printStackTrace();
		}

		try {
			cwbrowser.saveCrosswords();
		} catch (IOException e) {
			e.printStackTrace();
		}

/*	 try {
		cwbrowser.loadCrosswords();
	} catch (NumberFormatException e) {
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}*/

		cwbrowser.browseCrosswords();

		System.out.println("KONIEC");
	}
}
