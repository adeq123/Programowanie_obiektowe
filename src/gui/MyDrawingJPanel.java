package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import board.Crossword;
import dictionary.CwEntry;
import dictionary.CwEntry.Direction;

/**
 * 
 * @author krzysztof
 * 
 */
public class MyDrawingJPanel extends JPanel {

	private static final long serialVersionUID = 1L; // Default serial version number
	Crossword crossword; // Crossword to draw

	/**
	 * 
	 * @author krzysztof
	 * 
	 */
	public enum PaintType {
		SOLVED, NOTSOLVED
	} // type of displayed crossword: solved or not solved

	PaintType paintType; // Type of displayed crossword
	LinkedList<JFormattedTextField> boardCells = new LinkedList<JFormattedTextField>(); // list of text fields (board cells)

	/**
	 * Setter
	 * 
	 * @param pt
	 *            - paint type
	 */
	public void setPaintType(PaintType pt) {
		this.paintType = pt;
	}

	/**
	 * Draw Crossword
	 * 
	 * @param crossword
	 *            - crossword to draw
	 * @throws ParseException
	 */
	public void drawCrossword(Crossword crossword) throws ParseException {
		this.crossword = crossword;
		setPreferredSize(new Dimension(1078, crossword.getBoard().getHeight() * 30 + 60 + crossword.getEntries().size() * 30));
		revalidate();
		repaint();
		createBoardCells();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		if (crossword != null) {
			CwEntry temp;
			Iterator<CwEntry> it = crossword.getROEntryIter();
			int k = 0;
			int s = 0;
			if (crossword.getStrategy().getClass().getName().equals("board.EasytStrategy") == true) {
				s = 1;
				it.next();
			}
			while (it.hasNext() == true) {
				temp = it.next();
				if (temp.getDir() == Direction.HORIZ) {
					if (k + 1 < 10)
						g2.drawString(String.valueOf(k + 1) + " ", temp.getY() * 30 + 30, temp.getX() * 30 + 50);
					else
						g2.drawString(String.valueOf(k + 1) + " ", temp.getY() * 30 + 22, temp.getX() * 30 + 50);

				} else {
					if (k + 1 < 10)
						g2.drawString(String.valueOf(k + 1) + " ", temp.getY() * 30 + 52, temp.getX() * 30 + 26);
					else
						g2.drawString(String.valueOf(k + 1) + " ", temp.getY() * 30 + 48, temp.getX() * 30 + 26);

				}
				g2.drawString(String.valueOf(k + 1) + ".", 20, k * 30 + 70 + crossword.getBoard().getHeight() * 30);
				g2.drawString(crossword.getEntries().get(s).getClue(), 40, k * 30 + 70 + crossword.getBoard().getHeight() * 30);
				k++;
				s++;
			}
			for (int i = 0; i < crossword.getBoard().getHeight(); i++) {
				for (int j = 0; j < crossword.getBoard().getWidth(); j++) {
					if (crossword.getBoard().getCell(i, j).getContent() != null) {
						g2.setColor(Color.BLACK);
						g2.drawRect(40 + j * 30, i * 30 + 30, 30, 30);
					}
				}
			}
			if (paintType == PaintType.SOLVED)
				printSolvedCrossword();
		}
	}

	/**
	 * Draw content of every board cell of solved crossword
	 */
	public void printSolvedCrossword() {
		if (crossword != null) {
			Iterator<JFormattedTextField> iter = boardCells.iterator();
			for (int i = 0; i < crossword.getBoard().getHeight(); i++) {
				for (int j = 0; j < crossword.getBoard().getWidth(); j++) {
					if (crossword.getBoard().getCell(i, j).getContent() != null) {
						{
							JFormattedTextField actualCell = iter.next();
							if (actualCell.getText().equalsIgnoreCase(crossword.getBoard().getCell(i, j).getContent()) == false) {
								actualCell.setForeground(Color.RED);
								actualCell.setText(crossword.getBoard().getCell(i, j).content);
								actualCell.setEditable(false);
							}
							if (j == 0 && crossword.getStrategy().getClass().getName().equals("board.EasytStrategy") == true && paintType == PaintType.SOLVED)
								actualCell.setForeground(Color.GREEN);
						}
					}
				}
			}
		}
	}

	/**
	 * Draw empty content of every board cell of crossword
	 */
	public void printNotSolvedcrossword() {
		if (crossword != null) {
			Iterator<JFormattedTextField> iter = boardCells.iterator();
			for (int i = 0; i < crossword.getBoard().getHeight(); i++) {
				for (int j = 0; j < crossword.getBoard().getWidth(); j++) {
					if (crossword.getBoard().getCell(i, j).getContent() != null) {
						{
							JFormattedTextField actualCell = iter.next();
							actualCell.setForeground(Color.BLACK);
							actualCell.setValue(null);
							actualCell.setEditable(true);
						}
					}
				}
			}
		}
	}

	/**
	 * Creates board cells as text fields
	 */
	public void createBoardCells() {
		for (int k = 0; k < boardCells.size(); k++)
			this.remove(boardCells.get(k));
		boardCells.removeAll(boardCells);
		for (int i = 0; i < crossword.getBoard().getHeight(); i++) {
			for (int j = 0; j < crossword.getBoard().getWidth(); j++) {
				if (crossword.getBoard().getCell(i, j).getContent() != null) {
					JFormattedTextField jftf = null;
					try {
						jftf = new JFormattedTextField(new MaskFormatter("U"));
					} catch (ParseException e) {
					}
					jftf.setBounds(41 + j * 30, i * 30 + 31, 28, 28);
					jftf.setHorizontalAlignment(JTextField.CENTER);
					boardCells.add(jftf);
				}
			}
		}
		for (int k = 0; k < boardCells.size(); k++)
			this.add(boardCells.get(k));
	}

}