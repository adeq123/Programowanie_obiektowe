package Lab2;

import java.util.LinkedList;

import Lab1.JIn;

public class Test {

	private static LinkedList<Punkt3D> punkty = new LinkedList<Punkt3D>();

	public static void main(String[] args) {
		int keyPressed = 0;
		while (keyPressed != 4) {
			System.out.println("1. Wczytaj punkt 3D");
			System.out.println("2. Wyœwietl wszystkie punkty");
			System.out.println("3. Oblicz odleg³oœæ");
			System.out.println("4. Zakoñcz");
			System.out.println("?");
			keyPressed = JIn.getInt();

			switch (keyPressed) {
			case 1:
				System.out.println("Podaj wspó³rzêdne punktu:");
				Punkt3D point = new Punkt3D(JIn.getDouble(), JIn.getDouble(),
						JIn.getDouble());
				punkty.add(point);
				break;
			case 2:
				for (int i = 0; i < punkty.size(); i++)
					System.out.println(punkty.get(i));
				break;
			case 3:
				if (punkty.size() > 1) {
					System.out.println("Podaj indeks pierwszego punktu:");
					int a = JIn.getInt();
					System.out.println("Podaj indeks drugiego punktu:");
					int b = JIn.getInt();
					if ((a >= 0) && (a < punkty.size()) && (b >= 0)
							&& (b < punkty.size()))
						System.out.println("Odleglosc wynosi: "
								+ punkty.get(a).distance(punkty.get(b)));
					else
						System.out.println("Poda³eœ nieprawid³owy indeks");
				} else
					System.out.println("Najpierw nale¿y dodac punkty");
				break;
			case 4:
				break;
			}

		}
	}

}
