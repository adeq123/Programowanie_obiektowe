package dictionary;

/**
 * 
 * @author krzysztof
 * 
 */
public class CwEntry extends Entry {

	/**
	 * 
	 * @author krzysztof
	 * 
	 */
	public enum Direction {
		HORIZ, VERT
	}; // possibilities - HORIZ if word is horizontal, VERT - vertical

	private int x; // position in x-axe
	private int y; // position in y-axe
	private Direction d; // direction

	/**
	 * Constructor
	 * 
	 * @param word
	 *            - word
	 * @param clue
	 *            - clue
	 * @param x
	 *            - position x
	 * @param y
	 *            - position y
	 * @param d
	 *            - direction
	 */
	public CwEntry(String word, String clue, int x, int y, Direction d) {
		super(word, clue);
		this.x = x;
		this.y = y;
		this.d = d;
	}

	/**
	 * Getter
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Getter
	 * 
	 * @return direction
	 */
	public Direction getDir() {
		return d;
	}

	public String toString() {
		return getDir().toString() + " " + getX() + " " + getY() + " "
				+ getWord() + "\n" + getClue();
	}
}
