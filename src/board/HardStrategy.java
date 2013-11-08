package board;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
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
	public CwEntry getMainWord(Crossword cw) throws noPossibilityToGenerateCrosswordException {
		Map<Character, Integer> firstLettersInDictionary = new HashMap<Character, Integer>();
		LinkedList<Entry> sameLengthWords = new LinkedList<Entry>();

		Entry e;
		Iterator<Entry> it = cw.getCwDB().getDict().iterator();
		while (it.hasNext()) {
			e = it.next();
			if (firstLettersInDictionary.containsKey(e.getWord().charAt(0)) == false)
				firstLettersInDictionary.put(e.getWord().charAt(0), 1);
			else
				firstLettersInDictionary
						.put(e.getWord().charAt(0), firstLettersInDictionary
								.get(e.getWord().charAt(0)) + 1);
			if (e.getWord().length() == cw.getBoard().getHeight()) {
				sameLengthWords.add(e);
			}
		}

		Iterator<Entry> iter = sameLengthWords.iterator();
		LinkedList<Entry> finalListOfWords = new LinkedList<Entry>(
				sameLengthWords);
		while (iter.hasNext() == true) {
			e = iter.next();
			Map<Character, Integer> lettersInWord = new HashMap<Character, Integer>();
			for (int j = 0; j < e.getWord().length(); j++) {
				if (lettersInWord.containsKey(e.getWord().charAt(j)) == false)
					lettersInWord.put(e.getWord().charAt(j), 1);
				else
					lettersInWord.put(e.getWord().charAt(j),
							lettersInWord.get(e.getWord().charAt(j)) + 1);
			}

			for (Map.Entry<Character, Integer> entry : lettersInWord.entrySet()) {
				if (firstLettersInDictionary.containsKey(entry.getKey()) == true) {
					if (entry.getValue() >= firstLettersInDictionary.get(entry
							.getKey())) {
						finalListOfWords.remove(e);
						break;
					}
				} else {
					finalListOfWords.remove(e);
					break;
				}
			}

		}
		if (finalListOfWords.size() == 0)
			throw new noPossibilityToGenerateCrosswordException();
		Random rand = new Random();
		Entry finalEntry = finalListOfWords.get(rand.nextInt(finalListOfWords
				.size()));
		return new CwEntry(finalEntry.getWord(), finalEntry.getClue(), 0, 0,
				Direction.HORIZ);
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
	public CwEntry findEntry(Crossword cw) throws noPossibilityToGenerateCrosswordException {
		Random rand = new Random();
		Entry entry = null;
		CwEntry cwentry = null;

		int amountOfWords = cw.getEntries().size();
		//zakoncz generowanie gdy znajdziesz juz wszystkie słowa
		if (cw.getBoard().getHeight() == (amountOfWords - 1))
			return null;
		// jako pierwsze wygeneruj słowo GŁÓWNE
		if (cw.getEntries().isEmpty() == true) {
			cwentry = getMainWord(cw);
		} else {
			do {
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