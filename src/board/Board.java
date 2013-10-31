package board;

import java.util.LinkedList;

import board.BoardCell.Direction;
import board.BoardCell.Position;

/**
 * 
 * @author krzysztof
 * 
 */
public class Board implements Cloneable {

	private BoardCell[][] board; // two-dimensional table of BoardCells
	private int height; // height of board
	private int width; // width of board

	/**
	 * Getter
	 * 
	 * @return board
	 */
	public BoardCell[][] getBoard() {
		return board;
	}

	/**
	 * Setter
	 * 
	 * @param board
	 */
	public void setBoard(BoardCell[][] board) {
		this.board = board;
	}

	/**
	 * Setter
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Setter
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Clones board
	 * 
	 * @return clone of board
	 */
	public Board clone() {
		// TO DO Popraw!
		Board newBoard = new Board(height, width);
		newBoard.height = height;
		newBoard.width = width;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				newBoard.board[i][j] = board[i][j].clone();
			}
		}
		return newBoard;
	}

	/**
	 * Constructor
	 * 
	 * @param height
	 *            - height
	 * @param width
	 *            - width
	 */
	public Board(int height, int width) {
		board = new BoardCell[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				board[i][j] = new BoardCell();
			}
		}
		this.height = height;
		this.width = width;
	}

	/**
	 * Getter
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Getter
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns cell on x and y position
	 * 
	 * @param x
	 *            - x position
	 * @param y
	 *            - y position
	 * @return BoardCell
	 */
	public BoardCell getCell(int x, int y) {
		return board[x][y];
	}

	/**
	 * Sets cell
	 * 
	 * @param x
	 *            - x position
	 * @param y
	 *            - y position
	 * @param c
	 *            - BoardCell to set
	 */
	public void setCell(int x, int y, BoardCell c) {
		board[x][y] = c;
	}

	/**
	 * Creates list of BoardCells that can be beginnings of words
	 * 
	 * @return list of BoardCells
	 */
	public LinkedList<BoardCell> getStartCells() {
		LinkedList<BoardCell> startBoardCelllinkedList = new LinkedList<BoardCell>();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (board[i][j].abilities[Direction.HORIZ.ordinal()][Position.START
						.ordinal()] == true
						|| board[i][j].abilities[Direction.VERT.ordinal()][Position.START
								.ordinal()] == true) {
					startBoardCelllinkedList.add(board[i][j]);
				}

			}
		}
		return startBoardCelllinkedList;
	}

	/**
	 * Creates pattern
	 * 
	 * @param fromx
	 *            - position of beginning of x
	 * @param fromy
	 *            - position of beginning of y
	 * @param tox
	 *            - position of ending of x
	 * @param toy
	 *            - position of ending of y
	 * @return pattern (String) 
	 */
	public String createPattern(int fromx, int fromy, int tox, int toy) {
		char[] tableOfChar = new char[(((toy - fromy) > 0) ? (toy - fromy)
				: (tox - fromx))];
		if (fromx == tox) {
			for (int i = fromy; i < toy; i++) {
					if (board[fromx][fromy + i].content != null)
						tableOfChar[i] = board[fromx][fromy + i].content
								.charAt(0);
					else
						tableOfChar[i] = '.';
			}
		} else if (fromy == toy) {
			for (int i = fromx; i < tox; i++) {
				if (board[fromx + i][fromy].content != null)
					tableOfChar[i] = board[fromx + i][fromy].content.charAt(0);
				else
					tableOfChar[i] = '.';
			}
		}
		return new String(tableOfChar, 0, tableOfChar.length);
	}
}