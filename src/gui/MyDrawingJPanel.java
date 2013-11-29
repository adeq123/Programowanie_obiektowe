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

/**
 * 
 * @author krzysztof
 * 
 */
public class MyDrawingJPanel extends JPanel {

	private static final long serialVersionUID = 1L; // Default serial version
														// number
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
	LinkedList<JFormattedTextField> boardCells = new LinkedList<JFormattedTextField>(); // list
																						// of
																						// text
																						// fields
																						// (board
																						// cells)

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
		setPreferredSize(new Dimension(1078, 2 * crossword.getBoard()
				.getHeight() * 30 + 60));
		revalidate();
		repaint();
		drawBoardCells();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		// zmien to, przeciez crossword zawsze != nul!!
		if (crossword != null) {
			Crossword cw = crossword;
			for (int i = 0; i < cw.getBoard().getHeight(); i++) {
				g2.drawString(String.valueOf(i + 1) + " ", 20, 50 + i * 30);
				g2.drawString(String.valueOf(i + 1) + ".", 20, 70 + i * 30 + 30
						* cw.getBoard().getHeight());
				for (int j = 0; j < cw.getBoard().getWidth(); j++) {
					if (cw.getBoard().getCell(i, j).getContent() != null) {
						g2.setColor(Color.BLACK);
						g2.drawRect(40 + j * 30, i * 30 + 30, 30, 30);
						g2.drawString(cw.getEntries().get(i + 1).getClue(), 40,
								70 + 30 * i + 30 * cw.getBoard().getHeight());
					}
				}
			}
		}
		if (paintType == PaintType.SOLVED)
			drawCrosswordContent();
	}

	/**
	 * Draw board cells as text fields
	 * 
	 * @throws ParseException
	 */
	public void drawBoardCells() throws ParseException {
		Graphics2D g2d = (Graphics2D) this.getGraphics();
		for (int k = 0; k < boardCells.size(); k++)
			this.remove(boardCells.get(k));
		boardCells.removeAll(boardCells);
		g2d.setColor(Color.BLACK);
		for (int i = 0; i < crossword.getBoard().getHeight(); i++) {
			for (int j = 0; j < crossword.getBoard().getWidth(); j++) {
				if (crossword.getBoard().getCell(i, j).getContent() != null) {
					JFormattedTextField jftf = null;
					jftf = new JFormattedTextField(new MaskFormatter("U"));
					jftf.setBounds(41 + j * 30, i * 30 + 31, 28, 28);
					jftf.setHorizontalAlignment(JTextField.CENTER);
					boardCells.add(jftf);
				}
			}
		}
		for (int k = 0; k < boardCells.size(); k++)
			this.add(boardCells.get(k));
	}

	/**
	 * Draw the content of every board cell
	 * 
	 * @param paintType
	 *            - type of crossword to paint
	 */
	public void drawCrosswordContent() {
		if (crossword != null) {
			Iterator<JFormattedTextField> iter = boardCells.iterator();
			for (int i = 0; i < crossword.getBoard().getHeight(); i++) {
				for (int j = 0; j < crossword.getBoard().getWidth(); j++) {
					if (crossword.getBoard().getCell(i, j).getContent() != null) {
						{
							JFormattedTextField actualCell = iter.next();
							if (paintType == PaintType.SOLVED) {
								if (actualCell.getText().equalsIgnoreCase(
										crossword.getBoard().getCell(i, j)
												.getContent()) == false) {
									actualCell.setForeground(Color.RED);
									actualCell.setText(crossword.getBoard()
											.getCell(i, j).content);
									actualCell.setEditable(false);
								}
							} else {
								actualCell.setForeground(Color.BLACK);
								actualCell.setValue(null);
								actualCell.setEditable(true);
							}
							if (j == 0)
								actualCell.setForeground(Color.GREEN);
						}
					}
				}
			}
		}
	}
}
