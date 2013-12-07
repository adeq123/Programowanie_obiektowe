package browse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import board.Board;
import board.Crossword;
import board.EasytStrategy;
import board.HardStrategy;
import board.Strategy;
import dictionary.CwEntry;
import dictionary.CwEntry.Direction;

/**
 * 
 * @author krzysztof
 * 
 */
public class CwReader implements Reader {

	@Override
	public LinkedList<Crossword> getAllCws(String path) throws FileNotFoundException, IOException {
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
				FileReader fileReader = new FileReader(path + "/" + files[i]);
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				String strategy = bufferedReader.readLine();
				Strategy s;
				if (strategy.equals("Easy"))
					s = new EasytStrategy();
				else
					s = new HardStrategy();
				Crossword cw = new Crossword(temp, s);
				int height = Integer.parseInt(bufferedReader.readLine());
				int width = Integer.parseInt(bufferedReader.readLine());
				Board b = new Board(height, width);
				System.out.println(b.getHeight());
				System.out.println(b.getWidth());
				cw.setBoard(b);
				String line1, line2;
				LinkedList<CwEntry> entries = new LinkedList<CwEntry>();
				cw.setEntries(entries);
				while ((line1 = bufferedReader.readLine()) != null) {
					String[] splitted = line1.split(" ");
					line2 = bufferedReader.readLine();
					// entries.add(new CwEntry(splitted[3], line2,
					// Integer.parseInt(splitted[1]),
					// Integer.parseInt(splitted[2]),
					// Direction.valueOf(splitted[0])));
					cw.getEntries().add(new CwEntry(splitted[3], line2, Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Direction.valueOf(splitted[0])));

					System.out.println("A");
					for (int j = 0; j < splitted[3].length(); j++) {
						// BoardCell bc = new BoardCell();
						// bc.setContent(String.valueOf(splitted[3].charAt(j)));
						String dir = Direction.valueOf(splitted[0]).toString();
						System.out.println(dir);
						if (dir.equals("VERT") == true) {
							System.out.println("!" + Direction.valueOf(splitted[0]) + "!");
							System.out.println(splitted[3].length());
							System.out.println(Integer.parseInt(splitted[1]) + j);
							System.out.println(Integer.parseInt(splitted[2]));
							b.getCell(Integer.parseInt(splitted[1]) + j, Integer.parseInt(splitted[2])).setContent(String.valueOf(splitted[3].charAt(j)));
						}
						// b.setCell(Integer.parseInt(splitted[1]),
						// Integer.parseInt(splitted[2]) + j, bc);
						else {
							System.out.println(splitted[3].length());
							System.out.println(Integer.parseInt(splitted[1]));
							System.out.println(Integer.parseInt(splitted[2]) + j);
							b.getCell(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]) + j).setContent(String.valueOf(splitted[3].charAt(j)));
							// b.setCell(Integer.parseInt(splitted[1]) + j,
							// Integer.parseInt(splitted[2]), bc);
						}
					}
				}

				crosswords.add(cw);
				if (bufferedReader != null)
					bufferedReader.close();
			}
		}
		return crosswords;
	}
}
