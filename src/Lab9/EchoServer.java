package Lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class EchoServer extends Thread {

	public final int limit = 2;
	public int amountOfClients = 0;
	public ServerSocket serverSocket = null;
	public LinkedList<Socket> waitingClients = new LinkedList<Socket>();

	public static void main(String[] args) {

		new EchoServer().start();

	}

	private class StartAndListen extends Thread {

		Socket clientSocket = null;

		public void run() {
			try {
				serverSocket = new ServerSocket(6666);
				System.out.println("Serwer startuje");
				while (true) {
					clientSocket = serverSocket.accept();
					synchronized (waitingClients) {
						waitingClients.add(clientSocket);
					}
				}
			} catch (IOException e) {
				System.out.println("Could not listen on port: 6666");
				System.exit(-1);
			}

		}
	}

	private class SwitchClient extends Thread {

		private Socket clientSocket;

		public SwitchClient(Socket clientSocket) {

			synchronized (this) {
				this.clientSocket = clientSocket;
			}

		}

		public void run() {
			PrintWriter out;
			try {
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String inputLine;

				while (!(inputLine = in.readLine()).equals("KONIEC")) {
					System.out.println("Serwer otrzymal wiadomosc: " + inputLine);
					out.println("Serwer: Obsluguje klienta numer: " + inputLine.split(" ")[2]);
				}

				synchronized (this) {
					out.close();
					in.close();
					clientSocket.close();
					amountOfClients--;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {

		new StartAndListen().start();

		while (true) {
			synchronized (this) {
				if (amountOfClients < limit && waitingClients.size() > 0) {
					System.out.println("Nowe polaczenie");
					new SwitchClient(waitingClients.get(0)).start();
					waitingClients.remove(waitingClients.get(0));
					amountOfClients++;
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}