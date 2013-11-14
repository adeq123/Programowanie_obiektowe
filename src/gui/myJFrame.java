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
import java.io.FileOutputStream;
import java.io.IOException;

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

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

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
	private JTextField pathTextField = null;
	private JButton loadButton;
	private JFileChooser fileChooser;
	private JButton threeDotsButton;
	private File currentDirectory = null;
	private int actualIndexOfCrossword = 0;
	private JButton previousButton;
	private JButton nextButton;

	private JPanel thirdPanel;

	public enum PaintType {
		SOLVED, NOTSOLVED
	}

	PaintType paintType = PaintType.NOTSOLVED;
	private JRadioButton notSolvedRadioButton;
	private JRadioButton solvedRadioButton;

	private JPanel fourthPanel;
	private JButton saveButton;
	private JButton printButton;

	private myDrawingJPanel drawingPanel;
	private JScrollPane scrollDrawingPanel;

	private Crossword crossword;
	private int crosswordHeight;
	private int crosswordWidth;
	private CwBrowser cwbrowser = null;

	public void drawProper(PaintType pt) {
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
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
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
				crosswordHeight = ((Number) heightSpinner.getValue())
						.intValue();
			}
		});
		heightSpinner.setBounds(73, 27, 47, 25);
		firstPanel.add(heightSpinner);

		widthLabel = new JLabel("Width");
		widthLabel.setBounds(140, 27, 64, 25);
		firstPanel.add(widthLabel);

		widthSpinner = new JSpinner();
		widthSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				crosswordWidth = ((Number) widthSpinner.getValue()).intValue();
			}
		});
		widthSpinner.setBounds(197, 27, 47, 25);
		firstPanel.add(widthSpinner);

		// ZMIEN TO!!!!!!!!!!!!!!
		generateButton = new JButton("Generate");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					crossword = new Crossword(crosswordHeight, crosswordWidth,
							"tak.txt");
					ConcretStrategy s = new ConcretStrategy();
					System.out.println("AAAAAAAAAAAAAA");
					crossword.generate(s);
					System.out.println("AAAAAAAAAAAAAA");
					drawCrossword(crossword);
				} catch (wrongCrosswordDimensionsException e) {
					JOptionPane.showMessageDialog(myJFrame.this,
							"The dimensions of crossword are wrong!");
				} catch (noPossibilityToGenerateCrosswordException e) {
					// ZROb COS Z TM!!!!!!!!
					e.printStackTrace();
				}
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
		pathTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("lol");
			}
		});
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
				//ma pobierac z path i dopiero cos tworxyc!!!!!!!!!!!!! zrob tu try i łap wyjatek!
				System.out.println(pathTextField.getText());
				//if to co wyzej rozne of=d nulaa to dalej rob!
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
						System.out.println("A");
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
				.getBorder("TitledBorder.border"), "Displayed crossword",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		thirdPanel.setBounds(667, 11, 189, 112);
		contentPane.add(thirdPanel);
		thirdPanel.setLayout(null);

		notSolvedRadioButton = new JRadioButton("not solved");
		notSolvedRadioButton.setBounds(20, 27, 149, 25);
		notSolvedRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintType = PaintType.NOTSOLVED;
				drawingPanel.repaint();
			}
		});
		notSolvedRadioButton.setSelected(true);
		thirdPanel.add(notSolvedRadioButton);

		solvedRadioButton = new JRadioButton("solved");
		solvedRadioButton.setBounds(20, 66, 149, 25);
		solvedRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintType = PaintType.SOLVED;
				drawingPanel.repaint();
			}
		});
		thirdPanel.add(solvedRadioButton);

		ButtonGroup group = new ButtonGroup();
		group.add(notSolvedRadioButton);
		group.add(solvedRadioButton);

		fourthPanel = new JPanel();
		fourthPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Control",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		fourthPanel.setBounds(868, 11, 150, 112);
		contentPane.add(fourthPanel);
		fourthPanel.setLayout(null);

		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (crossword != null) {
					fileChooser = new JFileChooser();
					fileChooser
							.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					// zrob zeby sie krzyzowki zapisywaly z roszerzeniem .txt
					// FileNameExtensionFilter
					// fileChooser.setAcceptAllFileFilterUsed(false);
					// fileChooser.setFileFilter();
					int option = fileChooser.showDialog(myJFrame.this,
							"Approve directory");
					if (option == JFileChooser.APPROVE_OPTION) {
						try {
							cwbrowser = new CwBrowser(fileChooser
									.getSelectedFile().toString());
							cwbrowser.saveCrosswords(crossword);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(myJFrame.this,
							"There's no crossowrds to save!");
				}
			}
		});
		saveButton.setBounds(20, 27, 110, 25);
		fourthPanel.add(saveButton);

		printButton = new JButton("Print");
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createPdf();
			}
		});
		printButton.setBounds(20, 66, 110, 25);
		fourthPanel.add(printButton);

		drawingPanel = new myDrawingJPanel();
		drawingPanel.setBackground(Color.white);

		scrollDrawingPanel = new JScrollPane(drawingPanel);
		scrollDrawingPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Crosswords preview",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollDrawingPanel.setBounds(10, 135, 1008, 476);
		contentPane.add(scrollDrawingPanel);
	}

	public void drawCrossword(Crossword crossword) {

		int a = crossword.getBoard().getHeight();
		int b = crossword.getBoard().getWidth();
		System.out.println("QQQQQQQQQQQQQQQ");
		if (drawingPanel.getHeight() < 2 * a * 30 + 60) {
			drawingPanel.setPreferredSize(new Dimension(1078, 2 * a * 30 + 60));
			drawingPanel.revalidate();
		} else {
			if (436 > 2 * a * 30 + 60)
				drawingPanel.setPreferredSize(new Dimension(1078, 436));
			else
				drawingPanel.setPreferredSize(new Dimension(1078,
						2 * a * 30 + 60));
			drawingPanel.revalidate();
		}
		drawingPanel.repaint();
	}

	public class myDrawingJPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g;

			if (crossword != null) {
				Crossword cw = myJFrame.this.crossword;
				for (int i = 0; i < cw.getBoard().getHeight(); i++) {
					g2.drawString(String.valueOf(i + 1) + " ", 20, 50 + i * 30);
					g2.drawString(String.valueOf(i + 1) + ".", 20, 70 + i * 30
							+ 30 * cw.getBoard().getHeight());
					for (int j = 0; j < cw.getBoard().getWidth(); j++) {
						if (cw.getBoard().getCell(i, j).content != null) {
							if (paintType == PaintType.SOLVED)
								g2.drawString(cw.getBoard().getCell(i, j)
										.getContent(), 40 + j * 30 + 10,
										15 + 30 * i + 35);
							g2.setColor(Color.BLACK);
							g2.drawRect(40 + j * 30, i * 30 + 30, 30, 30);

							g2.drawString(cw.getEntries().get(i + 1).getClue(),
									40, 70 + 30 * i + 30
											* cw.getBoard().getHeight());
						}
					}
				}
			}
		}
	}

	public void createPdf() {
		Document document = new Document(PageSize.A4);
		document.addTitle("Crossword example");
		document.addAuthor("Krzysztof Spytkowski");
		document.addSubject("Crossword");
		document.addKeywords("crossword");
		document.addCreator("My program using iText");
		// a zamknac plik to co?!
		Paragraph p = new Paragraph();
		try {
			BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN,
					BaseFont.CP1250, BaseFont.EMBEDDED);
			Font f = new Font(bf, 16, Font.NORMAL);
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("nameOfFile.pdf"));
			document.open();
			PdfContentByte cb = writer.getDirectContent();

			for (int i = 0; i < crossword.getBoard().getHeight(); i++) {
				p.add(new Chunk((i + 1) + "\n", f).setLineHeight(30));
				for (int j = 0; j < crossword.getBoard().getWidth(); j++) {
					if (crossword.getBoard().getCell(i, j).content != null) {
						cb.saveState();
						cb.setColorStroke(GrayColor.BLACK);
						cb.setColorFill(GrayColor.WHITE);
						cb.rectangle(60f + j * 30f, -45f + 840f - 30f * i - 30,
								30f, 30f);// wysokosc A4 = 840f
						cb.fillStroke();
						cb.restoreState();
					}
				}
			}

			p.add(new Chunk("\n", f).setLineHeight(30));

			for (int i = 1; i <= crossword.getBoard().getHeight(); i++) {
				p.add(new Chunk(i + ". "
						+ crossword.getEntries().get(i).getClue() + "\n\n", f)
						.setLineHeight(15));
			}

			document.add(p);
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// usun ten dokument jak cos poszlo nie tak!
		}
	}
}
