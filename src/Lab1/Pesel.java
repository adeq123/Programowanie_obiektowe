package Lab1;

public class Pesel {
	private static boolean dataPoprawna(int[] proba) {
		if (proba[2] == 4 || proba[2] == 5 || proba[2] == 6 || proba[2] == 7
				|| proba[4] == 4 || proba[4] == 5 || proba[4] == 6
				|| proba[4] == 7 || proba[4] == 8 || proba[4] == 9)
			return false;
		return true;
	}

	private static boolean cyfraKontrolna(int[] proba) {
		int poczatek = proba[0] + 3 * proba[1] + 7 * proba[2] + 9 * proba[3]
				+ proba[4] + 3 * proba[5] + 7 * proba[6] + 9 * proba[7]
				+ proba[8] + 3 * proba[9];
		int cyfraKontrolna = poczatek % 10;
		if (cyfraKontrolna == 0)
			return (cyfraKontrolna == proba[10]);
		else
			return ((10 - cyfraKontrolna % 10) == proba[10]);
	}

	public static boolean jestPoprawny(String proba) {
		char[] lol = proba.toCharArray();
		if (lol.length != 11)
			return false;
		int[] lol2 = new int[11];
		for (int i = 0; i < lol.length; i++) {
			lol2[i] = (int) lol[i] - 48;
		}
		return dataPoprawna(lol2) && cyfraKontrolna(lol2);
	}
}
