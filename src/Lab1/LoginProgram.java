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

			// TODO: prosba o wpisanie has³a i loginu
			System.out.println("Podaj swoj login:");
			String login = bfr.readLine();
			System.out.println("Podaj swoje haslo:");
			String haslo = bfr.readLine();

			/*
			 * TODO: sprawdzenie czy podane has³o i login zgadzaja sie z tymi
			 * przechowywanymi w obiekcie log Jeœli tak, to ma zostaæ
			 * wyswietlone "OK", jesli nie - prosze wyswietliæ informacje o
			 * b³edzie
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
