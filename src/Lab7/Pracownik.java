package Lab7;

public abstract class Pracownik {
	String pesel;
	double wynagrodzenieBrutto;

	public Pracownik(double wynBr, String p) {
		wynagrodzenieBrutto = wynBr;
		pesel = p;
	}

	public abstract double liczNetto();

	public String getPesel() {
		return pesel;
	}
}
