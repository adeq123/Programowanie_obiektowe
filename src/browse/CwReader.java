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

/**
 * 
 * @author krzysztof
 * 
 */
public class CwReader implements Reader {

	@Override
	public LinkedList<Crossword> getAllCws(String path)
			throws FileNotFoundException, IOException {
		LinkedList<Crossword> crosswords = new LinkedList<Crossword>();
		File file = new File(path);
		if (file.isDirectory() == true) {
			String[] files = file.list();
			for (int i = 0; i < files.length; i++) {
				long temp;
				try {
					temp = Long.parseLong(files[i]);
				} catch (NumberFormatException e) {
					continue;
				}
				Crossword cw = new Crossword(temp);
				FileReader fileReader = new FileReader(path + "/" + files[i]);
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				int height = Integer.parseInt(bufferedReader.readLine());
				int width = Integer.parseInt(bufferedReader.readLine());
				Board b = new Board(height, width);
				String line1, line2;
				LinkedList<CwEntry> entries = new LinkedList<CwEntry>();
				while ((line1 = bufferedReader.readLine()) != null) {
					String[] splitted = line1.split(" ");
					line2 = bufferedReader.readLine();
					entries.add(new CwEntry(splitted[3], line2, Integer
							.parseInt(splitted[1]), Integer
							.parseInt(splitted[2]), Direction
							.valueOf(splitted[0])));
					for (int j = 0; j < splitted[3].length(); j++) {
						BoardCell bc = new BoardCell();
						bc.setContent(String.valueOf(splitted[3].charAt(j)));
						b.setCell(Integer.parseInt(splitted[1]),
								Integer.parseInt(splitted[2]) + j, bc);
					}
				}
				cw.setEntries(entries);
				cw.setBoard(b);
				crosswords.add(cw);
				if (bufferedReader != null)
					bufferedReader.close();
			}
		}

		return crosswords;
	}
}
