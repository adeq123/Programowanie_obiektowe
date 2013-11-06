package Lab4;

import java.util.HashMap;
import java.util.Map;

public class Polibiusz implements Algorythm {

	public static final Map<Character, Integer> dictionary = new HashMap<Character, Integer>();
	public static final Map<Integer, Character> dictionary2 = new HashMap<Integer, Character>();
	static {
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				if (i * 10 + j < 14) {
					dictionary.put((char) ('A' + i * 5 + j), (i + 1) * 10 + j
							+ 1);
					dictionary2.put((i + 1) * 10 + j + 1,
							(char) ('A' + i * 5 + j));
				}
				if (i * 10 + j > 12) {
					dictionary.put((char) ('B' + i * 5 + j), (i + 1) * 10 + j
							+ 1);
					dictionary2.put((i + 1) * 10 + j + 1,
							(char) ('B' + i * 5 + j));
				}
			}
	}

	@Override
	public String crypt(String word) {
		char[] table = new char[word.length()];
		table = word.toCharArray();
		String rezultat = "";
		for (int i = 0; i < table.length; i++) {
			if (dictionary.containsKey(table[i])) {
				rezultat += dictionary.get(table[i]);
				rezultat += " ";
			}
		}
		return rezultat;
	}

	@Override
	public String decrypt(String word) {
		int[] table2 = new int[word.length()];
		for (int i = 0, j = 0; i < word.length(); i = i + 2, j++) {
			if (word.charAt(i) == ' ') {
				i--;
				j--;
				continue;
			}
			int a = (word.charAt(i) - '1' + 1) * 10;
			a += word.charAt(i + 1) + 1 - '1';
			if (dictionary2.containsKey(a) == true)
				table2[j] = a;
		}
		String rezultat = "";
		for (int i = 0; i < table2.length; i++) {
			if (table2[i] != 0) {
				rezultat += dictionary2.get(table2[i]);
				rezultat += " ";
			}
		}
		return rezultat;
	}
}
