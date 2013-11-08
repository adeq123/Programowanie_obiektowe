package board;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import dictionary.CwEntry;
import dictionary.InteliCwDB;

/**
 * 
 * @author krzysztof
 * 
 */
public class Crossword {
	public final long id; // id
	private LinkedList<CwEntry> entries; // list of CwEntries
	private Board b; // board

	/**
	 * Constructor
	 * 
	 * @param id
	 *            - id
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Crossword(long id) throws FileNotFoundException, IOException {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return entries (LinkedList<CwEntry>)
	 */
	public LinkedList<CwEntry> getEntries() {
		return entries;
	}

	/**
	 * Setter
	 * 
	 * @param entries
	 *            - LinkedList<CwEntry>
	 */
	public void setEntries(LinkedList<CwEntry> entries) {
		this.entries = entries;
	}

	/**
	 * Getter
	 * 
	 * @return board
	 */
	public Board getBoard() {
		return b;
	}

	/**
	 * Setter
	 * 
	 * @param b
	 *            - Board
	 */
	public void setBoard(Board b) {
		this.b = b;
	}

	private InteliCwDB cwdb; // InteliCwDB

	/**
	 * Constructor
	 * 
	 * @param height
	 *            - height
	 * @param width
	 *            - width
	 * @param filename
	 *            - file contained dictionary
	 */
	public Crossword(int height, int width, String filename) {
		entries = new LinkedList<CwEntry>();
		b = new Board(height, width);
		cwdb = new InteliCwDB(filename);
		id = 0;
	}

	/**
	 * Returns readonly iterator of list of CwEntries
	 * 
	 * @return iterator
	 */
	public Iterator<CwEntry> getROEntryIter() {
		return Collections.unmodifiableList(entries).iterator();
	}

	/**
	 * Copies board
	 * 
	 * @return new board
	 */
	public Board getBoardCopy() {
		return b.clone();
	}

	/**
	 * Getter
	 * 
	 * @return CwDB
	 */
	public InteliCwDB getCwDB() {
		return cwdb;
	}

	/**
	 * Setter
	 * 
	 * @param cwdb
	 *            - CwDB
	 */
	public void setCwDB(InteliCwDB cwdb) {
		this.cwdb = cwdb;
	}

	/**
	 * Checks if crossword contains word
	 * 
	 * @param word
	 *            - word
	 * @return boolean value (true - word found, false - not found)
	 */
	public boolean contains(String word) {
		CwEntry temp = null;
		for (ListIterator<CwEntry> iter = entries.listIterator(); iter
				.hasNext();) {
			temp = iter.next();
			if (temp.getWord().equals(word))
				return true;
		}
		return false;
	}

	/**
	 * Adds CwEntry to crossword
	 * 
	 * @param cwe
	 *            - CwEntry
	 * @param s
	 *            - Strategy
	 */
	public final void addCwEntry(CwEntry cwe, Strategy s) {
		entries.add(cwe);
		s.updateBoard(b, cwe);
	}

	/**
	 * Generate content of crossword
	 * 
	 * @param s
	 *            - Strategy
	 */
	public final void generate(Strategy s) {
		CwEntry e = null;
		while ((e = s.findEntry(this)) != null) {
			addCwEntry(e, s);
		}
	}
}