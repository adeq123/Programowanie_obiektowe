package Lab1;

public class LiczbyPierwsze {
	public static void wypiszMniejszeLiczbyPierwsze(int ograniczenieGorne) {

		for (int i = 2; i < ograniczenieGorne; i++) {
			if (czyPierwsza(i) == true)
				System.out.print(i + " ");
		}
	}

	public static boolean czyPierwsza(int i) {
		for (int j = 2; j < (int) Math.sqrt(i) + 1; j++) {
			if (i % j == 0)
				return false;
		}
		return true;
	}

	public static void main(String argv[]) {
		int ograniczenie = JIn.getInt();
		LiczbyPierwsze.wypiszMniejszeLiczbyPierwsze(ograniczenie);
	}
}
