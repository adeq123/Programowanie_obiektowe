package pkg2;

import pkg1.B;

public class C extends B {
	public C(int number, String name) {
		super(number, name);
	}

	/*Pole name jest niewidoczne
	 * 
	 * // package 
	 * void changeName void changeName() { 
	 * name = "Nowa nazwa C"; }
	 * 
	 * public String toString() { 
	 * return name; }
	 */
}
