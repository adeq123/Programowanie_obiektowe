package board;

import java.util.Iterator;

import dictionary.CwEntry;

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
	 */
	public static void main(String[] args) {
		Crossword crossword = new Crossword(11, 20, "cwdb.txt"); // od 2 do 12
		ConcretStrategy s = new ConcretStrategy();
		crossword.generate(s);
		Iterator<CwEntry> iter = crossword.getROEntryIter();
		CwEntry c;
		while (iter.hasNext() == true) {
			c = iter.next();
		//	System.out.println(c.getWord());
		//	System.out.println(c.getClue());
		}
		for (int i = 0; i < crossword.getBoard().getHeight(); i++) {
			for (int j = 0; j < crossword.getBoard().getWidth(); j++) {
				if (crossword.getBoard().getCell(i, j).content != null)
					System.out
							.print(crossword.getBoard().getCell(i, j).content);
				else
					System.out.print(".");
			}
			System.out.println();
		}
	}
}
