package board;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import dictionary.CwEntry;
import dictionary.CwEntry.Direction;
import dictionary.Entry;
import exceptions.noPossibilityToGenerateCrosswordException;

/**
 * 
 * @author krzysztof
 * 
 */
public class HardStrategy extends Strategy {

	/**
	 * Returns CwEntry beeing new main word
	 * 
	 * @param cw
	 *            - Crossword
	 * @return CwEntry
	 * @throws noPossibilityToGenerateCrosswordException
	 */
	public CwEntry getFirstWord(Crossword cw)
			throws noPossibilityToGenerateCrosswordException {
		LinkedList<Entry> propriateLengthWords = new LinkedList<Entry>();
		Random rand = new Random();

		Entry e;
		Iterator<Entry> it = cw.getCwDB().getDict().iterator();
		while (it.hasNext()) {
			e = it.next();
			if (e.getWord().length() <= cw.getBoard().getHeight()
					|| e.getWord().length() <= cw.getBoard().getWidth()) {
				propriateLengthWords.add(e);
			}
		}

		if (propriateLengthWords.size() == 0)
			throw new noPossibilityToGenerateCrosswordException();
		Entry finalEntry = propriateLengthWords.get(rand
				.nextInt(propriateLengthWords.size()));
		if (cw.getBoard().getHeight() > cw.getBoard().getWidth())
			return new CwEntry(finalEntry.getWord(), finalEntry.getClue(), 0,
					0, Direction.HORIZ);
		else
			return new CwEntry(finalEntry.getWord(), finalEntry.getClue(), 0,
					0, Direction.VERT);
	}

	/**
	 * Finds new CwEntry that could be added to crossword
	 * 
	 * @param cw
	 *            - Crossword
	 * @return new CwEntry
	 * @throws noPossibilityToGenerateCrosswordException
	 */
	@Override
	public CwEntry findEntry(Crossword cw)
			throws noPossibilityToGenerateCrosswordException {
		Random rand = new Random();
		Entry entry = null;
		CwEntry cwentry = null;

		int amountOfWords = cw.getEntries().size();
		// jako pierwsze wygeneruj słowo losowe
		if (cw.getEntries().isEmpty() == true) {
			cwentry = getFirstWord(cw);
		} else {
			do {
				LinkedList<BoardCell> newBoardCellList = new LinkedList<BoardCell>();
				// dla kazdej komorki
				for (int i = 0; i < cw.getBoard().getHeight(); i++) {
					for (int j = 0; j < cw.getBoard().getHeight(); j++) {
						// jesli komorka nie jest pusta
						if (cw.getBoard().getBoard()[i][j].getContent() != null) {
							newBoardCellList
									.add(cw.getBoard().getBoard()[i][j]);
						}
					}
				}

				// slownik, usun z niego wyrazy ktore juz sa na krzyzowce!!!!!!
				LinkedList<Entry> newDictList = new LinkedList<Entry>(
						cw.getEntries());
				Iterator<BoardCell> iter = newBoardCellList.iterator();
				// dopoki newDictList ma slowa...
				while (iter.hasNext() == true) {
						
					LinkedList<String> newPatternList = new LinkedList<String>();
					Iterator<BoardCell> it = newBoardCellList.iterator();
					while (it.hasNext() == true) {

					}
				}

				String currentPattern = cw.getBoard().createPattern(
						amountOfWords - 1, 0, amountOfWords - 1,
						rand.nextInt(cw.getBoard().getWidth()) - 1);
				entry = cw.getCwDB().getRandom(currentPattern);
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
				// jezeli dodatkowo przecina jakies komorki to je zmien!!!!!!
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