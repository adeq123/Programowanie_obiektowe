package Lab5;

import java.io.IOException;

/**
 * 
 * @author krzysztof
 * 
 */
public class Main {

	/**
	 * 
	 * @param args
	 *            - program arguments
	 */
	public static void main(String[] args) {
		SubtitlesDelayer delayer = new SubtitlesDelayer(args[0], args[1],
				Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		try {
			delayer.delay();
		} catch (IndexOutOfBoundsException a) {
			System.out
					.println("Wrong program arguments (try: input file, output file, delay, framerate)");
		} catch (IOException e) {
			System.out.println("Error when open files");
		} catch (UnproperSequenceOfCharsException e) {
			System.out.println(e.getMessage());
		}
	}
}
