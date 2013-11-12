package Lab5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 
 * @author krzysztof
 * 
 */
public class SubtitlesDelayer {
	String in; // input file
	String out; // output file
	int delay; // delay
	int fps; // fps

	/**
	 * Constructor
	 * 
	 * @param in
	 *            - input file
	 * @param out
	 *            - output file
	 * @param delay
	 *            - delay
	 * @param fps
	 *            - framerate
	 */
	public SubtitlesDelayer(String in, String out, int delay, int fps) {
		this.in = in;
		this.out = out;
		this.delay = delay;
		this.fps = fps;
	}

	/**
	 * Method that delays the subtitles
	 * 
	 * @throws IOException
	 * @throws UnproperSequenceOfCharsException
	 */
	public void delay() throws IOException, UnproperSequenceOfCharsException {
		FileReader fr = new FileReader(in);
		BufferedReader bf = new BufferedReader(fr);
		FileWriter fw = new FileWriter(out);
		BufferedWriter bw = new BufferedWriter(fw);
		String line = null;
		int begin;
		int end;

		try {
			while ((line = bf.readLine()) != null) {
				String[] splitedLine = line.split("\\}");

				if ((Pattern.matches("\\{[[0-9]]*", splitedLine[0])) == true
						&& (Pattern.matches("\\{[[0-9]]*", splitedLine[1]))) {
					begin = Integer.parseInt(splitedLine[0].split("\\{")[1]);
					end = Integer.parseInt(splitedLine[1].split("\\{")[1]);
					if (begin > end) {
						throw new UnproperSequenceOfCharsException(
								"Begining time of subtitles cannot be further than the end");
					}
					begin += fps + delay; // jakie wzory?!
					end += fps + delay;
					bw.write("{" + String.valueOf(begin) + "}{"
							+ String.valueOf(end) + "}" + splitedLine[2]);
					bw.newLine();
				} else {
					throw new UnproperSequenceOfCharsException(
							"Unproper format of subtitles");
				}
			}
		} finally {
			if (bf != null)
				bf.close();
			if (bw != null)
				bw.close();
		}
	}
}
