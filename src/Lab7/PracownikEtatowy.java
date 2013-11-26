package Lab7;

public class PracownikEtatowy extends Pracownik {

	public PracownikEtatowy(double wynBr, String p) {
		super(wynBr, p);
	}

	@Override
	public String toString() {
		return "PracownikEtatowy o peselu " + pesel + " zarabia brutto "
				+ wynagrodzenieBrutto;
	}

	@Override
	public double liczNetto() {
		return wynagrodzenieBrutto * 0.6;
	}

}
