package Lab2;

import Lab2.pkg1.A;
import Lab2.pkg1.B;
import Lab2.pkg2.C;

public class ABCMain {

	/*1.Pola o dost�pie pakietowym sa widoczne w klasach pochodnych znajduj�cych si� w tym samym
	 * pakiecie, natomiast nie s� widoczne w klasach pochodnych w innych pakietach.
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
