package dictionary;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InteliCwDB extends CwDB {

	public InteliCwDB(String filename) {
		super(filename);
	}

	public LinkedList<Entry> findAll(String pattern) {

		LinkedList<Entry> newDict = new LinkedList<Entry>();
		Pattern myPattern = Pattern.compile(pattern);

		Entry helper = null;
		for (ListIterator<Entry> it = dict.listIterator(); it.hasNext();) {
			helper = it.next();

			Matcher matcher = myPattern.matcher(helper.getWord());

			if (matcher.matches() == true) {
				newDict.add(helper);

			}
		}
		return newDict;
	}

	public Entry getRandom() {
		Random rand = new Random();
		return dict.get(rand.nextInt(dict.size()));
	}

	public Entry getRandom(int length) {
		Random rand = new Random();
		LinkedList<Entry> lengthDict = new LinkedList<Entry>();
		Entry helper = null;
		for (ListIterator<Entry> it = dict.listIterator(); it.hasNext();) {
			helper = it.next();
			if (helper.getWord().length() == length)
				lengthDict.add(helper);
		}
		return lengthDict.get(rand.nextInt(lengthDict.size()));

	}

	public Entry getRandom(String pattern) {
		Random rand = new Random();
		LinkedList<Entry> patternDict = findAll(pattern);
		return patternDict.get(rand.nextInt(patternDict.size()));

	}

	// redefinied!
	public void add(String word, String clue) {
		dict.add(new Entry(word, clue));
		Collections.sort(dict, new Comparator<Entry>() {
			@Override
			public int compare(Entry first, Entry second) {
				return first.getWord().compareTo(second.getWord());

			}

		});

	}
}
