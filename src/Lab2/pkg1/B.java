package Lab2.pkg1;

public class B extends A {
	protected void decrement() {
		number -= 2;
	}
	// package void changeName
	void changeName() {
		name = "Nowa nazwa B";
	}

//	private void increment() {
//		number += 2;
//	}
	public B(int number, String name) {
		super(number,name);
	}
	
	public String toString() {
		return name + this.number;
	}
}
