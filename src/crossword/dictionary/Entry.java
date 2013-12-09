package crossword.dictionary;

/**
 * 
 * @author krzysztof
 * 
 */
public class Entry {
	private String word; // word
	private String clue; // clue

	/**
	 * Constructor
	 * 
	 * @param word
	 *            - word
	 * @param clue
	 *            - clue
	 */
	public Entry(String word, String clue) {
		this.word = word;
		this.clue = clue;
	}

	/**
	 * Getter
	 * 
	 * @return word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Getter
	 * 
	 * @return clue
	 */
	public String getClue() {
		return clue;
	}
	
	public String toString(){
		return "(" + word + " " + clue + ")";
	}
}
