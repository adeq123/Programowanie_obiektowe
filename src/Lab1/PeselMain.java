package Lab1;

public class PeselMain {
	public static void main(String[] args) {
		System.out.println("Podaj swoj numer PESEL:");
		String numer = JIn.getString();
		boolean stan = Pesel.jestPoprawny(numer);
		if (stan == true)
			System.out.println("Pesel poprawny!");
		else
			System.out.println("Pesel niepoprawny!");
	}
}
