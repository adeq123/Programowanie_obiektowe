package strategy;

import java.util.LinkedList;
import java.util.Random;

import board.Board;
import board.BoardCell;
import board.BoardCell.Position;
import board.Crossword;
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
			if (cw.getBoard().getHeight() >= cw.getBoard().getWidth()) {
				entry = cw.getCwDB().getRandom(".{2," + cw.getBoard().getHeight() + "}");
				if (entry == null)
					throw new noPossibilityToGenerateCrosswordException("There is no possibility to generate hard crossword with entered data!");
				return new CwEntry(entry.getWord(), entry.getClue(), 0, 0, Direction.VERT);
			} else {
				entry = cw.getCwDB().getRandom(".{2," + cw.getBoard().getWidth() + "}");
				if (entry == null)
					throw new noPossibilityToGenerateCrosswordException("There is no possibility to generate hard crossword with entered data!");
				return new CwEntry(entry.getWord(), entry.getClue(), 0, 0, Direction.HORIZ);
			}
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
						int p = endingBoardCells.size() - 1;
						endBC = endingBoardCells.get(p);
						boolean possible = true;
						for (int r = 1; r < cw.getBoard().getHorizPosition(endBC) - cw.getBoard().getHorizPosition(startBC); r++) {
							if (r < (cw.getBoard().getHorizPosition(endBC) - cw.getBoard().getHorizPosition(startBC)) && cw.getBoard().getCell(cw.getBoard().getVertPosition(startBC), cw.getBoard().getHorizPosition(startBC) + r).getAbility(Direction.HORIZ, Position.INNER) == true)
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
							String currentPattern = cw.getBoard().createPattern(cw.getBoard().getVertPosition(startBC), cw.getBoard().getHorizPosition(startBC), cw.getBoard().getVertPosition(endBC), cw.getBoard().getHorizPosition(endBC));
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
						int p = endingBoardCells.size() - 1;
						endBC = endingBoardCells.get(p);
						boolean possible = true;
						for (int r = 1; r < cw.getBoard().getVertPosition(endBC) - cw.getBoard().getVertPosition(startBC); r++) {
							if (r < (cw.getBoard().getVertPosition(endBC) - cw.getBoard().getVertPosition(startBC)) && cw.getBoard().getCell(cw.getBoard().getVertPosition(startBC) + r, cw.getBoard().getHorizPosition(startBC)).getAbility(Direction.VERT, Position.INNER) == true)
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
							String currentPattern = cw.getBoard().createPattern(cw.getBoard().getVertPosition(startBC), cw.getBoard().getHorizPosition(startBC), cw.getBoard().getVertPosition(endBC), cw.getBoard().getHorizPosition(endBC));
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

	@Override
	public String toString() {
		return "Hard";
	}

}
