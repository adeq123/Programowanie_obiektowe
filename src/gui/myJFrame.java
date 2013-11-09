package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	private JTextField textField;
	private Crossword crossword;
	private ConcretStrategy strategy;
	private int height;
	private int width;
	private CwBrowser cwbrowser;
	private String path;
	JSpinner spinner;
	JSpinner spinner_1;
	JFileChooser fileChooser;
	MyCanvas canvas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myJFrame frame = new myJFrame();
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
		setBounds(100, 100, 1087, 652);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("ComboBox.border"));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "New crossword",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		// panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.setBounds(10, 11, 266, 112);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(20, 27, 64, 25);
		panel.add(lblHeight);

		spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				height = ((Number) spinner.getValue()).intValue();
			}
		});
		spinner.setBounds(83, 27, 34, 25);
		panel.add(spinner);

		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(150, 27, 64, 25);
		panel.add(lblWidth);

		spinner_1 = new JSpinner();
		spinner_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				width = ((Number) spinner_1.getValue()).intValue();
			}
		});
		spinner_1.setBounds(212, 27, 34, 25);
		panel.add(spinner_1);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					crossword = new Crossword(height, width, "tak.txt");
					ConcretStrategy s = new ConcretStrategy();
					try {
						crossword.generate(s);
					} catch (noPossibilityToGenerateCrosswordException e) {
						e.printStackTrace();
					}
					canvas.repaint();
					
					
					
				} catch (wrongCrosswordDimensionsException e) {
					JOptionPane.showMessageDialog(myJFrame.this,
						    "The dimensions of crossword are wrong!");
					
					//e.printStackTrace();
				}
			}
		});
		btnGenerate.setBounds(73, 66, 110, 25);
		panel.add(btnGenerate);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "From file",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(285, 11, 370, 112);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		textField = new JTextField("Path...");
		textField.setBounds(20, 27, 146, 25);
		panel_1.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.showOpenDialog(myJFrame.this); 
				{
					// try {
					//File f = fileChooser.getSelectedFile();
					
					File f = fileChooser.getCurrentDirectory();
					setTitle(f.getAbsolutePath());
					path = f.getAbsolutePath();
					textField.setText(path);
					
					// BufferedReader br = new BufferedReader(new FileReader(f));
					// String temp = "";
					// while (br.ready()) {
					// temp += br.readLine() + "\n";
					// } // textArea.setText(temp);
					// } catch (IOException ex) {
					// System.out.println("Brak pliku");
					// }
				};
			}
		});
		btnNewButton.setBounds(168, 27, 39, 25);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Load");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cwbrowser = new CwBrowser(path);
				cwbrowser.browseCrosswords();
				System.out.println("AAA");
				
				
			}
		});
		btnNewButton_1.setBounds(240, 27, 110, 25);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Previous");
		btnNewButton_3.setBounds(65, 66, 110, 25);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Next");
		btnNewButton_4.setBounds(195, 66, 110, 25);
		panel_1.add(btnNewButton_4);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Control",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(667, 11, 410, 112);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JButton btnNewButton_2 = new JButton("Solve");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(20, 26, 110, 25);
		panel_2.add(btnNewButton_2);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(150, 26, 110, 25);
		panel_2.add(btnSave);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPrint.setBounds(280, 26, 110, 25);
		panel_2.add(btnPrint);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Crossword preview",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(10, 135, 1067, 467);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		canvas = new MyCanvas();
		canvas.setBounds(20, 29, 996, 477);
		panel_3.add(canvas);

		
	}
	
	public class MyCanvas extends Canvas {

		private static final long serialVersionUID = 1L;
		
		public void paint(Graphics g){
			//Graphics2D g2 = (Graphics2D) getGraphics();
			Graphics2D g2 = (Graphics2D) g;
			
			if (crossword != null){
				Crossword cw = myJFrame.this.crossword;

				for (int i = 0; i < cw.getBoard().getHeight(); i++) {
					for (int j = 0; j < cw.getBoard().getWidth(); j++) {
						if (cw.getBoard().getCell(i, j).content != null) {
							g2.drawString(String.valueOf(i+1)+".", 0, 20 +i*30);
							g2.setColor(Color.BLACK);
							g2.drawRect(20+j*30, i*30, 30, 30);
							System.out.print(cw.getBoard().getCell(i, j).content);
							g2.drawString(String.valueOf(i+1)+ ".", 0, 40 +i*30+30*cw.getBoard().getHeight());
							g2.drawString(cw.getEntries().get(i).getClue(), 20, 20+30*i+30*cw.getBoard().getHeight());
						} else{
							System.out.print(".");
						//	g2.setColor(Color.BLUE);
						//	g2.drawRect(j*20, i*20, 20, 20);
						}
							
					}
					System.out.println();
				}

				System.out.println();

				Iterator<CwEntry> it = cw.getEntries().iterator();
				CwEntry c;
				while (it.hasNext()) {
					c = it.next();
					System.out.println(c.getWord());
					System.out.println(c.getClue());
				}

				System.out.println();
			}
			
			
			
		}
			
			
			
		

	}
}
