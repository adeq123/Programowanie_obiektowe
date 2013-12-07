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
	 * Returns CwEntry being new main word
	 * 
	 * @param cw
	 *            - Crossword
	 * @return CwEntry
	 * @throws noPossibilityToGenerateCrosswordException
	 */
	public CwEntry getFirstWord(Crossword cw) throws noPossibilityToGenerateCrosswordException {

		Map<Character, Integer> firstLettersInDictionaryWithPropriateLength = new HashMap<Character, Integer>();
		LinkedList<Entry> sameLengthWords = new LinkedList<Entry>();
		Random rand = new Random();

		Entry e = null;
		Iterator<Entry> it = cw.getCwDB().getDict().iterator();
		while (it.hasNext()) {
			e = it.next();
			if (cw.getBoard().getHeight() >= cw.getBoard().getWidth()) {
				if ((firstLettersInDictionaryWithPropriateLength.containsKey(e.getWord().charAt(0)) == false) && (e.getWord().length() <= cw.getBoard().getWidth()))
					firstLettersInDictionaryWithPropriateLength.put(e.getWord().charAt(0), 1);
				else if (e.getWord().length() <= cw.getBoard().getWidth())
					firstLettersInDictionaryWithPropriateLength.put(e.getWord().charAt(0), firstLettersInDictionaryWithPropriateLength.get(e.getWord().charAt(0)) + 1);
				if (e.getWord().length() <= cw.getBoard().getHeight()) {
					sameLengthWords.add(new Entry(e.getWord(), e.getClue()));
				}
			} else {
				if ((firstLettersInDictionaryWithPropriateLength.containsKey(e.getWord().charAt(0)) == false) && (e.getWord().length() <= cw.getBoard().getHeight()))
					firstLettersInDictionaryWithPropriateLength.put(e.getWord().charAt(0), 1);
				else if (e.getWord().length() <= cw.getBoard().getHeight())
					firstLettersInDictionaryWithPropriateLength.put(e.getWord().charAt(0), firstLettersInDictionaryWithPropriateLength.get(e.getWord().charAt(0)) + 1);
				if (e.getWord().length() <= cw.getBoard().getWidth()) {
					sameLengthWords.add(new Entry(e.getWord(), e.getClue()));
				}
			}
		}

		Iterator<Entry> iter = sameLengthWords.iterator();
		LinkedList<Entry> finalListOfWords = new LinkedList<Entry>(sameLengthWords);
		while (iter.hasNext() == true) {
			e = iter.next();
			Map<Character, Integer> lettersInWord = new HashMap<Character, Integer>();
			for (int j = 0; j < e.getWord().length(); j++) {
				if (lettersInWord.containsKey(e.getWord().charAt(j)) == false) {

					lettersInWord.put(e.getWord().charAt(j), 1);
				} else {

					lettersInWord.put(e.getWord().charAt(j), lettersInWord.get(e.getWord().charAt(j)) + 1);
				}
			}
			for (Map.Entry<Character, Integer> entry : lettersInWord.entrySet()) {
				if (firstLettersInDictionaryWithPropriateLength.containsKey(entry.getKey()) == true) {
					if (entry.getValue() >= firstLettersInDictionaryWithPropriateLength.get(entry.getKey())) {
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
		Entry finalEntry = finalListOfWords.get(rand.nextInt(finalListOfWords.size()));
		if (cw.getBoard().getHeight() <= cw.getBoard().getWidth())
			return new CwEntry(finalEntry.getWord(), finalEntry.getClue(), 0, 0, Direction.HORIZ);
		else
			return new CwEntry(finalEntry.getWord(), finalEntry.getClue(), 0, 0, Direction.VERT);
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
		BoardCell startBC = null;
		BoardCell endBC = null;

		if (cw.getEntries().isEmpty() == true) {
			if (cw.getBoard().getHeight() >= cw.getBoard().getWidth()){
				entry = cw.getCwDB().getRandom(".{2," + cw.getBoard().getHeight() + "}");
				return new CwEntry(entry.getWord(), entry.getClue(), 0, 0, Direction.VERT);
			}	
			entry = cw.getCwDB().getRandom(".{2," + cw.getBoard().getWidth() + "}");
			return new CwEntry(entry.getWord(), entry.getClue(), 0, 0, Direction.HORIZ);
			//cwentry = getFirstWord(cw);
			//return cwentry;
		} else {
			LinkedList<BoardCell> startingBoardCells = cw.getBoard().getStartCells();
			while (startingBoardCells.isEmpty() == false) {
				int i = rand.nextInt(startingBoardCells.size());
				startBC = startingBoardCells.get(i);
				boolean directionFlag = true;
				if (startBC.getAbility(Direction.HORIZ, Position.START) == true && startBC.getAbility(Direction.VERT, Position.START) == true)
					directionFlag = rand.nextBoolean();
				else if (startBC.getAbility(Direction.HORIZ, Position.START) == true && startBC.getAbility(Direction.VERT, Position.START) == false)
					directionFlag = true;
				else if (startBC.getAbility(Direction.HORIZ, Position.START) == false && startBC.getAbility(Direction.VERT, Position.START) == true)
					directionFlag = false;

				if (directionFlag == true) {
					LinkedList<BoardCell> endingBoardCells = cw.getBoard().getEndCells(cw.getBoard().getVertPosition(startBC), cw.getBoard().getHorizPosition(startBC), Direction.HORIZ);
					while (endingBoardCells.isEmpty() == false) {
						//int p = rand.nextInt(endingBoardCells.size());
						int p = endingBoardCells.size() - 1;
						endBC = endingBoardCells.get(p);
						boolean possible = true;
						for (int r = 1; r < cw.getBoard().getHorizPosition(endBC) - cw.getBoard().getHorizPosition(startBC); r++) {
							if (r < (cw.getBoard().getHorizPosition(endBC) - cw.getBoard().getHorizPosition(startBC))
									&& cw.getBoard().getCell(cw.getBoard().getVertPosition(startBC), cw.getBoard().getHorizPosition(startBC) + r).getAbility(Direction.HORIZ, Position.INNER) == true)
								continue;
							else {
								possible = false;
								break;
							}
						}
						if (possible == true) {
							possible = false;
							for (int r = 0; r < cw.getBoard().getHorizPosition(endBC) - cw.getBoard().getHorizPosition(startBC) + 1; r++) {
								if (cw.getBoard().getCell(cw.getBoard().getVertPosition(startBC), cw.getBoard().getHorizPosition(startBC) + r).getContent() != null) {
									possible = true;
									break;
								}
							}
						}
						if (possible == false) {
							endingBoardCells.remove(p);
						}
						if (possible == true) {
							String currentPattern = cw.getBoard().createPattern(cw.getBoard().getVertPosition(startBC), cw.getBoard().getHorizPosition(startBC), cw.getBoard().getVertPosition(endBC),
									cw.getBoard().getHorizPosition(endBC));
							LinkedList<Entry> patternDict = cw.getCwDB().findAll(currentPattern);
							while (patternDict.size() > 0) {
								int kk = rand.nextInt(patternDict.size());
								entry = patternDict.get(kk);
								if (cw.contains(entry.getWord()) == false) {
									cwentry = new CwEntry(entry.getWord(), entry.getClue(), cw.getBoard().getVertPosition(startBC), cw.getBoard().getHorizPosition(startBC), Direction.HORIZ);
									return cwentry;
								} else
									patternDict.remove(kk);
							}
							endingBoardCells.remove(p);
						}
					}
				} else {
					LinkedList<BoardCell> endingBoardCells = cw.getBoard().getEndCells(cw.getBoard().getVertPosition(startBC), cw.getBoard().getHorizPosition(startBC), Direction.VERT);
					while (endingBoardCells.isEmpty() == false) {
						//int p = rand.nextInt(endingBoardCells.size());
						int p = endingBoardCells.size() - 1;
						endBC = endingBoardCells.get(p);
						boolean possible = true;
						for (int r = 1; r < cw.getBoard().getVertPosition(endBC) - cw.getBoard().getVertPosition(startBC); r++) {
							if (r < (cw.getBoard().getVertPosition(endBC) - cw.getBoard().getVertPosition(startBC))
									&& cw.getBoard().getCell(cw.getBoard().getVertPosition(startBC) + r, cw.getBoard().getHorizPosition(startBC)).getAbility(Direction.VERT, Position.INNER) == true)
								continue;
							else {
								possible = false;
								break;
							}
						}
						if (possible == true) {
							possible = false;
							for (int r = 0; r < cw.getBoard().getVertPosition(endBC) - cw.getBoard().getVertPosition(startBC) + 1; r++) {
								if (cw.getBoard().getCell(cw.getBoard().getVertPosition(startBC) + r, cw.getBoard().getHorizPosition(startBC)).getContent() != null) {
									possible = true;
									break;
								}
							}
						}
						if (possible == false) {
							endingBoardCells.remove(p);
						}
						if (possible == true) {
							String currentPattern = cw.getBoard().createPattern(cw.getBoard().getVertPosition(startBC), cw.getBoard().getHorizPosition(startBC), cw.getBoard().getVertPosition(endBC),
									cw.getBoard().getHorizPosition(endBC));
							LinkedList<Entry> patternDict = cw.getCwDB().findAll(currentPattern);
							while (patternDict.size() > 0) {
								int kk = rand.nextInt(patternDict.size());
								entry = patternDict.get(kk);
								if (cw.contains(entry.getWord()) == false) {
									cwentry = new CwEntry(entry.getWord(), entry.getClue(), cw.getBoard().getVertPosition(startBC), cw.getBoard().getHorizPosition(startBC), Direction.VERT);
									return cwentry;
								} else
									patternDict.remove(kk);
							}
							endingBoardCells.remove(p);
						}
					}
				}
				startingBoardCells.remove(i);
			}
		}
		return null;
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

		for (int i = 0; i < e.getWord().length(); i++) {
			if (e.getDir() == Direction.VERT) {
				b.getCell(e.getX() + i, e.getY()).setContent(new String(contentOfString, i, 1));
				for (int m = e.getX() - 1 + i; m < e.getX() + 2 + i; m++)
					for (int n = e.getY() - 1; n < e.getY() + 2 + i; n++) {
						if (m >= 0 && n >= 0 && m < b.getHeight() && n < b.getWidth()) {
							if (m == e.getX() - 1 && n == e.getY() - 1) {
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.START, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.INNER, false);
							} else if (m == e.getX() - 1 && n == e.getY()) {
								b.getCell(m, n).setAbility(Direction.VERT, Position.START, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.START, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.INNER, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.INNER, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.END, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.END, false);
							} else if (m == e.getX() - 1 && n == e.getY() + 1) {
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.START, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.INNER, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.END, false);
							} else if (m == e.getX() + i && n == e.getY() - 1) {
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.END, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.INNER, false);
								if (m != e.getX() && n == e.getY() - 1)
									b.getCell(m, n).setAbility(Direction.VERT, Position.END, false);
								if (m != e.getX() + e.getWord().length() - 1 && n == e.getY() - 1)
									b.getCell(m, n).setAbility(Direction.VERT, Position.START, false);
							} else if (m == e.getX() + i && n == e.getY()) {
								b.getCell(m, n).setAbility(Direction.VERT, Position.START, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.INNER, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.END, false);
							} else if (m == e.getX() + i && n == e.getY() + 1) {
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.START, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.INNER, false);
								if (m != e.getX() && n == e.getY() + 1)
									b.getCell(m, n).setAbility(Direction.VERT, Position.END, false);
								if (m != e.getX() + e.getWord().length() - 1 && n == e.getY() + 1)
									b.getCell(m, n).setAbility(Direction.VERT, Position.START, false);
							} else if (m == e.getX() + e.getWord().length() && n == e.getY() - 1) {
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.START, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.INNER, false);
							} else if (m == e.getX() + e.getWord().length() && n == e.getY()) {
								b.getCell(m, n).setAbility(Direction.VERT, Position.START, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.START, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.INNER, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.INNER, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.END, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.END, false);
							} else if (m == e.getX() + e.getWord().length() && n == e.getY() + 1) {
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.INNER, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.END, false);
							}
						}
					}
			} else {
				b.getCell(e.getX(), e.getY() + i).setContent(new String(contentOfString, i, 1));
				for (int m = e.getX() - 1; m < e.getX() + 2 + i; m++)
					for (int n = e.getY() - 1 + i; n < e.getY() + 2 + i; n++) {
						if (m >= 0 && n >= 0 && m < b.getHeight() && n < b.getWidth()) {
							if (m == e.getX() - 1 && n == e.getY() - 1) {
								b.getCell(m, n).setAbility(Direction.VERT, Position.START, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.INNER, false);
							} else if (m == e.getX() && n == e.getY() - 1) {
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.START, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.START, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.INNER, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.INNER, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.END, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.END, false);
							} else if (m == e.getX() + 1 && n == e.getY() - 1) {
								b.getCell(m, n).setAbility(Direction.VERT, Position.START, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.INNER, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.END, false);
							} else if (m == e.getX() - 1 && n == e.getY() + i) {
								b.getCell(m, n).setAbility(Direction.VERT, Position.END, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.INNER, false);
								if (m == e.getX() - 1 && n != e.getY())
									b.getCell(m, n).setAbility(Direction.HORIZ, Position.END, false);
								if (m == e.getX() - 1 && n != e.getY() + e.getWord().length() - 1)
									b.getCell(m, n).setAbility(Direction.HORIZ, Position.START, false);
							} else if (m == e.getX() && n == e.getY() + i) {
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.START, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.INNER, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.END, false);
							} else if (m == e.getX() + 1 && n == e.getY() + i) {
								b.getCell(m, n).setAbility(Direction.VERT, Position.START, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.INNER, false);
								if (m == e.getX() + 1 && n != e.getY())
									b.getCell(m, n).setAbility(Direction.HORIZ, Position.END, false);
								if (m == e.getX() + 1 && n != e.getY() + e.getWord().length() - 1)
									b.getCell(m, n).setAbility(Direction.HORIZ, Position.START, false);
							} else if (m == e.getX() - 1 && n == e.getY() + e.getWord().length()) {
								b.getCell(m, n).setAbility(Direction.VERT, Position.START, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.INNER, false);
							} else if (m == e.getX() && n == e.getY() + e.getWord().length()) {
								b.getCell(m, n).setAbility(Direction.VERT, Position.START, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.START, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.INNER, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.INNER, false);
								b.getCell(m, n).setAbility(Direction.HORIZ, Position.END, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.END, false);
							} else if (m == e.getX() + 1 && n == e.getY() + e.getWord().length()) {
								b.getCell(m, n).setAbility(Direction.VERT, Position.INNER, false);
								b.getCell(m, n).setAbility(Direction.VERT, Position.END, false);
							}

						}
					}
			}
		}
		/* To debug abilities of cells
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
		System.out.println();*/
		/* To debug letters in cells*/
		for (int i = 0; i < b.getHeight(); i++) {
			for (int j = 0; j < b.getWidth(); j++) {
				if (b.getCell(i, j).getContent() != null)
					System.out.print(b.getCell(i, j).getContent());
				else
					System.out.print("-");
			}
			System.out.println();
		}
		System.out.println();
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
				b.getCell(i, j).setAbility(Direction.HORIZ, Position.START, true);
				b.getCell(i, j).setAbility(Direction.VERT, Position.START, true);
				b.getCell(i, j).setAbility(Direction.HORIZ, Position.INNER, true);
				b.getCell(i, j).setAbility(Direction.VERT, Position.INNER, true);
				b.getCell(i, j).setAbility(Direction.HORIZ, Position.END, true);
				b.getCell(i, j).setAbility(Direction.VERT, Position.END, true);
				if (j == b.getWidth() - 1) {
					b.getCell(i, j).setAbility(Direction.HORIZ, Position.START, false);
					b.getCell(i, j).setAbility(Direction.HORIZ, Position.INNER, false);
				}
				if (i == b.getHeight() - 1) {
					b.getCell(i, j).setAbility(Direction.VERT, Position.START, false);
					b.getCell(i, j).setAbility(Direction.VERT, Position.INNER, false);
				}
				if (j == 0) {
					b.getCell(i, j).setAbility(Direction.HORIZ, Position.INNER, false);
					b.getCell(i, j).setAbility(Direction.HORIZ, Position.END, false);
				}
				if (i == 0) {
					b.getCell(i, j).setAbility(Direction.VERT, Position.INNER, false);
					b.getCell(i, j).setAbility(Direction.VERT, Position.END, false);
				}
			}
	}

}
