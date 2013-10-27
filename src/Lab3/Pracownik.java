package Lab3;

public abstract class Pracownik {
	protected int iloscZajec;
	public abstract void dodajZajecia();
	
	public String toString(){
		return "Pracownik ma " + iloscZajec + " zajec";
	}
}
