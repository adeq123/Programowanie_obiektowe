package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import strategy.EasyStrategy;
import strategy.HardStrategy;
import browse.CwBrowser;

import com.itextpdf.text.DocumentException;

import dictionary.InteliCwDB;
import exceptions.noCrosswordFoundToLoadException;
import exceptions.noPossibilityToGenerateCrosswordException;
import exceptions.toLargeCrossowrdToCreatePdfFileException;
import exceptions.wrongCrosswordDimensionsException;
import gui.MyDrawingJPanel.PaintType;

/**
 * 
 * @author krzysztof
 * 
 */
public class MyJFrame extends JFrame {

	private static final long serialVersionUID = 1L; // Serial version default number

	private JPanel contentPane; // Main panel

	private JPanel firstPanel; // Panel with database
	private JTextField databasePathTextField; // Path to file with database
	private JFileChooser databaseFileChooser; // Database file chooser
	private JButton threeDotsDatabaseButton; // File chooser button
	private JButton changeDatabaseButton; // Change database button

	private JPanel secondPanel; // Panel with browsed crosswords
	private JTextField browsePathTextField; // Path to directory with crosswords
	private JButton loadButton; // Load crosswords button
	private JFileChooser fileChooser; // Crosswords file chooser
	private JButton threeDotsButton; // File chooser button
	private File currentDirectory; // Directory with crosswords
	private JButton previousButton; // Show previous crossword button
	private JButton nextButton; // Show next crossword button

	private JPanel thirdPanel; // Control Panel
	private JFileChooser saveFileChooser; // Save file chooser
	private JButton saveButton; // Save button
	private JFileChooser toPDFFileChooser; // Create PDF file chooser
	private JButton toPDFButton; // Create PDF button

	private JPanel fourthPanel; // New Crossword Panel
	private JLabel heightLabel; // "Height" label
	private JSpinner heightSpinner; // Height chooser
	private JLabel widthLabel; // "Width" label
	private JSpinner widthSpinner; // Width chooser
	private JButton easyGenerateButton; // Generate new easy crossword button
	private JButton hardGenerateButton; // Generate new hard crossword button

	private JPanel fifthPanel; // Panel with type of displayed crossword
	private JRadioButton notSolvedRadioButton; // Not solved radio button
	private JRadioButton solvedRadioButton; // solved radio button

	private MyDrawingJPanel myDrawingJPanel; // Panel with actual drown crossword
	private JScrollPane scrollDrawingPanel; // Scroll Panel with crossword's dimensions

	private CwBrowser cwbrowser = new CwBrowser(); // Crossword's browser from file
	private EasyStrategy easyStrategy = new EasyStrategy(); // Easy strategy used to generate crosswords
	private HardStrategy hardStrategy = new HardStrategy(); // Hard strategy used to generate crosswords

	/**
	 * Constructor
	 * 
	 * Creates the frame.
	 * 
	 */
	public MyJFrame() {

		try {
			cwbrowser.setDatabase(new InteliCwDB("cwdb.txt"));
		} catch (FileNotFoundException e1) {
			cwbrowser.setDatabase(null);
		} catch (IOException e1) {
			cwbrowser.setDatabase(null);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 711);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("ComboBox.border"));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		firstPanel = new JPanel();
		firstPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Change database", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		firstPanel.setBounds(12, 11, 236, 104);
		contentPane.add(firstPanel);
		firstPanel.setLayout(null);

		databasePathTextField = new JTextField();
		databasePathTextField.setText("Path...");
		databasePathTextField.setBounds(20, 24, 156, 25);
		firstPanel.add(databasePathTextField);
		databasePathTextField.setColumns(10);

		threeDotsDatabaseButton = new JButton("...");
		threeDotsDatabaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				databaseFileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
				databaseFileChooser.setAcceptAllFileFilterUsed(false);
				databaseFileChooser.setFileFilter(filter);
				int option = databaseFileChooser.showDialog(MyJFrame.this, "Import");
				if (option == JFileChooser.APPROVE_OPTION) {
					databasePathTextField.setText(databaseFileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		threeDotsDatabaseButton.setBounds(177, 24, 39, 25);
		firstPanel.add(threeDotsDatabaseButton);

		changeDatabaseButton = new JButton("Change");
		changeDatabaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cwbrowser.setDatabase(new InteliCwDB(databasePathTextField.getText()));
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(MyJFrame.this, "File with database not found");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(MyJFrame.this, "Failed to import database from file");
				}
			}
		});
		changeDatabaseButton.setBounds(63, 60, 110, 25);
		firstPanel.add(changeDatabaseButton);

		secondPanel = new JPanel();
		secondPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Browse crosswords from file", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		secondPanel.setBounds(256, 11, 360, 104);
		contentPane.add(secondPanel);
		secondPanel.setLayout(null);

		browsePathTextField = new JTextField();
		browsePathTextField.setText("Path...");
		browsePathTextField.setBounds(20, 25, 156, 25);
		secondPanel.add(browsePathTextField);
		browsePathTextField.setColumns(10);

		threeDotsButton = new JButton("...");
		threeDotsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = fileChooser.showDialog(MyJFrame.this, "Approve directory");
				if (option == JFileChooser.APPROVE_OPTION) {
					currentDirectory = fileChooser.getSelectedFile();
					browsePathTextField.setText(currentDirectory.getAbsolutePath());
				}
			}
		});
		threeDotsButton.setBounds(178, 25, 39, 25);
		secondPanel.add(threeDotsButton);

		loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (browsePathTextField.getText().equals("Path...") == false) {
					currentDirectory = new File(browsePathTextField.getText());
					if (currentDirectory.isDirectory() == true) {
						if (currentDirectory.canRead() == true && currentDirectory.canExecute() == true) {
							cwbrowser.setPath(currentDirectory.getAbsolutePath());
							try {
								cwbrowser.loadCrosswords();
								myDrawingJPanel.drawCrossword(cwbrowser.getCrossword());
								previousButton.setEnabled(true);
								nextButton.setEnabled(true);
							} catch (noCrosswordFoundToLoadException e) {
								previousButton.setEnabled(false);
								nextButton.setEnabled(false);
								JOptionPane.showMessageDialog(MyJFrame.this, e.getMessage());
							} catch (FileNotFoundException e) {
								JOptionPane.showMessageDialog(MyJFrame.this, "There is no crosswords in directory!");
							} catch (IOException e) {
								JOptionPane.showMessageDialog(MyJFrame.this, "Sorry, there is a problem with loading s from directory");
							}
						} else {
							JOptionPane.showMessageDialog(MyJFrame.this, "Cannot read or execute files from directory!");
						}
					} else {
						JOptionPane.showMessageDialog(MyJFrame.this, "The path doesn't point valid directory!");
					}

				} else {
					JOptionPane.showMessageDialog(MyJFrame.this, "Choose directory or write down a path to file!");
				}

			}
		});
		loadButton.setBounds(249, 25, 90, 25);
		secondPanel.add(loadButton);

		previousButton = new JButton("Previous");
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cwbrowser != null && cwbrowser.crosswordsList.size() > 0) {
					myDrawingJPanel.drawCrossword(cwbrowser.previousCrossword());
				} else {
					JOptionPane.showMessageDialog(MyJFrame.this, "There's no previous crossowrds to display");
				}
			}
		});
		previousButton.setBounds(70, 60, 110, 25);
		previousButton.setEnabled(false);
		secondPanel.add(previousButton);

		nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cwbrowser != null && cwbrowser.crosswordsList.size() > 0) {
					myDrawingJPanel.drawCrossword(cwbrowser.nextCrossword());
				} else {
					JOptionPane.showMessageDialog(MyJFrame.this, "There's no next crossowrds to display");
				}
			}
		});
		nextButton.setBounds(200, 60, 110, 25);
		nextButton.setEnabled(false);
		secondPanel.add(nextButton);

		thirdPanel = new JPanel();
		thirdPanel.setBounds(624, 11, 132, 104);
		contentPane.add(thirdPanel);
		thirdPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Control", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		thirdPanel.setLayout(null);

		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cwbrowser.getCrossword() != null) {
					saveFileChooser = new JFileChooser();
					saveFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int option = saveFileChooser.showDialog(MyJFrame.this, "Approve directory");
					if (option == JFileChooser.APPROVE_OPTION) {
						if (saveFileChooser.getSelectedFile().exists() == true) {
							if (saveFileChooser.getSelectedFile().canWrite() == true) {
								try {
									cwbrowser.setPath(saveFileChooser.getSelectedFile().toString());
									cwbrowser.saveCrosswords(cwbrowser.getCrossword());
									JOptionPane.showMessageDialog(MyJFrame.this, "Crossword saved in: " + cwbrowser.getPath());
								} catch (IOException e1) {
									JOptionPane.showMessageDialog(MyJFrame.this, "Failed to save crossowrd!");
								}

							} else {
								JOptionPane.showMessageDialog(MyJFrame.this, "Can't write to this directory!");
							}
						} else {
							JOptionPane.showMessageDialog(MyJFrame.this, "This directory doesn't exists!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(MyJFrame.this, "There's no crossowrds to save!");
				}
			}
		});
		saveButton.setBounds(21, 25, 90, 25);
		thirdPanel.add(saveButton);

		toPDFButton = new JButton("To PDF");
		toPDFButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cwbrowser.getCrossword() != null) {
					toPDFFileChooser = new JFileChooser();
					toPDFFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int option = toPDFFileChooser.showDialog(MyJFrame.this, "Approve directory");
					if (option == JFileChooser.APPROVE_OPTION) {
						File toPDFFile;
						try {
							toPDFFile = cwbrowser.cwwriter.createPdf(toPDFFileChooser.getSelectedFile(), cwbrowser.getCrossword());
							JOptionPane.showMessageDialog(MyJFrame.this, "Crossword saved in: " + toPDFFile.getAbsolutePath());
						} catch (toLargeCrossowrdToCreatePdfFileException e1) {
							JOptionPane.showMessageDialog(MyJFrame.this, e1.getMessage());
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(MyJFrame.this, "Directory to write crossword not found");
						} catch (DocumentException e1) {
							JOptionPane.showMessageDialog(MyJFrame.this, "There is no possibility to create PDF");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(MyJFrame.this, "Failed to create PDF with crossword");
						}
					}
				} else {
					JOptionPane.showMessageDialog(MyJFrame.this, "You have to generate a crossword firstly!");
				}
			}
		});
		toPDFButton.setBounds(21, 60, 90, 25);
		thirdPanel.add(toPDFButton);

		fourthPanel = new JPanel();
		fourthPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Generate new crossword", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		fourthPanel.setBounds(12, 123, 497, 71);
		contentPane.add(fourthPanel);
		fourthPanel.setLayout(null);

		heightLabel = new JLabel("Height");
		heightLabel.setBounds(20, 25, 64, 25);
		fourthPanel.add(heightLabel);

		heightSpinner = new JSpinner();
		heightSpinner.setBounds(73, 25, 47, 25);
		fourthPanel.add(heightSpinner);

		widthLabel = new JLabel("Width");
		widthLabel.setBounds(138, 25, 54, 25);
		fourthPanel.add(widthLabel);

		widthSpinner = new JSpinner();
		widthSpinner.setBounds(191, 25, 47, 25);
		fourthPanel.add(widthSpinner);

		easyGenerateButton = new JButton("EASY");
		easyGenerateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (cwbrowser.getDatabase() == null) {
						JOptionPane.showMessageDialog(MyJFrame.this, "Failed to load database. Choose one before generating crossword.");
						threeDotsDatabaseButton.doClick();
						changeDatabaseButton.doClick();
					} else {
						cwbrowser.generateCrossword(((Number) heightSpinner.getValue()).intValue(), ((Number) widthSpinner.getValue()).intValue(), easyStrategy);
						myDrawingJPanel.drawCrossword(cwbrowser.getCrossword());
					}
				} catch (wrongCrosswordDimensionsException e) {
					JOptionPane.showMessageDialog(MyJFrame.this, e.getMessage());
				} catch (noPossibilityToGenerateCrosswordException e) {
					JOptionPane.showMessageDialog(MyJFrame.this, e.getMessage());
				}
			}
		});
		easyGenerateButton.setBounds(268, 25, 90, 25);
		fourthPanel.add(easyGenerateButton);

		hardGenerateButton = new JButton("HARD");
		hardGenerateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (cwbrowser.getDatabase() == null) {
						JOptionPane.showMessageDialog(MyJFrame.this, "Failed to load database. Choose one before generating crossword.");
						threeDotsDatabaseButton.doClick();
						changeDatabaseButton.doClick();
					} else {
						cwbrowser.generateCrossword(((Number) heightSpinner.getValue()).intValue(), ((Number) widthSpinner.getValue()).intValue(), hardStrategy);
						myDrawingJPanel.drawCrossword(cwbrowser.getCrossword());
					}
				} catch (wrongCrosswordDimensionsException e) {
					JOptionPane.showMessageDialog(MyJFrame.this, e.getMessage());
				} catch (noPossibilityToGenerateCrosswordException e) {
					JOptionPane.showMessageDialog(MyJFrame.this, e.getMessage());
				}
			}
		});
		hardGenerateButton.setBounds(381, 25, 90, 25);
		fourthPanel.add(hardGenerateButton);

		fifthPanel = new JPanel();
		fifthPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Displayed crossword", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		fifthPanel.setBounds(521, 123, 235, 71);
		contentPane.add(fifthPanel);
		fifthPanel.setLayout(null);

		notSolvedRadioButton = new JRadioButton("not solved");
		notSolvedRadioButton.setBounds(19, 25, 99, 25);
		notSolvedRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myDrawingJPanel.setPaintType(PaintType.NOTSOLVED);
				if (cwbrowser.getCrossword() != null) {
					myDrawingJPanel.printNotSolvedcrossword();
				}
			}
		});
		notSolvedRadioButton.setSelected(true);
		fifthPanel.add(notSolvedRadioButton);

		solvedRadioButton = new JRadioButton("solved");
		solvedRadioButton.setBounds(133, 25, 71, 25);
		solvedRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myDrawingJPanel.setPaintType(PaintType.SOLVED);
				if (cwbrowser.getCrossword() != null) {
					myDrawingJPanel.printSolvedCrossword();
				}
			}
		});
		fifthPanel.add(solvedRadioButton);

		ButtonGroup group = new ButtonGroup();
		group.add(notSolvedRadioButton);
		group.add(solvedRadioButton);

		myDrawingJPanel = new MyDrawingJPanel();
		myDrawingJPanel.setLayout(null);
		myDrawingJPanel.setBackground(Color.white);
		myDrawingJPanel.setPaintType(PaintType.NOTSOLVED);

		scrollDrawingPanel = new JScrollPane(myDrawingJPanel);
		scrollDrawingPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Crosswords preview", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollDrawingPanel.setBounds(12, 202, 744, 476);
		contentPane.add(scrollDrawingPanel);
	}
}