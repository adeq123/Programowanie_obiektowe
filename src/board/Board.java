package board;

import java.util.LinkedList;
import java.util.regex.Pattern;

public class Board implements Cloneable{

	private BoardCell[][] board;
	int height;
	int width;

	public Board clone(){
		Board newBoard = new Board(height,width);
		newBoard.height = height;
		newBoard.width = width;
		newBoard.board = board.clone();
		return newBoard;
		
	}
	
	public Board(int width, int height) {
		board = new BoardCell[width][height];
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
	public Pattern createPattern(int fromx, int fromy, int tox, int toy) {
		char[] tableOfChar = new char[(((toy - fromy) > 0) ? (toy - fromy)
				: (tox - fromx))];

		if (fromx == tox) {
			for (int i = 0; i < (toy - fromy); i++) {
				if (board[fromx][fromy + i].content != null)
				{
					System.out.println( board[fromx + i][fromy].content.charAt(0));
					
					tableOfChar[i] = board[fromx][fromy + i].content.charAt(0);
				}
				else
				{
					System.out.println("K");
					tableOfChar[i] = '.';}
			}

		} else if (fromy == toy) {
			for (int i = 0; i < (tox - fromx); i++) {
				if (board[fromx + i][fromy].content != null)
				{
					System.out.println("K");
					tableOfChar[i] = board[fromx + i][fromy].content.charAt(0);
				}
				else
				{
					System.out.println("K");
					tableOfChar[i] = '.';
				}
			}
		}
System.out.println(tableOfChar.toString());
		Pattern newPattern = Pattern.compile(tableOfChar.toString());
		return newPattern;
	}
	
}
