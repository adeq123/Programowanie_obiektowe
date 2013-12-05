package board;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import board.BoardCell.Position;

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

		Map<Character, Integer> firstLettersInDictionaryWithPropriateLength = new HashMap<Character, Integer>();
		LinkedList<Entry> sameLengthWords = new LinkedList<Entry>();
		Random rand = new Random();

		Entry e = null;
		Iterator<Entry> it = cw.getCwDB().getDict().iterator();
		while (it.hasNext()) {
			e = it.next();
			if (cw.getBoard().getHeight() >= cw.getBoard().getWidth()) {
				if ((firstLettersInDictionaryWithPropriateLength.containsKey(e
						.getWord().charAt(0)) == false)
						&& (e.getWord().length() <= cw.getBoard().getWidth()))
					firstLettersInDictionaryWithPropriateLength.put(e.getWord()
							.charAt(0), 1);
				else if (e.getWord().length() <= cw.getBoard().getWidth())
					firstLettersInDictionaryWithPropriateLength.put(e.getWord()
							.charAt(0),
							firstLettersInDictionaryWithPropriateLength.get(e
									.getWord().charAt(0)) + 1);
				if (e.getWord().length() <= cw.getBoard().getHeight()) {
					sameLengthWords.add(new Entry(e.getWord(), e.getClue()));
				}
			} else {
				if ((firstLettersInDictionaryWithPropriateLength.containsKey(e
						.getWord().charAt(0)) == false)
						&& (e.getWord().length() <= cw.getBoard().getHeight()))
					firstLettersInDictionaryWithPropriateLength.put(e.getWord()
							.charAt(0), 1);
				else if (e.getWord().length() <= cw.getBoard().getHeight())
					firstLettersInDictionaryWithPropriateLength.put(e.getWord()
							.charAt(0),
							firstLettersInDictionaryWithPropriateLength.get(e
									.getWord().charAt(0)) + 1);
				if (e.getWord().length() <= cw.getBoard().getWidth()) {
					sameLengthWords.add(new Entry(e.getWord(), e.getClue()));
				}
			}
		}

		Iterator<Entry> iter = sameLengthWords.iterator();
		LinkedList<Entry> finalListOfWords = new LinkedList<Entry>(
				sameLengthWords);
		while (iter.hasNext() == true) {
			e = iter.next();
			Map<Character, Integer> lettersInWord = new HashMap<Character, Integer>();
			for (int j = 0; j < e.getWord().length(); j++) {
				if (lettersInWord.containsKey(e.getWord().charAt(j)) == false) {

					lettersInWord.put(e.getWord().charAt(j), 1);
				} else {

					lettersInWord.put(e.getWord().charAt(j),
							lettersInWord.get(e.getWord().charAt(j)) + 1);
				}
			}
			for (Map.Entry<Character, Integer> entry : lettersInWord.entrySet()) {
				if (firstLettersInDictionaryWithPropriateLength
						.containsKey(entry.getKey()) == true) {
					if (entry.getValue() >= firstLettersInDictionaryWithPropriateLength
							.get(entry.getKey())) {
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
		Entry finalEntry = finalListOfWords.get(rand.nextInt(finalListOfWords
				.size()));
		// return new CwEntry(finalEntry.getWord(), finalEntry.getClue(), 0, 0,
		// Direction.HORIZ);

		if (cw.getBoard().getHeight() >= cw.getBoard().getWidth())
			return new CwEntry(finalEntry.getWord(), finalEntry.getClue(), 0,
					0, Direction.HORIZ);
		else
			return new CwEntry(finalEntry.getWord(), finalEntry.getClue(), 0,
					0, Direction.VERT);
		/*
		 * LinkedList<Entry> propriateLengthWords = new LinkedList<Entry>();
		 * Random rand = new Random();
		 * 
		 * Entry e; Iterator<Entry> it = cw.getCwDB().getDict().iterator();
		 * while (it.hasNext()) { e = it.next(); if (e.getWord().length() <=
		 * cw.getBoard().getHeight() && e.getWord().length() <=
		 * cw.getBoard().getWidth()) { propriateLengthWords.add(e); } }
		 * 
		 * if (propriateLengthWords.size() == 0) throw new
		 * noPossibilityToGenerateCrosswordException(); Entry finalEntry =
		 * propriateLengthWords.get(rand .nextInt(propriateLengthWords.size()));
		 * // if (cw.getBoard().getHeight() > cw.getBoard().getWidth()) if
		 * (rand.nextInt() % 2 == 0) return new CwEntry(finalEntry.getWord(),
		 * finalEntry.getClue(), 0, 0, Direction.HORIZ); else return new
		 * CwEntry(finalEntry.getWord(), finalEntry.getClue(), 0, 0,
		 * Direction.VERT);
		 */
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
			System.out.println(cwentry);
			//return null;
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
		if (b.isEmpty() == true)
			setFirstAbilities(b);

		char[] contentOfString = e.getWord().toCharArray();
		if (e.getDir() == Direction.VERT) {
			for (int i = 0; i < e.getWord().length(); i++) {
				// jezeli dodatkowo przecina jakies komorki to je zmien!!!!!!
				BoardCell boardcell = new BoardCell();
				boardcell.setContent(new String(contentOfString, i, 1));
				boardcell.setAbility(CwEntry.Direction.VERT,
						BoardCell.Position.START, false);
				boardcell.setAbility(CwEntry.Direction.VERT,
						BoardCell.Position.END, false);
				boardcell.setAbility(CwEntry.Direction.VERT,
						BoardCell.Position.INNER, false);
				if (e.getY() + 1 < b.getHeight())
					boardcell.setAbility(CwEntry.Direction.HORIZ,
							BoardCell.Position.START, true);
				else
					boardcell.setAbility(CwEntry.Direction.HORIZ,
							BoardCell.Position.START, false);
				if (e.getY() - 1 > 0)
					boardcell.setAbility(CwEntry.Direction.HORIZ,
							BoardCell.Position.END, true);
				else
					boardcell.setAbility(CwEntry.Direction.HORIZ,
							BoardCell.Position.END, false);
				if ((e.getY() + 1 < b.getHeight()) && (e.getY() - 1 > 0))
					boardcell.setAbility(CwEntry.Direction.HORIZ,
							BoardCell.Position.INNER, true);
				else
					boardcell.setAbility(CwEntry.Direction.HORIZ,
							BoardCell.Position.INNER, false);
				b.setCell(e.getX(), e.getY() + i, boardcell);
			}
		} else {
			for (int i = 0; i < e.getWord().length(); i++) {
				BoardCell boardcell = new BoardCell();
				boardcell.setContent(new String(contentOfString, i, 1));
				boardcell.setAbility(CwEntry.Direction.HORIZ,
						BoardCell.Position.START, false);
				boardcell.setAbility(CwEntry.Direction.HORIZ,
						BoardCell.Position.END, false);
				boardcell.setAbility(CwEntry.Direction.HORIZ,
						BoardCell.Position.INNER, false);
				if (e.getX() + 1 < b.getWidth())
					boardcell.setAbility(CwEntry.Direction.VERT,
							BoardCell.Position.START, true);
				else
					boardcell.setAbility(CwEntry.Direction.VERT,
							BoardCell.Position.START, false);
				if (e.getX() - 1 > 0)
					boardcell.setAbility(CwEntry.Direction.VERT,
							BoardCell.Position.END, true);
				else
					boardcell.setAbility(CwEntry.Direction.VERT,
							BoardCell.Position.END, false);
				if ((e.getX() + 1 < b.getWidth()) && (e.getX() - 1 > 0))
					boardcell.setAbility(CwEntry.Direction.VERT,
							BoardCell.Position.INNER, true);
				else
					boardcell.setAbility(CwEntry.Direction.VERT,
							BoardCell.Position.INNER, false);
				b.setCell(e.getX() + i, e.getY(), boardcell);
			}
		}
	}

	/**
	 * Sets abilities of cells in empty board
	 * 
	 * @param b
	 *            - board
	 */
	public void setFirstAbilities(Board b) {
		for (int i = 0; i < b.getHeight(); i++)
			for (int j = 0; j < b.getWidth(); j++) {
				b.getCell(i, j).setAbility(Direction.HORIZ, Position.START,
						true);
				b.getCell(i, j)
						.setAbility(Direction.VERT, Position.START, true);
				b.getCell(i, j).setAbility(Direction.HORIZ, Position.INNER,
						true);
				b.getCell(i, j)
						.setAbility(Direction.VERT, Position.INNER, true);
				b.getCell(i, j).setAbility(Direction.HORIZ, Position.END, true);
				b.getCell(i, j).setAbility(Direction.VERT, Position.END, true);

				if (j == b.getWidth() - 1) {
					b.getCell(i, j).setAbility(Direction.HORIZ, Position.START,
							false);
					b.getCell(i, j).setAbility(Direction.HORIZ, Position.INNER,
							false);
				}
				if (i == b.getHeight() - 1) {
					b.getCell(i, j).setAbility(Direction.VERT, Position.START,
							false);
					b.getCell(i, j).setAbility(Direction.VERT, Position.INNER,
							false);
				}
				if (j == 0) {
					b.getCell(i, j).setAbility(Direction.HORIZ, Position.INNER,
							false);
					b.getCell(i, j).setAbility(Direction.HORIZ, Position.END,
							false);
				}
				if (i == 0) {
					b.getCell(i, j).setAbility(Direction.VERT, Position.INNER,
							false);
					b.getCell(i, j).setAbility(Direction.VERT, Position.END,
							false);
				}
			}
		for (int i = 0; i < b.getHeight(); i++) {
			for (int j = 0; j < b.getWidth(); j++) {
				if (b.getCell(i, j).abilities[0][0] == false)
					System.out.print("F");
				else
					System.out.print("T");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < b.getHeight(); i++) {
			for (int j = 0; j < b.getWidth(); j++) {
				if (b.getCell(i, j).abilities[1][0] == false)
					System.out.print("F");
				else
					System.out.print("T");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < b.getHeight(); i++) {
			for (int j = 0; j < b.getWidth(); j++) {
				if (b.getCell(i, j).abilities[0][1] == false)
					System.out.print("F");
				else
					System.out.print("T");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < b.getHeight(); i++) {
			for (int j = 0; j < b.getWidth(); j++) {
				if (b.getCell(i, j).abilities[1][1] == false)
					System.out.print("F");
				else
					System.out.print("T");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < b.getHeight(); i++) {
			for (int j = 0; j < b.getWidth(); j++) {
				if (b.getCell(i, j).abilities[0][2] == false)
					System.out.print("F");
				else
					System.out.print("T");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < b.getHeight(); i++) {
			for (int j = 0; j < b.getWidth(); j++) {
				if (b.getCell(i, j).abilities[1][2] == false)
					System.out.print("F");
				else
					System.out.print("T");
			}
			System.out.println();
		}
		System.out.println();

	}

}
