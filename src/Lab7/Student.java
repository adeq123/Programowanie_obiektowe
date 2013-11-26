package Lab7;

public class Student extends Pracownik {

	public Student(double wynBr, String p) {
		super(wynBr, p);
	}

	@Override
	public String toString() {
		return "Student o peselu " + pesel + " zarabia brutto "
				+ wynagrodzenieBrutto;
	}

	@Override
	public double liczNetto() {
		return wynagrodzenieBrutto * 0.8;
	}

}
