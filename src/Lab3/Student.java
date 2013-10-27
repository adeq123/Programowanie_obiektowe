package Lab3;

public abstract class Student {
	protected int iloscZajec;
	public abstract void dodajZajecia();
	
	public String toString(){
		return "Student ma " + iloscZajec + " zajec";
	}
}
