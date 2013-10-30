package board;

import java.util.LinkedList;

public class Board implements Cloneable {

	public BoardCell[][] board;
	int height;
	int width;

	public Board clone() {
		Board newBoard = new Board(height, width);
		newBoard.height = height;
		newBoard.width = width;
		newBoard.board = board.clone();
		return newBoard;

	}

	public Board(int width, int height) {
		board = new BoardCell[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				board[i][j] = new BoardCell();
			}
		}
		this.height = height;
		this.width = width;

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BoardCell getCell(int x, int y) {
		return board[x][y];
	}

	public void setCell(int x, int y, BoardCell c) {
		board[x][y] = c;
	}

	public LinkedList<BoardCell> getStartCells() {
		LinkedList<BoardCell> startBoardCelllinkedList = new LinkedList<BoardCell>();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (board[i][j].abilities[0][0] == true
						|| board[i][j].abilities[1][0] == true) {
					startBoardCelllinkedList.add(board[i][j]);
				}

			}
		}

		return startBoardCelllinkedList;
	}

	// a nie powinna zwracac Pattern??
	public String createPattern(int fromy, int fromx, int toy, int tox) {
		char[] tableOfChar = new char[(((toy - fromy) > 0) ? (toy - fromy)
				: (tox - fromx))];
		// System.out.println()
		if (fromx == tox) {
			for (int i = 0; i < (toy - fromy); i++) {
				if (board[fromx][fromy + i].content != null) {
					tableOfChar[i] = board[fromx][fromy + i].content.charAt(0);
				} else {
					System.out.println("K");
					tableOfChar[i] = '.';
					tableOfChar[i + 1] = '*';
					break;
				}
			}

		} else if (fromy == toy) {
			for (int i = 0; i < (tox - fromx); i++) {
				System.out.println(board[fromx + i][fromy].content + "QQQQQ");
				if (board[0][fromy + i].content != null) {
					System.out.println("!");
					tableOfChar[i] = board[fromx][fromy + i].content.charAt(0);
				} else {
					System.out.println(".");
					tableOfChar[i] = '.';
					tableOfChar[i + 1] = '*';
					break;
				}
			}
		}
		if (tableOfChar[0] == '.')
			return null;
		String nowy = new String(tableOfChar, 0, 3);
		//	return null;
		// Pattern newPattern = Pattern.compile(nowy);
		// return newPattern;
		return nowy;
	}

}
