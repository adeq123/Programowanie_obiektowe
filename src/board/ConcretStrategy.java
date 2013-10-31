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
		Entry entry = null;
		CwEntry cwentry = null;
		if (cw.getEntries().isEmpty() == true) {
			do {
				entry = cw.getCwDB().getRandom();
			} while ((entry.getWord().contains("Ą") == true)
					|| (entry.getWord().contains("Ć") == true)
					|| (entry.getWord().contains("Ę") == true)
					|| (entry.getWord().contains("Ń") == true)
					|| (entry.getWord().contains("Ó") == true)
					|| (entry.getWord().contains("V") == true)
					|| (entry.getWord().contains("Y") == true)
					|| (entry.getWord().contains("X") == true)
					|| (entry.getWord().contains("Ź") == true));
			cwentry = new CwEntry(entry.getWord(), entry.getClue(), 0, 0,
					Direction.HORIZ);
		} else {
			int amountOfWords = cw.getEntries().size();

			do {
				String currentPattern = cw.getBoard().createPattern(
						amountOfWords - 1, 0, amountOfWords - 1,
						rand.nextInt(11) + 2);
				if (currentPattern != null) {
					do {
						entry = cw.getCwDB().getRandom(currentPattern);
						if (entry == null)
							break;
					} while (cw.contains(entry.getWord()) == true);
				} else
					return null;
			} while (entry == null);
			cwentry = new CwEntry(entry.getWord(), entry.getClue(), 0,
					amountOfWords, Direction.VERT);
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
