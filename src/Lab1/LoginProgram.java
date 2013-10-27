package Lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginProgram {
	public static void main(String[] argv) {
		Login log = new Login("ala", "makota");
		try {
			InputStreamReader rd = new InputStreamReader(System.in);
			BufferedReader bfr = new BufferedReader(rd);

			// TODO: prosba o wpisanie has�a i loginu
			System.out.println("Podaj swoj login:");
			String login = bfr.readLine();
			System.out.println("Podaj swoje haslo:");
			String haslo = bfr.readLine();

			/*
			 * TODO: sprawdzenie czy podane has�o i login zgadzaja sie z tymi
			 * przechowywanymi w obiekcie log Je�li tak, to ma zosta�
			 * wyswietlone "OK", jesli nie - prosze wyswietli� informacje o
			 * b�edzie
			 */
			if (log.check(login, haslo) == true)
				System.out.println("OK");
			else
				System.out.println("Blad przy logowaniu");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
