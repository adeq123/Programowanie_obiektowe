package board;

import java.io.IOException;

import dictionary.CwDB;

public class MAINTEST {

	public static void main(String[] args) {
		CwDB cwdb = new CwDB("cwdb.txt");
		try {
			cwdb.saveDB("javassie.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Crossword crossword = new Crossword();
		ConcretStrategy s = new ConcretStrategy();
		crossword.generate(s);
		
		
		
		
	}

}
