package browse;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import board.ConcretStrategy;
import board.Crossword;

public class CwBrowser {
	public CwReader cwreader = new CwReader();
	public CwWriter cwwriter = new CwWriter();
	public LinkedList<Crossword> crosswordsList = new LinkedList<Crossword>();

	public void generateCrossword(int height, int width,
			String dictionaryFilename) {
		Crossword cw = new Crossword(height, width, dictionaryFilename);
		ConcretStrategy s = new ConcretStrategy();
		cw.generate(s);
		crosswordsList.add(cw);
	}

	public void saveCrosswords() {
		Iterator<Crossword> iter = crosswordsList.iterator();
		while (iter.hasNext()) {
			try {
				cwwriter.write(iter.next());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		CwBrowser cwbrowser = new CwBrowser();
		cwbrowser.generateCrossword(10, 20, "cwdb.txt");

		cwbrowser.saveCrosswords();

		System.out.println("KONIEC");
	}

}
