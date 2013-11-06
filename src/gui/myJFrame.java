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
		setBounds(100, 100, 717, 470);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("ComboBox.border"));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "New crossword", TitledBorder.LEFT, TitledBorder.TOP, null, UIManager.getColor("TitledBorder.titleColor")));
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.setBounds(10, 11, 364, 53);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Height");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setPreferredSize(new Dimension(100, 40));
		lblNewLabel.setMinimumSize(new Dimension(100, 40));
		lblNewLabel.setMaximumSize(new Dimension(100, 40));
		lblNewLabel.setBounds(10, 21, 44, 20);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Width");
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1.setBounds(142, 24, 35, 14);
		panel.add(lblNewLabel_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinner.setBounds(71, 21, 29, 20);
		panel.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(193, 21, 29, 20);
		panel.add(spinner_1);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(248, 20, 89, 23);
		panel.add(btnGenerate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "From file", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(398, 11, 280, 53);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField("Path...");
		textField.setBounds(10, 22, 86, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("...");
		btnNewButton.setBounds(106, 21, 39, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Load");
		btnNewButton_1.setBounds(168, 19, 89, 23);
		panel_1.add(btnNewButton_1);
		
		JFileChooser pliki = new JFileChooser(".");
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
		}
		
	}
}
