package board;

import dictionary.CwEntry.Direction;

/**
 * 
 * @author krzysztof
 * 
 */
public class BoardCell implements Cloneable {
	public String content; // letter in cell
	public Boolean[][] abilities; // [HORIZ/VERT][START/END/INNER]

	public enum Position {
		START, END, INNER
	}; // position - START if content of cell could be beginning of word etc.

	/**
	 * Constructor
	 */
	public BoardCell() {
		content = null;
		abilities = new Boolean[2][3];
	}

	public BoardCell clone() {
		BoardCell newBoardCell = new BoardCell();
		if (content != null)
			newBoardCell.content = new String(content);
		else
			newBoardCell.content = null;
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
	public void setAbility(Direction direction, Position position,
			boolean ability) {
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
