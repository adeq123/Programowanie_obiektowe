package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import board.ConcretStrategy;
import board.Crossword;
import browse.CwBrowser;
import dictionary.CwEntry;
import exceptions.noPossibilityToGenerateCrosswordException;
import exceptions.wrongCrosswordDimensionsException;

public class myJFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel firstPanel;
	private JLabel heightLabel;
	private JSpinner heightSpinner;
	private JLabel widthLabel;
	private JSpinner widthSpinner;
	private JButton generateButton;

	private JPanel secondPanel;
	private JTextField pathTextField;
	private JButton loadButton;
	private JFileChooser fileChooser;
	private JButton threeDotsButton;
	private File currentDirectory;
	private int actualIndexOfCrossword = 0;
	private JButton previousButton;
	private JButton nextButton;

	private JPanel thirdPanel;
	private JButton solveButton;
	private JButton saveButton;
	private JButton printButton;
	private myDrawingJPanel drawingPanel;
	private JScrollPane scrollPanel;

	private Crossword crossword;
	private ConcretStrategy strategy;
	private int height;
	private int width;
	private CwBrowser cwbrowser = null;
	boolean lol = false;

	public enum PaintType{
		SOLVED, NOTSOLVED
	}
	
	PaintType paintType = PaintType.NOTSOLVED;
	private JRadioButton rdbtnNotSolved;
	private JRadioButton rdbtnSolved;
	
	public void drawProper(PaintType pt){
		paintType = pt;
		drawingPanel.repaint();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myJFrame frame = new myJFrame();
					frame.setTitle("Crossword's Program");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public myJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1028, 652);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("ComboBox.border"));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		firstPanel = new JPanel();
		firstPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "New crossword",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		firstPanel.setBounds(10, 11, 266, 112);
		contentPane.add(firstPanel);
		firstPanel.setLayout(null);

		heightLabel = new JLabel("Height");
		heightLabel.setBounds(20, 27, 64, 25);
		firstPanel.add(heightLabel);

		heightSpinner = new JSpinner();
		heightSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				height = ((Number) heightSpinner.getValue()).intValue();
			}
		});
		heightSpinner.setBounds(83, 27, 34, 25);
		firstPanel.add(heightSpinner);

		widthLabel = new JLabel("Width");
		widthLabel.setBounds(150, 27, 64, 25);
		firstPanel.add(widthLabel);

		widthSpinner = new JSpinner();
		widthSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				width = ((Number) widthSpinner.getValue()).intValue();
			}
		});
		widthSpinner.setBounds(212, 27, 34, 25);
		firstPanel.add(widthSpinner);

		// ZMIEN TO!!!!!!!!!!!!!!
		generateButton = new JButton("Generate");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					crossword = new Crossword(height, width, "tak.txt");
				} catch (wrongCrosswordDimensionsException e) {
					JOptionPane.showMessageDialog(myJFrame.this,
							"The dimensions of crossword are wrong!");
				}
				ConcretStrategy s = new ConcretStrategy();
				try {
					crossword.generate(s);
				} catch (noPossibilityToGenerateCrosswordException e) {
					// ZROb COS Z TM!!!!!!!!
					e.printStackTrace();
				}
				drawCrossword(crossword);
			}
		});
		generateButton.setBounds(73, 66, 110, 25);
		firstPanel.add(generateButton);

		secondPanel = new JPanel();
		secondPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "From file",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		secondPanel.setBounds(285, 11, 370, 112);
		contentPane.add(secondPanel);
		secondPanel.setLayout(null);

		pathTextField = new JTextField("Path...");
		pathTextField.setBounds(20, 27, 146, 25);
		secondPanel.add(pathTextField);
		pathTextField.setColumns(10);

		threeDotsButton = new JButton("...");
		threeDotsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = fileChooser.showDialog(myJFrame.this,
						"Approve directory");
				if (option == JFileChooser.APPROVE_OPTION) {
					currentDirectory = fileChooser.getSelectedFile();
					pathTextField.setText(currentDirectory.getAbsolutePath());
				}
			}
		});
		threeDotsButton.setBounds(168, 27, 39, 25);
		secondPanel.add(threeDotsButton);

		loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentDirectory.getAbsolutePath() != null) {
					cwbrowser = new CwBrowser(currentDirectory
							.getAbsolutePath());
					cwbrowser.browseCrosswords();

					try {
						cwbrowser.loadCrosswords();
						// zrob wyjatek ze jak nie ma ani jednej krzyzowki,a lbo
						// sa inne zle pliki...

						if (cwbrowser.crosswordsList.size() > 0) {
							actualIndexOfCrossword = 0;
							crossword = cwbrowser.crosswordsList
									.get(actualIndexOfCrossword);
							drawCrossword(crossword);
						} else {
							JOptionPane.showMessageDialog(myJFrame.this,
									"Any crossword was load");
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
						// TO DO ZROB COS z TYM!!!!!!!!!!!
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(myJFrame.this,
								"There is not such directory!");
					} catch (IOException e) {
						JOptionPane
								.showMessageDialog(myJFrame.this,
										"Sorry, there is a problem with loading crosswords from directory");
					}

					// jezeli jest pusto to jdialog nie ma tam krzyzowek!
				}
			}
		});
		loadButton.setBounds(240, 27, 110, 25);
		secondPanel.add(loadButton);

		previousButton = new JButton("Previous");
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cwbrowser != null && cwbrowser.crosswordsList.size() > 0) {
					actualIndexOfCrossword--;
					if (actualIndexOfCrossword < 0)
						actualIndexOfCrossword = actualIndexOfCrossword
								+ cwbrowser.crosswordsList.size();
					System.out.println(actualIndexOfCrossword);
					crossword = cwbrowser.crosswordsList
							.get(actualIndexOfCrossword);
					drawCrossword(crossword);
				} else
					JOptionPane.showMessageDialog(myJFrame.this,
							"There's no previous crossowrds to display");
			}
		});
		previousButton.setBounds(65, 66, 110, 25);
		secondPanel.add(previousButton);

		nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cwbrowser != null && cwbrowser.crosswordsList.size() > 0) {
					actualIndexOfCrossword++;
					if (actualIndexOfCrossword >= cwbrowser.crosswordsList
							.size())
						actualIndexOfCrossword = actualIndexOfCrossword
								% cwbrowser.crosswordsList.size();
					System.out.println(actualIndexOfCrossword);
					crossword = cwbrowser.crosswordsList
							.get(actualIndexOfCrossword);
					drawCrossword(crossword);
				} else
					JOptionPane.showMessageDialog(myJFrame.this,
							"There's no next crossowrds to display");
			}
		});
		nextButton.setBounds(195, 66, 110, 25);
		secondPanel.add(nextButton);

		thirdPanel = new JPanel();
		thirdPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Control",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		thirdPanel.setBounds(868, 11, 150, 112);
		contentPane.add(thirdPanel);
		thirdPanel.setLayout(null);

	/*	solveButton = new JButton("Solve");
		solveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					paintType = PaintType.SOLVED;
					drawCrossword(crossword);
			}
		});
		solveButton.setBounds(20, 26, 110, 25);
		thirdPanel.add(solveButton);*/

		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		saveButton.setBounds(20, 27, 110, 25);
		thirdPanel.add(saveButton);

		printButton = new JButton("Print");
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		printButton.setBounds(20, 66, 110, 25);
		thirdPanel.add(printButton);

		drawingPanel = new myDrawingJPanel();
		drawingPanel.setBackground(Color.white);

		scrollPanel = new JScrollPane(drawingPanel);
		scrollPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Crosswords preview",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPanel.setBounds(10, 135, 1008, 476);
		contentPane.add(scrollPanel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Displayed crossword",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(667, 11, 189, 112);
		contentPane.add(panel);
		panel.setLayout(null);
		
		rdbtnNotSolved = new JRadioButton("not solved");
		rdbtnNotSolved.setBounds(20, 27, 149, 25);
		rdbtnNotSolved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintType = PaintType.NOTSOLVED;
				drawingPanel.repaint();
			}
		});
		rdbtnNotSolved.setSelected(true);
		panel.add(rdbtnNotSolved);
		
		rdbtnSolved = new JRadioButton("solved");
		rdbtnSolved.setBounds(20, 66, 149, 25);
		rdbtnSolved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintType = PaintType.SOLVED;
				drawingPanel.repaint();
			}
		});
		panel.add(rdbtnSolved);
		
		ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnNotSolved);
	    group.add(rdbtnSolved);
	
		
	}

	public void drawCrossword(Crossword crossword) {

		int a = crossword.getBoard().getHeight();
		int b = crossword.getBoard().getWidth();
		if (drawingPanel.getHeight() < 2 * a * 30 + 60) {
			drawingPanel.setPreferredSize(new Dimension(100, 2 * a * 30 + 60));
			drawingPanel.revalidate();
		} else {
			if (436 > 2 * a * 30 + 60)
				drawingPanel.setPreferredSize(new Dimension(1008, 436));
			else
				drawingPanel.setPreferredSize(new Dimension(100,
						2 * a * 30 + 60));
			drawingPanel.revalidate();
		}
		drawingPanel.repaint();

	}

	public class myDrawingJPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g;

			if (crossword != null) {
				Crossword cw = myJFrame.this.crossword;

				for (int i = 0; i < cw.getBoard().getHeight(); i++) {
					g2.drawString(String.valueOf(i + 1) + ".", 20, 50 + i * 30);
					g2.drawString(String.valueOf(i + 1) + ".", 20, 70 + i * 30
							+ 30 * cw.getBoard().getHeight());
					for (int j = 0; j < cw.getBoard().getWidth(); j++) {
						if (cw.getBoard().getCell(i, j).content != null) {

							if (paintType == PaintType.SOLVED)
								g2.drawString(cw.getBoard().getCell(i, j).getContent(), 40 + j*30 + 10,
										15 + 30 * i+35);
							
							
							g2.setColor(Color.BLACK);
							g2.drawRect(40 + j * 30, i * 30 + 30, 30, 30);

							g2.drawString(cw.getEntries().get(i).getClue(), 40,
									70 + 30 * i + 30
											* cw.getBoard().getHeight());
						} else {
							System.out.print(".");
						}

					}
					System.out.println();
				}

				// System.out.println();

				Iterator<CwEntry> it = cw.getEntries().iterator();
				CwEntry c;
				while (it.hasNext()) {
					c = it.next();
					// System.out.println(c.getWord());
					// System.out.println(c.getClue());
				}

				// System.out.println();
			}
		}

	}
}
