package Lab3;

import java.util.LinkedList;

public class Zad1 {

	public static void changeFinalDouble(final double var){
		//nie mozna zmienic wartosci zmiennej final
		//var = 3.12;
	}
	public static void changeFinalLinkedList(final LinkedList<Double> list){
		list.add(3.14);
	}
	
}
