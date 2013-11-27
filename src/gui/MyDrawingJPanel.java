package gui;

import gui.myJFrame.PaintType;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

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
		if (crossword != null) {
			Crossword cw = crossword;
			for (int i = 0; i < cw.getBoard().getHeight(); i++) {
				g2.drawString(String.valueOf(i + 1) + " ", 20, 50 + i * 30);
				g2.drawString(String.valueOf(i + 1) + ".", 20, 70 + i * 30 + 30
						* cw.getBoard().getHeight());
				for (int j = 0; j < cw.getBoard().getWidth(); j++) {
					if (cw.getBoard().getCell(i, j).content != null) {
						if (paintType == PaintType.SOLVED)
							g2.drawString(cw.getBoard().getCell(i, j)
									.getContent(), 40 + j * 30 + 10,
									15 + 30 * i + 35);
						g2.setColor(Color.BLACK);
						g2.drawRect(40 + j * 30, i * 30 + 30, 30, 30);
						g2.drawString(cw.getEntries().get(i + 1).getClue(), 40,
								70 + 30 * i + 30 * cw.getBoard().getHeight());
					}
				}
			}
		}
	}
}
