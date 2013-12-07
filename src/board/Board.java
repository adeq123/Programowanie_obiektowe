package board;

import java.util.LinkedList;

import board.BoardCell.Position;
import dictionary.CwEntry.Direction;

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

	public Board clone() {
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
				 if
				 (board[i][j].abilities[Direction.HORIZ.ordinal()][Position.START
				 .ordinal()] == true
				 ||
				 board[i][j].abilities[Direction.VERT.ordinal()][Position.START
				 .ordinal()] == true) {
				 startBoardCelllinkedList.add(board[i][j]);
				 }
		/*		if (board[i][j].abilities[Direction.VERT.ordinal()][Position.START
						.ordinal()] == true) {
					startBoardCelllinkedList.add(board[i][j]);
				//	System.out.println("VERT");
				}
				if (board[i][j].abilities[Direction.HORIZ.ordinal()][Position.START
				                             						.ordinal()] == true) {
					//System.out.println("HORIZ");
					startBoardCelllinkedList.add(board[i][j]);
				                             				}
*/
			}
		}
		return startBoardCelllinkedList;
	}

	/**
	 * Creates list of BoardCells that can be endings of words
	 * 
	 * @return list of BoardCells
	 */
	public LinkedList<BoardCell> getEndCells(int x, int y, Direction dir) {
		LinkedList<BoardCell> endBoardCelllinkedList = new LinkedList<BoardCell>();
		if (dir == Direction.HORIZ) {
			y++;
			for (; y < width; y++) {
				if (board[x][y].abilities[dir.ordinal()][Position.END.ordinal()] == true) {
					endBoardCelllinkedList.add(board[x][y]);
				}
			}
		} else {
			x++;
			for (; x < height; x++) {
				if (board[x][y].abilities[dir.ordinal()][Position.END.ordinal()] == true) {
					endBoardCelllinkedList.add(board[x][y]);
				}
			}
		}
		return endBoardCelllinkedList;
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
		//System.out.println(fromx + " " + fromy + " " + tox + " " + toy);
		char[] tableOfChar = new char[(((toy - fromy) > 0) ? (toy - fromy + 1)
				: (tox - fromx + 1))];
		if (fromx == tox) {
			for (int i = fromy; i < toy + 1; i++) {
				if (board[fromx][i].content != null)
					tableOfChar[i - fromy] = board[fromx][i].content.charAt(0);
				else
					tableOfChar[i - fromy] = '.';
			}
		} else if (fromy == toy) {
			for (int i = fromx; i < tox + 1; i++) {
				if (board[i][fromy].content != null) {
					tableOfChar[i - fromx] = board[i][fromy].content.charAt(0);
				} else
					tableOfChar[i - fromx] = '.';
			}
		}
		return new String(tableOfChar, 0, tableOfChar.length);
	}

	/**
	 * Checks if Board has only empty cells
	 * 
	 * @return true if is empty, false otherwise
	 */
	public boolean isEmpty() {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				if (board[i][j].getContent() != null)
					return false;
			}
		return true;
	}

	/**
	 * Gives horizontal position of board cell
	 * 
	 * @param bc
	 *            - boardCell
	 * @return horizontal position
	 */
	public int getHorizPosition(BoardCell bc) {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				if (bc == board[i][j])
					return j;
			}
		return -1;
	}

	/**
	 * Gives vertical position of board cell
	 * 
	 * @param bc
	 *            - boardCell
	 * @return vertical position
	 */
	public int getVertPosition(BoardCell bc) {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				if (bc == board[i][j])
					return i;
			}
		return -1;
	}

	/*
	 * public static void main(String[] args){ Board b = new Board(5,5);
	 * BoardCell bc = b.getCell(2, 3);
	 * System.out.println(b.getHorizPosition(bc)); }
	 */
}