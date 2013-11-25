package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

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
import javax.swing.filechooser.FileNameExtensionFilter;

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

import dictionary.InteliCwDB;
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
	// private File defaultDatabase = new File("tak.txt");
	private JButton generateButton;

	private JPanel secondPanel;
	private JTextField pathTextField = null;
	private JButton loadButton;
	private JFileChooser fileChooser;
	private JButton threeDotsButton;
	private File currentDirectory = null;
	// private int actualIndexOfCrossword = 0;
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
	private JFileChooser saveFileChooser;
	private JButton saveButton;
	private JFileChooser toPDFFileChooser;
	private JButton toPDFButton;

	private JPanel fifthPanel;
	private JTextField pathDatabaseTextField = null;
	private JFileChooser databaseFileChooser;
	private JButton threeDotsDatabaseButton;
	private JButton changeDatabaseButton;

	private MyDrawingJPanel drawingPanel;
	private JScrollPane scrollDrawingPanel;

	// private Crossword crossword = null;
	private int crosswordHeight;
	private int crosswordWidth;
	// private CwBrowser cwbrowser = null;
	private CwBrowser cwbrowser = new CwBrowser();

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

		try {
			cwbrowser.setDatabase(new InteliCwDB("cwdb.txt"));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 711);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("ComboBox.border"));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		firstPanel = new JPanel();
		firstPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "New crossword",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		firstPanel.setBounds(12, 123, 406, 71);
		contentPane.add(firstPanel);
		firstPanel.setLayout(null);

		heightLabel = new JLabel("Height");
		heightLabel.setBounds(20, 25, 64, 25);
		firstPanel.add(heightLabel);

		heightSpinner = new JSpinner();
		heightSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				crosswordHeight = ((Number) heightSpinner.getValue())
						.intValue();
			}
		});
		heightSpinner.setBounds(73, 25, 47, 25);
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
		widthSpinner.setBounds(197, 25, 47, 25);
		firstPanel.add(widthSpinner);

		generateButton = new JButton("Generate");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cwbrowser
							.generateCrossword(crosswordHeight, crosswordWidth);
					drawingPanel.drawCrossword(cwbrowser.getCrossword(), paintType);
				} catch (wrongCrosswordDimensionsException e) {
					JOptionPane.showMessageDialog(myJFrame.this,
							"The dimensions of crossword are wrong!");
				} catch (noPossibilityToGenerateCrosswordException e) {
					JOptionPane
							.showMessageDialog(myJFrame.this,
									"There is no possibility to generate crossword with entered data!");
				}
			}
		});
		generateButton.setBounds(276, 25, 110, 25);
		firstPanel.add(generateButton);

		secondPanel = new JPanel();
		secondPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"Browse crosswords from file", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		secondPanel.setBounds(256, 11, 360, 104);
		contentPane.add(secondPanel);
		secondPanel.setLayout(null);

		pathTextField = new JTextField("Path...");
		pathTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("lol");
			}
		});
		pathTextField.setBounds(20, 25, 156, 25);
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
		threeDotsButton.setBounds(178, 25, 39, 25);
		secondPanel.add(threeDotsButton);

		loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (pathTextField.getText().equals("Path...") == false) {
					currentDirectory = new File(pathTextField.getText());
					if (currentDirectory.isDirectory() == true) {
						if (currentDirectory.canRead() == true
								&& currentDirectory.canExecute() == true) {

							// cwbrowser = new CwBrowser(currentDirectory
							// .getAbsolutePath());
							cwbrowser.setPath(currentDirectory
									.getAbsolutePath());

							try {
								cwbrowser.loadCrosswords();

								if (cwbrowser.crosswordsList.size() > 0) {
									// System.out.println("SSSSSSSSSS");
									// actualIndexOfCrossword = 0;
									// crossword = cwbrowser.crosswordsList
									// .get(actualIndexOfCrossword);
									// drawCrossword(crossword);
									drawingPanel.drawCrossword(cwbrowser.getCrossword(), paintType);
									previousButton.setEnabled(true);
									nextButton.setEnabled(true);
								} else {
									previousButton.setEnabled(false);
									nextButton.setEnabled(false);
									JOptionPane.showMessageDialog(
											myJFrame.this,
											"Any crossword was found to load");
								}
							} catch (FileNotFoundException e) {
								JOptionPane.showMessageDialog(myJFrame.this,
										"There is no crosswords in directory!");
							} catch (IOException e) {
								JOptionPane
										.showMessageDialog(myJFrame.this,
												"Sorry, there is a problem with loading s from directory");
							}
						} else {
							JOptionPane
									.showMessageDialog(myJFrame.this,
											"Cannot read or execute files from directory!");
						}
					} else {
						JOptionPane.showMessageDialog(myJFrame.this,
								"The path doesn't point a valid directory!");
					}

				} else {
					JOptionPane.showMessageDialog(myJFrame.this,
							"Choose a directory or write down a path to file!");
				}

			}
		});
		loadButton.setBounds(249, 25, 90, 25);
		secondPanel.add(loadButton);

		previousButton = new JButton("Previous");
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cwbrowser != null && cwbrowser.crosswordsList.size() > 0) {
					// actualIndexOfCrossword--;
					// if (actualIndexOfCrossword < 0)
					// actualIndexOfCrossword = actualIndexOfCrossword
					// + cwbrowser.crosswordsList.size();
					// System.out.println(actualIndexOfCrossword);
					// crossword = cwbrowser.crosswordsList
					// .get(actualIndexOfCrossword);
					// drawCrossword(crossword);
					drawingPanel.drawCrossword(cwbrowser.previousCrossword(), paintType);
					System.out.println("Q");
				} else
					JOptionPane.showMessageDialog(myJFrame.this,
							"There's no previous crossowrds to display");
			}
		});
		previousButton.setBounds(70, 60, 110, 25);
		previousButton.setEnabled(false);
		secondPanel.add(previousButton);

		nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cwbrowser != null && cwbrowser.crosswordsList.size() > 0) {
					// actualIndexOfCrossword++;
					// if (actualIndexOfCrossword >= cwbrowser.crosswordsList
					// .size())
					// actualIndexOfCrossword = actualIndexOfCrossword
					// % cwbrowser.crosswordsList.size();
					// System.out.println(actualIndexOfCrossword);
					// crossword = cwbrowser.crosswordsList
					// .get(actualIndexOfCrossword);
					// drawCrossword(crossword);
					drawingPanel.drawCrossword(cwbrowser.nextCrossword(), paintType);
				} else
					JOptionPane.showMessageDialog(myJFrame.this,
							"There's no next crossowrds to display");
			}
		});
		nextButton.setBounds(200, 60, 110, 25);
		nextButton.setEnabled(false);
		secondPanel.add(nextButton);

		thirdPanel = new JPanel();
		thirdPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Displayed crossword",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		thirdPanel.setBounds(426, 123, 330, 71);
		contentPane.add(thirdPanel);
		thirdPanel.setLayout(null);

		notSolvedRadioButton = new JRadioButton("not solved");
		notSolvedRadioButton.setBounds(20, 25, 140, 25);
		notSolvedRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintType = PaintType.NOTSOLVED;
				drawingPanel.drawCrossword(cwbrowser.getCrossword(), paintType);
			}
		});
		notSolvedRadioButton.setSelected(true);
		thirdPanel.add(notSolvedRadioButton);

		solvedRadioButton = new JRadioButton("solved");
		solvedRadioButton.setBounds(185, 25, 140, 25);
		solvedRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintType = PaintType.SOLVED;
				drawingPanel.drawCrossword(cwbrowser.getCrossword(), paintType);
			}
		});
		thirdPanel.add(solvedRadioButton);

		ButtonGroup group = new ButtonGroup();
		group.add(notSolvedRadioButton);
		group.add(solvedRadioButton);

		drawingPanel = new MyDrawingJPanel();
		drawingPanel.setBackground(Color.white);

		scrollDrawingPanel = new JScrollPane(drawingPanel);
		scrollDrawingPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Crosswords preview",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollDrawingPanel.setBounds(12, 202, 744, 476);
		contentPane.add(scrollDrawingPanel);

		fifthPanel = new JPanel();
		fifthPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Change default database",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		fifthPanel.setBounds(12, 11, 236, 104);
		contentPane.add(fifthPanel);
		fifthPanel.setLayout(null);

		pathDatabaseTextField = new JTextField();
		pathDatabaseTextField.setText("Path...");
		pathDatabaseTextField.setBounds(20, 24, 156, 25);
		fifthPanel.add(pathDatabaseTextField);
		pathDatabaseTextField.setColumns(10);

		threeDotsDatabaseButton = new JButton("...");
		threeDotsDatabaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				databaseFileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						".txt", "txt");
				databaseFileChooser.setAcceptAllFileFilterUsed(false);
				databaseFileChooser.setFileFilter(filter);
				int option = databaseFileChooser.showDialog(myJFrame.this,// a
																			// moze
																			// rodzicem
																			// ma
																			// byc
																			// button??
						"Import");
				if (option == JFileChooser.APPROVE_OPTION) {
					// defaultDatabase = databaseFileChooser.getSelectedFile();
					pathDatabaseTextField.setText(databaseFileChooser
							.getSelectedFile().getAbsolutePath());

				}

			}
		});
		threeDotsDatabaseButton.setBounds(177, 24, 39, 25);
		fifthPanel.add(threeDotsDatabaseButton);

		changeDatabaseButton = new JButton("Change");
		changeDatabaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// crossword.setCwDB(pathDatabaseTextField.getText());
					// cwbrowser.getCrossword().setCwDB(pathDatabaseTextField.getText());
					cwbrowser.setDatabase(new InteliCwDB(pathDatabaseTextField
							.getText()));

					// pathDatabaseTextField.setText(defaultDatabase
					// .getAbsolutePath());
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(myJFrame.this,
							"File with database not found", "Database error",
							JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(myJFrame.this,
							"Failed to import database from file",
							"Database error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		changeDatabaseButton.setBounds(63, 60, 110, 25);
		fifthPanel.add(changeDatabaseButton);

		fourthPanel = new JPanel();
		fourthPanel.setBounds(624, 11, 132, 104);
		contentPane.add(fourthPanel);
		fourthPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Control",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		fourthPanel.setLayout(null);

		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if (crossword != null) {
				if (cwbrowser.getCrossword() != null) {
					saveFileChooser = new JFileChooser();
					saveFileChooser
							.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int option = saveFileChooser.showDialog(myJFrame.this,
							"Approve directory");
					if (option == JFileChooser.APPROVE_OPTION) {
						try {
							// cwbrowser = new CwBrowser(toPDFFileChooser
							// .getSelectedFile().toString());
							// cwbrowser.saveCrosswords(crossword);
							cwbrowser.setPath(saveFileChooser.getSelectedFile()
									.toString());
							cwbrowser.saveCrosswords(cwbrowser.getCrossword());
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(myJFrame.this,
									"Failed to save crossowrd!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(myJFrame.this,
							"There's no crossowrds to save!");
				}
			}
		});
		saveButton.setBounds(21, 25, 90, 25);
		fourthPanel.add(saveButton);

		toPDFButton = new JButton("To PDF");
		toPDFButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cwbrowser.getCrossword() != null) {
					toPDFFileChooser = new JFileChooser();
					toPDFFileChooser
							.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int option = toPDFFileChooser.showDialog(myJFrame.this,
							"Approve directory");
					if (option == JFileChooser.APPROVE_OPTION) {
						File fff = createPdf(
								toPDFFileChooser.getSelectedFile(),
								cwbrowser.getCrossword());
						JOptionPane.showMessageDialog(myJFrame.this,
								"Crossword saved in: " + fff.getAbsolutePath());
					}
				} else {
					JOptionPane.showMessageDialog(myJFrame.this,
							"You have to generate a crossword firstly!");
				}

			}
		});
		toPDFButton.setBounds(21, 60, 90, 25);
		fourthPanel.add(toPDFButton);
	}

	public File createPdf(File direct, Crossword cw) {
		File file = new File(direct.getAbsolutePath() + "/"
				+ Long.toString(new Date().getTime()));
		Document document = new Document(PageSize.A4);
		document.addTitle("Crossword example");
		document.addAuthor("Krzysztof Spytkowski");
		document.addSubject("Crossword");
		document.addKeywords("crossword");
		document.addCreator("My program using iText");
		Paragraph p = new Paragraph();
		PdfWriter pdfWriter = null;
		try {
			BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN,
					BaseFont.CP1250, BaseFont.EMBEDDED);
			Font f = new Font(bf, 16, Font.NORMAL);
			pdfWriter = PdfWriter.getInstance(document,
					new FileOutputStream(file.getAbsoluteFile()));
			document.open();
			PdfContentByte cb = pdfWriter.getDirectContent();
			for (int i = 0; i < cw.getBoard().getHeight(); i++) {
				p.add(new Chunk((i + 1) + "\n", f).setLineHeight(30));
				for (int j = 0; j < cw.getBoard().getWidth(); j++) {
					if (cw.getBoard().getCell(i, j).content != null) {
						cb.saveState();
						cb.setColorStroke(GrayColor.BLACK);
						cb.setColorFill(GrayColor.WHITE);
						cb.rectangle(60 + j * 30, -45 + 840 - 30 * i - 30,
								30, 30); // A4 height = 840f
						cb.fillStroke();
						cb.restoreState();
					}
				}
			}
			p.add(new Chunk("\n", f).setLineHeight(30));
			for (int i = 1; i <= cw.getBoard().getHeight(); i++) {
				p.add(new Chunk(i + ". " + cw.getEntries().get(i).getClue()
						+ "\n\n", f).setLineHeight(15));
			}
			document.add(p);
			document.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(myJFrame.this,
					"Directory to write crossword not found");
		} catch (DocumentException e) {
			JOptionPane.showMessageDialog(myJFrame.this,
					"There is no possibility to create PDF");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(myJFrame.this,
					"Failed to create PDF with crossword");
		} finally {
			if (pdfWriter != null)
				pdfWriter.close();
			if (document != null)
				document.close();
		}
		return file;
	}
}
