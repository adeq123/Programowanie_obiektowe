package Lab3;

public class PracowityStudent extends Student {

	private int sumaGodzin;

	public Pracownik rzutujNaPracownika() {
		return new InnerPracownik();
	}

	public void dodajZajecia() {
		iloscZajec++;
		sumaGodzin++;
	}

	class InnerPracownik extends Pracownik {

		@Override
		public void dodajZajecia() {
			// TODO Auto-generated method stub
			iloscZajec++; // zmienia zmienna odziedziczona z Pracownik
			sumaGodzin++;
			PracowityStudent.this.iloscZajec++;// zmienia zmienna
												// PracowitegoStudenta
												// odziedziczona z Student
		}

		public String toString() {
			return "InnerPracownik ma " + iloscZajec + " zajec";
		}
	}

	public String toString() {
		return "PracowityStudent ma " + iloscZajec + " zajec i sume godzin: "
				+ sumaGodzin;
	}
}
