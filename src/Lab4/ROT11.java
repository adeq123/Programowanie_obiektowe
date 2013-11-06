package Lab4;

public class ROT11 implements Algorythm {
	private static final char[] alphabet = new char[] { 'a', 'b', 'c', 'd',
			'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private static final int ROTATION = 11;

	@Override
	public String crypt(String word) {
		char[] table = new char[word.length()];
		table = word.toCharArray();
		for (int i = 0; i < table.length; i++) {
			if (table[i] == ' ')
				continue;
			if (table[i] <= 'Z') {
				table[i] += ROTATION;
				if (table[i] > 'Z') {
					table[i] %= ('Z' + 1);
					table[i] += 'A';
				}
			} else {
				table[i] += ROTATION;
				if (table[i] > 'z') {
					table[i] %= ('z' + 1);
					table[i] += 'a';
				}
			}
		}
		return new String(table);
	}

	@Override
	public String decrypt(String word) {
		char[] table = new char[word.length()];
		table = word.toCharArray();
		for (int i = 0; i < table.length; i++) {
			if (table[i] == ' ')
				continue;
			if (table[i] <= 'Z') {
				table[i] -= ROTATION;
				if (table[i] < 'A') {
					table[i] = (char) ('A' - table[i]);
					table[i] = (char) ('Z' - table[i] + 1);
				}
			} else {
				table[i] -= ROTATION;
				if (table[i] < 'a') {
					table[i] = (char) ('a' - table[i]);
					table[i] = (char) ('z' - table[i] + 1);
				}
			}
		}
		return new String(table);
	}
}
