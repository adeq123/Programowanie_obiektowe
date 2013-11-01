package Lab4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class Cryptographer {
	public static void cryptfile(String filename, Algorythm algorythm)
			throws IOException {
		LinkedList<String> contentOfFile = new LinkedList<String>();
		LinkedList<String> contentOfCryptfile = new LinkedList<String>();

		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		try {
			String word = null;
			while ((word = bufferedReader.readLine()) != null)
				contentOfFile.add(word);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null)
				bufferedReader.close();
		}
		
		Iterator<String> it = contentOfFile.iterator();
		while (it.hasNext() == true) {
			contentOfCryptfile.add(algorythm.crypt(it.next()));
		}

		FileWriter fileWriter = new FileWriter("zaszyfrowane.txt");
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		try {
			Iterator<String> iter = contentOfCryptfile.iterator();
			while (iter.hasNext() == true) {
				bufferedWriter.write(iter.next());
				bufferedWriter.newLine();
			}
		} finally {
			if (bufferedWriter != null)
				bufferedWriter.close();
		}
		System.out.println("Plik zaszyfrowany: zaszyfrowane.txt");
	}

	public static void decryptfile(String filename, Algorythm algorythm) 
		throws IOException {
			LinkedList<String> contentOfFile = new LinkedList<String>();
			LinkedList<String> contentOfCryptfile = new LinkedList<String>();

			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			try {
				String word = null;
				while ((word = bufferedReader.readLine()) != null)
					contentOfFile.add(word);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (bufferedReader != null)
					bufferedReader.close();
			}
			
			Iterator<String> it = contentOfFile.iterator();
			while (it.hasNext() == true) {
				contentOfCryptfile.add(algorythm.decrypt(it.next()));
			}

			FileWriter fileWriter = new FileWriter("odszyfrowane.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			try {
				Iterator<String> iter = contentOfCryptfile.iterator();
				while (iter.hasNext() == true) {
					bufferedWriter.write(iter.next());
					bufferedWriter.newLine();
				}
			} finally {
				if (bufferedWriter != null)
					bufferedWriter.close();
			}
			System.out.println("Plik odszyfrowany: odszyfrowane.txt");
		}		
}
