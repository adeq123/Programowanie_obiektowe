package Lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JIn {

	public static String getString() {
		String text = null;
		try {
			InputStreamReader rd = new InputStreamReader(System.in);
			BufferedReader bfr = new BufferedReader(rd);
			text = bfr.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

	public static int getInt() {
		int a = 0;
		try {
			InputStreamReader rd = new InputStreamReader(System.in);
			BufferedReader bfr = new BufferedReader(rd);
			a = Integer.parseInt(bfr.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	public static double getDouble() {
		double d = 0.;
		try {
			InputStreamReader rd = new InputStreamReader(System.in);
			BufferedReader bfr = new BufferedReader(rd);
			d = Double.parseDouble(bfr.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return d;
	}
}
