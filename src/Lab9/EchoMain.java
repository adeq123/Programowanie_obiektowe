package Lab9;

import java.io.IOException;

public class EchoMain {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Zaczynamy");
		
		System.out.println("Tworze klienta numer 1");
		EchoClient client1 = new EchoClient(1);
		Thread client1Thread = new Thread(client1);
		client1Thread.start();
		
		System.out.println("Tworze klienta numer 2");
		EchoClient client2 = new EchoClient(2);
		Thread client2Thread = new Thread(client2);
		client2Thread.start();
		
		System.out.println("Tworze klienta numer 3");
		EchoClient client3 = new EchoClient(3);
		Thread client3Thread = new Thread(client3);
		client3Thread.start();
		
		System.out.println("Tworze klienta numer 4");
		EchoClient client4 = new EchoClient(4);
		Thread client4Thread = new Thread(client4);
		client4Thread.start();
	}
}
