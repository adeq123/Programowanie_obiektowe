package browse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import board.Crossword;
import dictionary.CwEntry;

public class CwWriter implements Writer {

	@Override
	public void write(Crossword crossword) throws IOException {
		// TODO Auto-generated method stub
		FileWriter fileWriter = new FileWriter(Long.toString(getUniqueID()));
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		try {
			Iterator<CwEntry> iter = crossword.getEntries().iterator();
			CwEntry c;
			while (iter.hasNext() == true) {
				c = iter.next();
				bufferedWriter.write(c.getWord());
				System.out.println(c.getWord());
				bufferedWriter.newLine();
				bufferedWriter.write(c.getClue());
				bufferedWriter.newLine();
			}
			bufferedWriter.write(Integer.toString(crossword.getBoard().getHeight()));
			bufferedWriter.newLine();
			bufferedWriter.write(Integer.toString(crossword.getBoard().getWidth()));
			bufferedWriter.newLine();
			for (int i = 0; i < crossword.getBoard().getHeight(); i++) {
				for (int j = 0; j < crossword.getBoard().getWidth(); j++) {
					if (crossword.getBoard().getCell(i, j).content != null)
						bufferedWriter
								.write(crossword.getBoard().getCell(i, j).content);
					else
						bufferedWriter.write("null");
					bufferedWriter.newLine();
				}
			}
		} finally {
			if (bufferedWriter != null)
				bufferedWriter.close();
		}
	}

	@Override
	public long getUniqueID() {
		// TODO Auto-generated method stub
		return new Date().getTime();
	}

}
