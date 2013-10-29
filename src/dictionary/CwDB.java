package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

public class CwDB {
	protected LinkedList<Entry> dict;

	public CwDB(String filename) {

		dict = new LinkedList<Entry>();

		try {
			createDB(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void add(String word, String clue) {
		dict.add(new Entry(word, clue));
	}

	public Entry get(String word) {
		Entry helper = null;
		for (ListIterator<Entry> it = dict.listIterator(); it.hasNext();) {
			helper = it.next();
			if (helper.getWord().equals(word))
				return helper;
		}
		return helper;
	}

	public void remove(String word) {
		Entry helper = null;
		for (ListIterator<Entry> it = dict.listIterator(); it.hasNext();) {
			helper = it.next();
			if (helper.getWord().equals(word)) {
				it.remove();
				break;
			}
		}
	}

	public int getSize() {
		return dict.size();
	}

	public void saveDB(String filename) throws IOException {
		FileWriter fileWriter = new FileWriter(filename);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		try {
			Entry temp = null;
			for (ListIterator<Entry> it = dict.listIterator(); it.hasNext();) {
				temp = it.next();
				bufferedWriter.write(temp.getWord());
				bufferedWriter.newLine();
				bufferedWriter.write(temp.getClue());
				bufferedWriter.newLine();
			}
		} finally {
			if (bufferedWriter != null)
				bufferedWriter.close();
		}
	}

	protected void createDB(String filename) throws IOException {
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		try {

			String word = null;
			String clue = null;
			while ((word = bufferedReader.readLine()) != null
					&& ((clue = bufferedReader.readLine()) != null))
				add(word, clue);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			bufferedReader.close();
		}
	}
}
