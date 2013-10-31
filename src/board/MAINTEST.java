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
		Crossword crossword = new Crossword(20, 20, "cwdb.txt");
		ConcretStrategy s = new ConcretStrategy();
		crossword.generate(s);
		Iterator<CwEntry> iter = crossword.getROEntryIter();
		CwEntry c;
		while (iter.hasNext() == true) {
			c = iter.next();
			System.out.println(c.getWord());
			System.out.println(c.getClue());
		}
	}
}
