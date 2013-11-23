package dictionary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author krzysztof
 * 
 */
public class InteliCwDB extends CwDB {

	/**
	 * Constructor
	 * 
	 * @param filename
	 *            - name of file
	 * @throws FileNotFoundException
	 *             , IOException
	 */
	public InteliCwDB(String filename) throws FileNotFoundException,
			IOException {
		super(filename);
	}

	/**
	 * Creates list of Entries that matches pattern
	 * 
	 * @param pattern
	 *            - pattern
	 * @return list of Entries
	 */
	public LinkedList<Entry> findAll(String pattern) {
		LinkedList<Entry> newDict = new LinkedList<Entry>();
		Pattern myPattern = Pattern.compile(pattern);
		Entry e = null;
		ListIterator<Entry> it = dict.listIterator();
		while (it.hasNext() == true) {
			e = it.next();
			Matcher matcher = myPattern.matcher(e.getWord());
			if (matcher.matches() == true)
				newDict.add(e);
			matcher.reset();
		}
		return newDict;
	}

	/**
	 * Search for random Entry
	 * 
	 * @return found Entry
	 */
	public Entry getRandom() {
		Random rand = new Random();
		return dict.get(rand.nextInt(dict.size()));
	}

	/**
	 * Search for random Entry
	 * 
	 * @param length
	 *            - length of searching word
	 * @return found Entry
	 */
	public Entry getRandom(int length) {
		Random rand = new Random();
		LinkedList<Entry> lengthDict = new LinkedList<Entry>();
		Entry e = null;
		for (ListIterator<Entry> it = dict.listIterator(); it.hasNext();) {
			e = it.next();
			if (e.getWord().length() == length)
				lengthDict.add(e);
		}
		return lengthDict.get(rand.nextInt(lengthDict.size()));
	}

	/**
	 * Search for random Entry
	 * 
	 * @param pattern
	 *            - pattern of word
	 * @return found Entry
	 */
	public Entry getRandom(String pattern) {
		Random rand = new Random();
		LinkedList<Entry> patternDict = findAll(pattern);
		if (patternDict.isEmpty() == true)
			return null;
		return patternDict.get(rand.nextInt(patternDict.size()));
	}

	/**
	 * Add Entry to dictionary and sort it
	 * 
	 * @param word
	 *            - word
	 * 
	 * @param clue
	 *            - clue
	 */
	@Override
	public void add(String word, String clue) {
		dict.add(new Entry(word.toUpperCase(), clue));
		Collections.sort(dict, new Comparator<Entry>() {
			@Override
			public int compare(Entry first, Entry second) {
				return first.getWord().compareTo(second.getWord());
			}
		});
	}
}
