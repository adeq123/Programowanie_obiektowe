package pkg1;

public class A {

	protected int number;
	// package String
	String name;

	public A(int number, String name) {
		this.number = number;
		this.name = name;
	}

	public void callDecrement() {
		decrement();
	}

	public void callChangeName() {
		changeName();
	}

	public void callIncrement() {
		increment();
	}

	private void increment() {
		number += 1;
	}

	protected void decrement() {
		number -= 1;
	}

	//package changeName
	void changeName() {
		name = "Nowa nazwa A";
	}

	public String toString() {
		return name + this.number;
	}
}
