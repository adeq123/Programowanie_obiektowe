package Lab4;

import java.util.HashMap;
import java.util.Map;

public class Polibiusz implements Algorythm {

	public static final Map<Character, Integer> dictionary = new HashMap<Character, Integer>();
	static {
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				if (i * 10 + j < 25)
					dictionary.put((char) ('A' + i * 5 + j), (i + 1) * 10 + j
							+ 1);
				else if (i * 10 + j > 23)
					dictionary.put((char) ('B' + i * 5 + j), (i + 1) * 10 + j
							+ 1);
			}
	}

	@Override
	public String crypt(String word) {
		// TODO Auto-generated method stub
		char[] table = new char[word.length()];
		table = word.toCharArray();

		
		return new String(table);
	}

	@Override
	public String decrypt(String word) {
		// TODO Auto-generated method stub
		return null;
	}

}
