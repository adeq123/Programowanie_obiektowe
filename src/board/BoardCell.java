package board;

/**
 * 
 * @author krzysztof
 * 
 */
public class BoardCell implements Cloneable {
	public String content; // letter in cell
	public Boolean[][] abilities; // [HORIZ/VERT][START/END/INNER]

	public enum Direction {
		HORIZ, VERT
	}; // direction - HORIZ if word is horizontal, SECOND - vertical
		// public final static int HORIZ = 0; // horizontal
		// public final static int VERT = 1; // vertical
	public enum Position {
		START, END, INNER
	}; // position - START if content of cell could be beginning of word etc.
	// public final static int START = 0; // beginning of word
	// public final static int END = 1; // ending of word
	// public final static int INNER = 2; // middle of word

	/**
	 * Constructor
	 */
	public BoardCell() {
		content = null;
		abilities = new Boolean[2][3];
	}

	/**
	 * Clones BoardCell
	 * 
	 * return clone of BoardCell
	 */
	public BoardCell clone() {
		BoardCell newBoardCell = new BoardCell();
		newBoardCell.content = new String(content);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				newBoardCell.abilities[i][j] = abilities[i][j];
			}
		}
		return newBoardCell;
	}

	/**
	 * Sets abilities of cell
	 * 
	 * @param direction
	 *            - direction
	 * @param position
	 *            - position
	 * @param ability
	 *            - ability
	 */
	public void setAbility(Direction direction, Position position, boolean ability) {
		abilities[direction.ordinal()][position.ordinal()] = ability;
	}

	/**
	 * Setter
	 * 
	 * @param content
	 *            - content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Getter
	 * 
	 * @return content of cell (String)
	 */
	public String getContent() {
		return content;
	}
}
