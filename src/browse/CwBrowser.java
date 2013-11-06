package browse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import board.ConcretStrategy;
import board.Crossword;
import dictionary.CwEntry;

public class CwBrowser {
	String path;
	public CwReader cwreader = new CwReader();
	public CwWriter cwwriter = new CwWriter();
	public LinkedList<Crossword> crosswordsList = new LinkedList<Crossword>();

	public CwBrowser(String path) {
		this.path = path;
	}

	public void generateCrossword(int height, int width,
			String dictionaryFilename) {
		Crossword cw = new Crossword(height, width, dictionaryFilename);
		ConcretStrategy s = new ConcretStrategy();
		cw.generate(s);
		crosswordsList.add(cw);
	}

	public void saveCrosswords() {
		try {
			cwwriter.write(path, crosswordsList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadCrosswords() {
		try {
			crosswordsList = cwreader.getAllCws(path);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void browseCrosswords() {
		Iterator<Crossword> iter = crosswordsList.iterator();
		Crossword cw;
		while (iter.hasNext()) {
			cw = iter.next();

			for (int i = 0; i < cw.getBoard().getHeight(); i++) {
				for (int j = 0; j < cw.getBoard().getWidth(); j++) {
					if (cw.getBoard().getCell(i, j).content != null)
					{
						System.out.print(cw.getBoard().getCell(i, j).content);
					}
					else
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

	public static void main(String[] args) {
		CwBrowser cwbrowser = new CwBrowser(
				"/home/krzysztof/workspace/Programowanie_obiektowe/krzyzowki");
		cwbrowser.generateCrossword(10, 20, "cwdb.txt");
		cwbrowser.generateCrossword(12, 20, "cwdb.txt");
		cwbrowser.generateCrossword(11, 20, "cwdb.txt");

		cwbrowser.saveCrosswords();

	//	cwbrowser.loadCrosswords();
		
		cwbrowser.browseCrosswords();

		System.out.println("KONIEC");
	}
}
