package crossword.browse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import crossword.board.Board;
import crossword.board.Crossword;
import crossword.dictionary.CwEntry;
import crossword.dictionary.CwEntry.Direction;
import crossword.exceptions.noCrosswordFoundToLoadException;
import crossword.strategy.EasyStrategy;
import crossword.strategy.HardStrategy;
import crossword.strategy.Strategy;

/**
 * 
 * @author krzysztof
 * 
 */
public class CwReader implements Reader {

	@Override
	public LinkedList<Crossword> getAllCws(String path) throws FileNotFoundException, IOException, noCrosswordFoundToLoadException {

		LinkedList<Crossword> crosswords = new LinkedList<Crossword>();
		File file = new File(path);
		String[] files = file.list();
		for (int i = 0; i < files.length; i++) {
			long temp;
			try {
				temp = Long.parseLong(files[i]);
			} catch (NumberFormatException e) {
				continue;
			}
			FileReader fileReader = new FileReader(path + "/" + files[i]);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String strategy = bufferedReader.readLine();
			Strategy s;
			if (strategy.equals("Easy"))
				s = new EasyStrategy();
			else
				s = new HardStrategy();
			Crossword cw = new Crossword(temp, s);
			int height = Integer.parseInt(bufferedReader.readLine());
			int width = Integer.parseInt(bufferedReader.readLine());
			Board b = new Board(height, width);
			cw.setBoard(b);
			String line1, line2;
			LinkedList<CwEntry> entries = new LinkedList<CwEntry>();
			cw.setEntries(entries);
			while ((line1 = bufferedReader.readLine()) != null) {
				String[] splitted = line1.split(" ");
				line2 = bufferedReader.readLine();
				cw.getEntries().add(new CwEntry(splitted[3], line2, Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Direction.valueOf(splitted[0])));
				for (int j = 0; j < splitted[3].length(); j++) {
					String dir = Direction.valueOf(splitted[0]).toString();
					if (dir.equals("VERT") == true) {
						b.getCell(Integer.parseInt(splitted[1]) + j, Integer.parseInt(splitted[2])).setContent(String.valueOf(splitted[3].charAt(j)));
					} else {
						b.getCell(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]) + j).setContent(String.valueOf(splitted[3].charAt(j)));
					}
				}
			}
			crosswords.add(cw);
			if (bufferedReader != null)
				bufferedReader.close();
		}
		if (crosswords.size() == 0)
			throw new noCrosswordFoundToLoadException("No crossword was found to be load in this directory");
		return crosswords;
	}
}