package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 
 * @author krzysztof
 * 
 */
public class CwDB {

	protected LinkedList<Entry> dict; // list of Entries

	/**
	 * Getter
	 * 
	 * @return dict
	 */
	public LinkedList<Entry> getDict() {
		return dict;
	}

	/**
	 * Constructor
	 * 
	 * @param filename
	 *            - name of file
	 * @throws IOException
	 */
	public CwDB(String filename) throws FileNotFoundException, IOException {
		dict = new LinkedList<Entry>();
		createDB(filename);
	}

	/**
	 * Add word and clue to dictionary
	 * 
	 * @param word
	 *            - word to add
	 * @param clue
	 *            - clue connected to word
	 */
	public void add(String word, String clue) {
		dict.add(new Entry(word, clue));
	}

	/**
	 * Returns Entry connected with word
	 * 
	 * @param word
	 *            - word to find its clue
	 * @return Entry with found word, null if word doesn't exist in dictionary
	 */
	public Entry get(String word) {
		Entry e = null;
		ListIterator<Entry> it = dict.listIterator();
		while (it.hasNext() == true) {
			e = it.next();
			if (e.getWord().equals(word))
				break;
		}
		if (e.getWord().equals(word) == false)
			return null;
		return e;
	}

	/**
	 * Remove Entry connected with word from dictionary
	 * 
	 * @param word
	 *            - key to find Entry to remove from dictionary
	 */
	public void remove(String word) {
		Entry e = null;
		for (ListIterator<Entry> it = dict.listIterator(); it.hasNext();) {
			e = it.next();
			if (e.getWord().equals(word)) {
				it.remove();
				break;
			}
		}
	}

	/**
	 * Returns size of dictionary
	 * 
	 * @return size of dictionary
	 */
	public int getSize() {
		return dict.size();
	}

	/**
	 * Saves dictionary to concrete file
	 * 
	 * @param filename
	 *            - name of file to save
	 * @throws IOException
	 *             - possible exception
	 */
	public void saveDB(String filename) throws IOException {
		FileWriter fileWriter = new FileWriter(filename);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		try {
			Entry e = null;
			for (ListIterator<Entry> it = dict.listIterator(); it.hasNext();) {
				e = it.next();
				bufferedWriter.write(e.getWord());
				bufferedWriter.newLine();
				bufferedWriter.write(e.getClue());
				bufferedWriter.newLine();
			}
		} finally {
			if (bufferedWriter != null)
				bufferedWriter.close();
		}
	}

	/**
	 * Creates file with dictionary
	 * 
	 * @param filename
	 *            - name of file
	 * @throws IOException
	 *             - possible exception
	 */
	protected void createDB(String filename) throws FileNotFoundException,
			IOException {
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String word = null;
		String clue = null;
		while ((word = bufferedReader.readLine()) != null
				&& ((clue = bufferedReader.readLine()) != null))
			add(word, clue);
		if (bufferedReader != null)
			bufferedReader.close();

	}
}
