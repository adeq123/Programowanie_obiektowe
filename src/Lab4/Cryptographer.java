package Lab4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cryptographer {
	public static void cryptfile(String filename, String output, Algorythm algorythm)
			throws IOException {
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		FileWriter fileWriter = new FileWriter(output);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		try {
			String word = null;
			while ((word = bufferedReader.readLine()) != null) {
				bufferedWriter.write(algorythm.crypt(word));
				bufferedWriter.newLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null)
				bufferedReader.close();
			if (bufferedWriter != null)
				bufferedWriter.close();
		}
		System.out.println("Plik zaszyfrowany: " + output);
	}

	public static void decryptfile(String filename, String output, Algorythm algorythm)
			throws IOException {
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		FileWriter fileWriter = new FileWriter(output);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		try {
			String word = null;
			while ((word = bufferedReader.readLine()) != null) {
				bufferedWriter.write(algorythm.decrypt(word));
				bufferedWriter.newLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null)
				bufferedReader.close();
			if (bufferedWriter != null)
				bufferedWriter.close();
		}
		System.out.println("Plik odszyfrowany: " + output);
	}
}
