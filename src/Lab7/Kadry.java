package Lab7;

import java.util.LinkedList;

public class Kadry {
	public LinkedList<Pracownik> listaPracownikow = new LinkedList<Pracownik>();
	
	public void dodajPracownika(Pracownik pracownik){
		listaPracownikow.add(pracownik);
	}
	
	public Pracownik wyszukajPracownika(String pesel){
		for (int i = 0; i < listaPracownikow.size(); i++) {
			if (listaPracownikow.get(i).getPesel().equals(pesel))
				return listaPracownikow.get(i);
		}
		return null;
	}
	
	public void usunPracownika(String pesel){
		listaPracownikow.remove(wyszukajPracownika(pesel));
	}
	
	public void zmienBrutto(double noweBrutto, String pesel){
		wyszukajPracownika(pesel).wynagrodzenieBrutto = noweBrutto;	
	}
	
	public double pobierzBrutto(String pesel){
		return wyszukajPracownika(pesel).wynagrodzenieBrutto;	
	}
	
	public double pobierzNetto(String pesel){
		return wyszukajPracownika(pesel).liczNetto();	
	}
	
}
