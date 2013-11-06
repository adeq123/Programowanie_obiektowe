package Lab4;

import java.io.IOException;

import Lab1.JIn;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		try {
			String inputF = args[0];
			String outputF = args[1];

			System.out.println("Chcesz szyfrowac czy deszyfrowac? (s/d)");
			String choice = JIn.getString();

			System.out.println("Wybierz algorytm: ROT11 lub Polibusz? (r/p)");
			String algo = JIn.getString();

			if (algo.equals("r") == true) {
				Algorythm rot11 = new ROT11();
				if (choice.equals("s")) {
					Cryptographer.cryptfile(inputF, outputF, rot11);
				} else {
					Cryptographer.decryptfile(inputF, outputF, rot11);
				}
			} else {
				Algorythm poli = new Polibiusz();
				if (choice.equals("s") == true) {
					Cryptographer.cryptfile(inputF, outputF, poli);
				} else {
					Cryptographer.decryptfile(inputF, outputF, poli);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e1) {
			e1.printStackTrace();
		}
	}
}
