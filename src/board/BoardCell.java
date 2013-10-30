package board;

public class BoardCell implements Cloneable {
	public String content;
	public Boolean[][] abilities = new Boolean[2][3]; // )(Horiz/Vert)(Start/End/Inner)
	public final int HORIZ = 0;
	public final int VERT = 1;
	public final int START = 0;
	public final int END = 1;
	public final int INNER = 2;// true=enable, false =
						// disable
	
	
	public BoardCell(){
		content = null;
	}

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

	public void setAbility(int direction, int position, boolean ability) {
		abilities[direction][position] = ability;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}
