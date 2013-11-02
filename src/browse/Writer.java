package browse;

import java.io.IOException;
import java.util.LinkedList;

import board.Crossword;

public interface Writer {
	public void write(String path,LinkedList<Crossword> crosswordsList) throws IOException;
    public long getUniqueID();
}
