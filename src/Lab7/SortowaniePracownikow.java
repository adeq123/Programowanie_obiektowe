package Lab7;

import java.util.Collections;
import java.util.Comparator;

public class SortowaniePracownikow implements Comparator<Pracownik> {

	@Override
	public int compare(Pracownik p1, Pracownik p2) {
		if (p1.wynagrodzenieBrutto > p2.wynagrodzenieBrutto)
			return 1;
		else if (p1.wynagrodzenieBrutto < p2.wynagrodzenieBrutto)
			return -1;
		return 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Kadry kadry = new Kadry();
		kadry.dodajPracownika(new Student(5000, "12345678912"));
		kadry.dodajPracownika(new Student(1000, "23456789123"));
		kadry.dodajPracownika(new PracownikEtatowy(4000, "34567891234"));
		kadry.dodajPracownika(new PracownikEtatowy(2000, "45678912345"));
		kadry.dodajPracownika(new Student(3000, "56789123456"));

		System.out.println("Nieposortowanie: ");
		for (Pracownik p : kadry.listaPracownikow) {
			System.out.println(p);
		}

		Collections.sort(kadry.listaPracownikow, new SortowaniePracownikow());

		System.out.println("Posortowanie: ");
		for (Pracownik p : kadry.listaPracownikow) {
			System.out.println(p);
		}
	}
}
