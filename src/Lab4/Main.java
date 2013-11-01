package Lab4;

import java.io.IOException;

import Lab1.JIn;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args){
		// TODO Auto-generated method stub

		System.out.println("Podaj sciezke do pliku wejsciowego");
		String inputF = JIn.getString();

		System.out.println("Chcesz szyfrowac czy deszyfrowac? (s/d)");
		String choice = JIn.getString();

		System.out.println("Wybierz algorytm: ROT11 lub Polibusz? (r/p)");
		String algo = JIn.getString();
		
		if (algo.equals("r") == true) {
			Algorythm rot11 = new ROT11();
			if (choice.equals("s")) {
				try {
					Cryptographer.cryptfile(inputF, rot11);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					Cryptographer.decryptfile(inputF, rot11);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			Algorythm poli = new Polibiusz();
			if (choice.equals("d") == true) {
				try {
					Cryptographer.cryptfile(inputF, poli);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					Cryptographer.decryptfile(inputF, poli);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
