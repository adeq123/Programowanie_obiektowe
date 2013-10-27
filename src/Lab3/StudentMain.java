package Lab3;

public class StudentMain {
	public static PracowityStudent pracowityStudent = new PracowityStudent();

	public static void main(String[] args) {
		pracowityStudent.rzutujNaPracownika().dodajZajecia();
		System.out.println(pracowityStudent); // zmienila sie iloscZajec w
												// Pracownik, sumaGodzin w
												// PracowityStudent
		PracowityStudent a = new PracowityStudent();
		PracowityStudent.InnerPracownik b = a.new InnerPracownik();
		b.dodajZajecia();
		b.dodajZajecia();
		System.out.println(a);
		System.out.println(b);
	}

}
