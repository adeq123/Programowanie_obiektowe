package board;

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
	 * Finds new CwEntry that could be added to crossword
	 * 
	 * @param cw
	 *            - Crossword
	 * @return new CwEntry
	 */
	@Override
	public CwEntry findEntry(Crossword cw) {
		Random rand = new Random();
		Entry entry;
		CwEntry cwentry = null;
		if (cw.getEntries().isEmpty() == true) {
			do {
				entry = cw.getCwDB().getRandom();
			} while (entry.getWord().contains("y") == true);
			cwentry = new CwEntry(entry.getWord(), entry.getClue(), 0, 0,
					Direction.HORIZ);
		} else {
			int amountOfWords = cw.getEntries().size();
			String currentPattern = cw.getBoard().createPattern(
					amountOfWords - 1, 0, amountOfWords - 1,
					rand.nextInt(5) + 3);
			if (currentPattern != null) {
				do {
					entry = cw.getCwDB().getRandom(currentPattern);
				} while (cw.contains(entry.getWord()) == true);
				cwentry = new CwEntry(entry.getWord(), entry.getClue(), 0,
						amountOfWords, Direction.VERT);
			}
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
				boardcell.setAbility(boardcell.VERT, boardcell.START, false);
				boardcell.setAbility(boardcell.VERT, boardcell.END, false);
				boardcell.setAbility(boardcell.VERT, boardcell.INNER, false);
				if (e.getY() + 1 < b.getHeight())
					boardcell
							.setAbility(boardcell.HORIZ, boardcell.START, true);
				else
					boardcell.setAbility(boardcell.HORIZ, boardcell.START,
							false);
				if (e.getY() - 1 > 0)
					boardcell.setAbility(boardcell.HORIZ, boardcell.END, true);
				else
					boardcell.setAbility(boardcell.HORIZ, boardcell.END, false);
				if ((e.getY() + 1 < b.getHeight()) && (e.getY() - 1 > 0))
					boardcell
							.setAbility(boardcell.HORIZ, boardcell.INNER, true);
				else
					boardcell.setAbility(boardcell.HORIZ, boardcell.INNER,
							false);

				b.setCell(e.getX(), e.getY() + i, boardcell);
			}
		} else {
			for (int i = 0; i < e.getWord().length(); i++) {
				BoardCell boardcell = new BoardCell();
				boardcell.setContent(new String(contentOfString, i, 1));
				boardcell.setAbility(boardcell.HORIZ, boardcell.START, false);
				boardcell.setAbility(boardcell.HORIZ, boardcell.END, false);
				boardcell.setAbility(boardcell.HORIZ, boardcell.INNER, false);
				if (e.getX() + 1 < b.getWidth())
					boardcell.setAbility(boardcell.VERT, boardcell.START, true);
				else
					boardcell
							.setAbility(boardcell.VERT, boardcell.START, false);
				if (e.getX() - 1 > 0)
					boardcell.setAbility(boardcell.VERT, boardcell.END, true);
				else
					boardcell.setAbility(boardcell.VERT, boardcell.END, false);
				if ((e.getX() + 1 < b.getWidth()) && (e.getX() - 1 > 0))
					boardcell.setAbility(boardcell.VERT, boardcell.INNER, true);
				else
					boardcell
							.setAbility(boardcell.VERT, boardcell.INNER, false);
				b.setCell(e.getX() + i, e.getY(), boardcell);
			}
		}
	}
}
