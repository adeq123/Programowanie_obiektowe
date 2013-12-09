package crossword.strategy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import crossword.board.Board;
import crossword.board.Crossword;
import crossword.dictionary.CwEntry;
import crossword.dictionary.CwEntry.Direction;
import crossword.dictionary.Entry;
import crossword.exceptions.noPossibilityToGenerateCrosswordException;

/**
 * 
 * @author krzysztof
 * 
 */
public class EasyStrategy extends Strategy {

	/**
	 * Returns CwEntry being new main word
	 * 
	 * @param cw
	 *            - Crossword
	 * @return CwEntry
	 * @throws noPossibilityToGenerateCrosswordException
	 */
	public CwEntry getMainWord(Crossword cw)
			throws noPossibilityToGenerateCrosswordException {
		Map<Character, Integer> firstLettersInDictionaryWithPropriateLength = new HashMap<Character, Integer>();
		LinkedList<Entry> sameLengthWords = new LinkedList<Entry>();

		Entry e = null;
		Iterator<Entry> it = cw.getCwDB().getDict().iterator();
		while (it.hasNext()) {
			e = it.next();
			if ((firstLettersInDictionaryWithPropriateLength.containsKey(e.getWord().charAt(0)) == false) && (e.getWord().length() <= cw.getBoard().getWidth()))
				firstLettersInDictionaryWithPropriateLength.put(e.getWord().charAt(0), 1);
			else if (e.getWord().length() <= cw.getBoard().getWidth())
				firstLettersInDictionaryWithPropriateLength
						.put(e.getWord().charAt(0), firstLettersInDictionaryWithPropriateLength
								.get(e.getWord().charAt(0)) + 1);
			if (e.getWord().length() == cw.getBoard().getHeight()) {
				sameLengthWords.add(new Entry(e.getWord(),e.getClue()));
			}
		}
		
		Iterator<Entry> iter = sameLengthWords.iterator();
		LinkedList<Entry> finalListOfWords = new LinkedList<Entry>(
				sameLengthWords);
		while (iter.hasNext() == true) {
			e = iter.next();
			Map<Character, Integer> lettersInWord = new HashMap<Character, Integer>();
			for (int j = 0; j < e.getWord().length(); j++) {
				if (lettersInWord.containsKey(e.getWord().charAt(j)) == false){
					
					lettersInWord.put(e.getWord().charAt(j), 1);
				}
				else{
					
					lettersInWord.put(e.getWord().charAt(j),
							lettersInWord.get(e.getWord().charAt(j)) + 1);
				}
			}
			for (Map.Entry<Character, Integer> entry : lettersInWord.entrySet()) {
				if (firstLettersInDictionaryWithPropriateLength.containsKey(entry.getKey()) == true) {
					if (entry.getValue() >= firstLettersInDictionaryWithPropriateLength.get(entry
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
			throw new noPossibilityToGenerateCrosswordException("There is no possibility to generate easy crossword with entered data!");
		Random rand = new Random();
		Entry finalEntry = finalListOfWords.get(rand.nextInt(finalListOfWords
				.size()));
		return new CwEntry(finalEntry.getWord(), finalEntry.getClue(), 0, 0,
				Direction.VERT);
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
		if (cw.getBoard().getHeight() == (amountOfWords - 1))
			return null;
		if (cw.getEntries().isEmpty() == true) {
			cwentry = getMainWord(cw);
		} else {
			do {
				String currentPattern = cw.getBoard().createPattern(
						amountOfWords - 1, 0, amountOfWords - 1,
						rand.nextInt(cw.getBoard().getWidth()));
				entry = cw.getCwDB().getRandom(currentPattern);
			} while ((entry == null || cw.contains(entry.getWord()) == true));
			cwentry = new CwEntry(entry.getWord(), entry.getClue(),
					amountOfWords - 1, 0, Direction.HORIZ);
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
		
		for (int i = 0; i < e.getWord().length(); i++) {
			if (e.getDir() == Direction.VERT) {
				b.getCell(e.getX() + i, e.getY()).setContent(new String(contentOfString, i, 1));
			}
			else
				b.getCell(e.getX(), e.getY() + i).setContent(new String(contentOfString, i, 1));
		}
	}

	@Override
	public String toString() {
		return "Easy";
	}
}