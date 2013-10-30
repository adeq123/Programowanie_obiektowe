package board;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import dictionary.CwDB;
import dictionary.CwEntry;
import dictionary.InteliCwDB;

public class Crossword {
//private
	public LinkedList<CwEntry> entries;
	public Board b;
	private InteliCwDB cwdb;
	
	public Crossword(){
		entries = new LinkedList<CwEntry>();
		b = new Board(20,20);
		cwdb = new InteliCwDB("cwdb.txt");
	}
	

	public Iterator<CwEntry> getROEntryIter() {
		return Collections.unmodifiableList(entries).iterator();
	}

	public Board getBoardCopy() {
		return b.clone();
	}

	public InteliCwDB getCwDB() {
		return cwdb;
	}

	public void setCwDB(InteliCwDB cwdb) {
		this.cwdb = cwdb;
	}

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

	public final void addCwEntry(CwEntry cwe, Strategy s) {
		entries.add(cwe);
		s.updateBoard(b, cwe);
	}

	public final void generate(Strategy s) {

		// Entry e = null; bobek sie pomysli?
		CwEntry e = null;
		// koniec pomylki
		while ((e = s.findEntry(this)) != null) {
			addCwEntry(e, s);
		}
	}

}
