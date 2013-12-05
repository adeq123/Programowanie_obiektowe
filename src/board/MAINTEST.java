package board;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import dictionary.CwEntry;
import exceptions.noPossibilityToGenerateCrosswordException;
import exceptions.wrongCrosswordDimensionsException;

/**
 * 
 * @author krzysztof
 * 
 */
public class MAINTEST {

	/**
	 * Main function
	 * 
	 * @param args
	 *            - arguments of main
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Crossword crossword;
		try {
			crossword = new Crossword(20, 20, "tak.txt");

			HardStrategy s = new HardStrategy();
			try {
				crossword.generate(s);
			} catch (noPossibilityToGenerateCrosswordException e) {
				e.printStackTrace();
			}
			Iterator<CwEntry> iter = crossword.getROEntryIter();
			CwEntry c;
			while (iter.hasNext() == true) {
				c = iter.next();
				// System.out.println(c.getWord());
				// System.out.println(c.getClue());
			}
		/*	for (int i = 0; i < crossword.getBoard().getHeight(); i++) {
				for (int j = 0; j < crossword.getBoard().getWidth(); j++) {
					if (crossword.getBoard().getCell(i, j).content != null)
						System.out
								.print(crossword.getBoard().getCell(i, j).content);
					else
						System.out.print(".");
				}
				System.out.println();
			}*/
		} catch (wrongCrosswordDimensionsException e1) {
			e1.printStackTrace();
		} // od 2 do 12
	}
}
