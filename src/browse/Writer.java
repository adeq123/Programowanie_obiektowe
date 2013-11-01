package browse;

import java.io.IOException;

import board.Crossword;

public interface Writer {
	public void write(Crossword crossword) throws IOException;
    public long getUniqueID();
}
