package Lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient extends Thread {

	public final int number;

	public static void main(String[] args) throws IOException {

		System.out.println("Zaczynamy");

		System.out.println("Tworze klienta numer 1");
		new EchoClient(1).start();

		System.out.println("Tworze klienta numer 2");
		new EchoClient(2).start();

		System.out.println("Tworze klienta numer 3");
		new EchoClient(3).start();

		System.out.println("Tworze klienta numer 4");
		new EchoClient(4).start();
	}

	public EchoClient(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void run() {

		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			echoSocket = new Socket("localhost", 6666);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: localhost.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for " + "the connection to: localhost.");
			System.exit(1);
		}

		try {
			for (int i = 1; i < 6 + number * 2; i++) {
				out.println("Klient numer: " + number + " z zapytaniem numer: " + i);
				System.out.println("Klient numer: " + number + " otrzymal wiadomosc: " +in.readLine());
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			out.print("KONIEC");
			System.out.println("Klient numer " + number + " konczy dzialanie");
			out.close();
			in.close();
			echoSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
