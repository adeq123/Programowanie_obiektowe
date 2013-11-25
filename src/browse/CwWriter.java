package browse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import board.Crossword;
import dictionary.CwEntry;

/**
 * 
 * @author krzysztof
 * 
 */
public class CwWriter implements Writer {

	@Override
	public void write(String path, LinkedList<Crossword> crosswordsList)
			throws IOException {
		File file = new File(path);
		Iterator<Crossword> it = crosswordsList.iterator();
		Crossword crossword;
		while (it.hasNext()) {
			crossword = it.next();
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile() + "/"
					+ Long.toString(getUniqueID()));
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			try {
				bufferedWriter.write(Integer.toString(crossword.getBoard()
						.getHeight()));
				bufferedWriter.newLine();
				bufferedWriter.write(Integer.toString(crossword.getBoard()
						.getWidth()));
				bufferedWriter.newLine();
				Iterator<CwEntry> iter = crossword.getEntries().iterator();
				CwEntry c;
				while (iter.hasNext() == true) {
					c = iter.next();
					bufferedWriter.write(c.toString());
					bufferedWriter.newLine();
				}
			} finally {
				if (bufferedWriter != null)
					bufferedWriter.close();
			}
		}
	}

	@Override
	public long getUniqueID() {
		return new Date().getTime();
	}

}
