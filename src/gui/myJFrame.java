package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JMenu;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import java.awt.Canvas;

public class myJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

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
		setBounds(100, 100, 1052, 652);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("ComboBox.border"));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "New crossword", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		//panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.setBounds(10, 11, 328, 71);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(20, 27, 37, 20);
		panel.add(lblHeight);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(67, 27, 34, 21);
		panel.add(spinner);
		
		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(121, 27, 34, 20);
		panel.add(lblWidth);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(163, 27, 34, 21);
		panel.add(spinner_1);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(219, 26, 89, 23);
		panel.add(btnGenerate);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "From file", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(348, 11, 339, 71);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField("Path...");
		textField.setBounds(20, 27, 146, 21);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("...");
		btnNewButton.setBounds(171, 26, 39, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Load");
		btnNewButton_1.setBounds(230, 26, 89, 23);
		panel_1.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Control", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(697, 11, 329, 71);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Solve");
		btnNewButton_2.setBounds(20, 26, 89, 23);
		panel_2.add(btnNewButton_2);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(119, 26, 89, 23);
		panel_2.add(btnSave);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(218, 26, 89, 23);
		panel_2.add(btnPrint);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Crossword preview", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(10, 87, 1016, 515);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(10, 28, 996, 477);
		panel_3.add(canvas);
		
		/*JFileChooser pliki = new JFileChooser(".");
		if (JFileChooser.APPROVE_OPTION==pliki.showOpenDialog(this))
		try
		{
			File f = pliki.getSelectedFile();
			setTitle(f.getAbsolutePath()+" Notatnik");
			BufferedReader br = new BufferedReader(new FileReader(f));
			String temp="";
			while (br.ready())
			{
				temp+=br.readLine()+"\n";
			}
			//textArea.setText(temp);
		}
		catch (IOException ex)
		{
			System.out.println("Brak pliku");
		}*/
		
	}
}
