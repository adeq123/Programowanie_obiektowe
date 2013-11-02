package browse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import board.Board;
import board.BoardCell;
import board.Crossword;
import dictionary.CwEntry;
import dictionary.CwEntry.Direction;

public class CwReader implements Reader {

	@Override
	public LinkedList<Crossword> getAllCws(String path) throws NumberFormatException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		System.out.println("A");
		LinkedList<Crossword> crosswords = new LinkedList<Crossword>();
		File file = new File(path);
		if (file.isDirectory() == true) {
			String[] files = file.list();
			for (int i = 0; i < files.length; i++) {
				System.out.println("A");
				System.out.println(files[i]);
				Crossword cw = new Crossword(Long.parseLong(files[i]));

				FileReader fileReader = new FileReader(path + "/" + files[i]);
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				int height = Integer.parseInt(bufferedReader.readLine());
				int width = Integer.parseInt(bufferedReader.readLine());
				 System.out.println(height);
				 System.out.println(width);
				Board b = new Board(height, width);
				for (int k = 0; k < height; k++) {
					for (int j = 0; j < width; j++) {
						BoardCell bc = new BoardCell();
						String s = bufferedReader.readLine();
						if (s.equals("null") == true)
							bc.setContent(null);
						else
							bc.setContent(s);
						// bufferedReader.readLine();
					}
				}
				cw.setBoard(b);
				
				LinkedList<CwEntry> entries = new LinkedList<CwEntry>();
				String word;
				String clue;
				while ((word = bufferedReader.readLine()) != null) {
					clue = bufferedReader.readLine();
					entries.add(new CwEntry(word, clue, 0, 0, Direction.HORIZ));
				}
				cw.setEntries(entries);
				
				crosswords.add(cw);
				
				if (bufferedReader != null)
					bufferedReader.close();
			}
		}
		return crosswords;
	}
}
