package gui;

import gui.myJFrame.PaintType;

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
	PaintType paintType; // Type of crossword
	LinkedList<JFormattedTextField> boardCells = new LinkedList<JFormattedTextField>(); // list
																						// of

	// text
	// fields
	// (board
	// cells)

	/**
	 * Draw Crossword
	 * 
	 * @param crossword
	 *            - crossword to draw
	 * @param paintType
	 *            - type of crossword
	 */
	public void drawCrossword(Crossword crossword, PaintType paintType) {
		this.crossword = crossword;
		this.paintType = paintType;
		setPreferredSize(new Dimension(1078, 2 * crossword.getBoard()
				.getHeight() * 30 + 60));
		revalidate();
		repaint();
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
			drawCrosswordContent(PaintType.SOLVED);
	}

	/**
	 * Draw board cells as text fields
	 */
	public void drawBoardCells() {
		Graphics2D g2d = (Graphics2D) this.getGraphics();
		for (int k = 0; k < boardCells.size(); k++)
			this.remove(boardCells.get(k));
		boardCells.removeAll(boardCells);
		g2d.setColor(Color.BLACK);
		for (int i = 0; i < crossword.getBoard().getHeight(); i++) {
			for (int j = 0; j < crossword.getBoard().getWidth(); j++) {
				if (crossword.getBoard().getCell(i, j).getContent() != null) {
					JFormattedTextField jftf = null;
					try {
						jftf = new JFormattedTextField(new MaskFormatter("U"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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

	/**
	 * Draw the content of every board cell
	 * 
	 * @param paintType
	 *            - type of crossword to paint
	 */
	public void drawCrosswordContent(PaintType paintType) {
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
						}
					}
				}
			}
		}
	}
}
