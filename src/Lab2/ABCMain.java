package Lab2;

import pkg1.A;
import pkg1.B;
import pkg2.C;

public class ABCMain {

	/*1.Pola o dostêpie pakietowym sa widoczne w klasach pochodnych znajduj¹cych siê w tym samym
	 * pakiecie, natomiast nie s¹ widoczne w klasach pochodnych w innych pakietach.
	 * 
	 */
	public static void main(String[] args) {
		A a  = new A(100, "Klasa A");
		B b = new B(200, "Klasa B");
		C c = new C(300, "Klasa C");
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
		a.callChangeName();
		b.callChangeName();
		c.callChangeName();
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
		a.callDecrement();
		b.callDecrement();
		c.callDecrement();
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
		a.callIncrement();
		b.callIncrement();
		c.callIncrement();
	
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		

	}

}
