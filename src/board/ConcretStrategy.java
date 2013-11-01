package board;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import dictionary.CwEntry;
import dictionary.CwEntry.Direction;
import dictionary.Entry;

/**
 * 
 * @author krzysztof
 * 
 */
public class ConcretStrategy extends Strategy {

	/**
	 * Returns CwEntry beeing new main word
	 * 
	 * @param cw
	 *            - Crossword
	 * @return CwEntry
	 */
	public CwEntry getMainWord(Crossword cw) {
		Map<Character, Integer> firstLetters = new HashMap<Character, Integer>();
		LinkedList<Entry> lol = new LinkedList<Entry>();
		Iterator<Entry> iter = cw.getCwDB().getDict().iterator();

		System.out.println("A");
		Entry e;
		while (iter.hasNext()) {
			e = iter.next();
			if (e.getWord().length() == cw.getBoard().getHeight()) {
				lol.add(e);
			}
			if (firstLetters.containsKey(e.getWord().charAt(0)) == false)
				firstLetters.put(e.getWord().charAt(0), 1);
			else
				firstLetters.put(e.getWord().charAt(0),
						firstLetters.get(e.getWord().charAt(0)) + 1);
		}

		Entry d;
		Random rand = new Random();
		Iterator<Entry> it = lol.iterator();
		for (int k = 0; k < lol.size(); k++) {
			System.out.println("S");
			d = lol.get(k);
			Map<Character, Integer> cos = new HashMap<Character, Integer>();
			for (int i = 0; i < d.getWord().length(); i++) {
				if (cos.containsKey(d.getWord().charAt(i)) == false)
					cos.put(d.getWord().charAt(i), 1);
				else
					cos.put(d.getWord().charAt(i),
							cos.get(d.getWord().charAt(i)) + 1);
			}

			for (Map.Entry<Character, Integer> entry : cos.entrySet()) {
				System.out.println(entry.getValue());
				System.out.println(firstLetters.get(entry.getKey()));
				if (entry.getValue() >= firstLetters.get(entry.getKey())) {
					System.out.println("NIE DZIALA!");
					lol.remove(d);
				}
			}
			System.out.println(lol.getFirst().getWord());
			if (lol.size() == 0)
				System.out.println("PUSTO!"); // wyjatek - nie ma opcji zrobic
												// krzyzowki z podana dlugoscia
												// hasla glownego

		}
		Entry ss = lol.get(rand.nextInt(lol.size()));
		return new CwEntry(ss.getWord(), ss.getClue(), 0, 0, Direction.HORIZ);
		// Entry entry = cw.getCwDB().getRandom(cw.getBoard().getHeight());
		// return new CwEntry(entry.getWord(), entry.getClue(), 0, 0,
		// Direction.HORIZ);
	}

	/**
	 * Finds new CwEntry that could be added to crossword
	 * 
	 * @param cw
	 *            - Crossword
	 * @return new CwEntry
	 */
	@Override
	public CwEntry findEntry(Crossword cw) {
		Random rand = new Random();
		Entry entry = null;
		CwEntry cwentry = null;

		int amountOfWords = cw.getEntries().size();
		if (cw.getBoard().getHeight() == (amountOfWords - 1))
			return null;

		if (cw.getEntries().isEmpty() == true) {
			cwentry = getMainWord(cw);
		} else {
			do {
				String currentPattern = cw.getBoard().createPattern(
						amountOfWords - 1, 0, amountOfWords - 1,
						rand.nextInt(11) + 2);
				System.out.println(currentPattern);
				entry = cw.getCwDB().getRandom(currentPattern);
				System.out.print("Q");
			} while ((entry == null || cw.contains(entry.getWord()) == true));
			cwentry = new CwEntry(entry.getWord(), entry.getClue(),
					amountOfWords - 1, 0, Direction.VERT);
		}
		return cwentry;
	}

	/**
	 * Updates Board
	 * 
	 * @param b
	 *            - Board
	 * @param e
	 *            - CwEntry
	 */
	@Override
	public void updateBoard(Board b, CwEntry e) {
		char[] contentOfString = e.getWord().toCharArray();
		if (e.getDir() == Direction.VERT) {
			for (int i = 0; i < e.getWord().length(); i++) {
				BoardCell boardcell = new BoardCell();
				boardcell.setContent(new String(contentOfString, i, 1));
				boardcell.setAbility(BoardCell.Direction.VERT,
						BoardCell.Position.START, false);
				boardcell.setAbility(BoardCell.Direction.VERT,
						BoardCell.Position.END, false);
				boardcell.setAbility(BoardCell.Direction.VERT,
						BoardCell.Position.INNER, false);
				if (e.getY() + 1 < b.getHeight())
					boardcell.setAbility(BoardCell.Direction.HORIZ,
							BoardCell.Position.START, true);
				else
					boardcell.setAbility(BoardCell.Direction.HORIZ,
							BoardCell.Position.START, false);
				if (e.getY() - 1 > 0)
					boardcell.setAbility(BoardCell.Direction.HORIZ,
							BoardCell.Position.END, true);
				else
					boardcell.setAbility(BoardCell.Direction.HORIZ,
							BoardCell.Position.END, false);
				if ((e.getY() + 1 < b.getHeight()) && (e.getY() - 1 > 0))
					boardcell.setAbility(BoardCell.Direction.HORIZ,
							BoardCell.Position.INNER, true);
				else
					boardcell.setAbility(BoardCell.Direction.HORIZ,
							BoardCell.Position.INNER, false);
				b.setCell(e.getX(), e.getY() + i, boardcell);
			}
		} else {
			for (int i = 0; i < e.getWord().length(); i++) {
				BoardCell boardcell = new BoardCell();
				boardcell.setContent(new String(contentOfString, i, 1));
				boardcell.setAbility(BoardCell.Direction.HORIZ,
						BoardCell.Position.START, false);
				boardcell.setAbility(BoardCell.Direction.HORIZ,
						BoardCell.Position.END, false);
				boardcell.setAbility(BoardCell.Direction.HORIZ,
						BoardCell.Position.INNER, false);
				if (e.getX() + 1 < b.getWidth())
					boardcell.setAbility(BoardCell.Direction.VERT,
							BoardCell.Position.START, true);
				else
					boardcell.setAbility(BoardCell.Direction.VERT,
							BoardCell.Position.START, false);
				if (e.getX() - 1 > 0)
					boardcell.setAbility(BoardCell.Direction.VERT,
							BoardCell.Position.END, true);
				else
					boardcell.setAbility(BoardCell.Direction.VERT,
							BoardCell.Position.END, false);
				if ((e.getX() + 1 < b.getWidth()) && (e.getX() - 1 > 0))
					boardcell.setAbility(BoardCell.Direction.VERT,
							BoardCell.Position.INNER, true);
				else
					boardcell.setAbility(BoardCell.Direction.VERT,
							BoardCell.Position.INNER, false);
				b.setCell(e.getX() + i, e.getY(), boardcell);
			}
		}
	}
}
