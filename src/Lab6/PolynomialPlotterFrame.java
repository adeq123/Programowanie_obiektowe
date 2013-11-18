package Lab6;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PolynomialPlotterFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JPanel panel;
	private JLabel lblWsp;
	private JTextField txtNp;
	private JLabel lblZakres;
	private JTextField txtNp_1;
	private JLabel lblNewLabel;
	private JTextField txtNp_2;
	private JButton btnRysuj;
	
	private PolynomialPlotterPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PolynomialPlotterFrame frame = new PolynomialPlotterFrame();
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
	public PolynomialPlotterFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 663);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Kontrola",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 12, 702, 96);
		panel.setLayout(null);
		contentPane.add(panel);
		
		lblWsp = new JLabel("Wspolczynniki wielomianu");
		lblWsp.setBounds(72, 23, 200, 15);
		panel.add(lblWsp);
		
		txtNp = new JTextField();
		txtNp.setText("dla wielomianu 2x^3-3x^2+0x+0 wpisz:  \"2 -3 0 0\" ");
		txtNp.setBounds(12, 54, 320, 19);
		panel.add(txtNp);
		txtNp.setColumns(10);
		
		lblZakres = new JLabel("Zakres");
		lblZakres.setBounds(359, 23, 70, 15);
		panel.add(lblZakres);
		
		txtNp_1 = new JTextField();
		txtNp_1.setText("np. \"-100;100\"");
		txtNp_1.setBounds(344, 54, 100, 19);
		panel.add(txtNp_1);
		txtNp_1.setColumns(10);
		
		lblNewLabel = new JLabel("Probkowanie");
		lblNewLabel.setBounds(456, 23, 100, 15);
		panel.add(lblNewLabel);
		
		txtNp_2 = new JTextField();
		txtNp_2.setText("np. \"0.01\"");
		txtNp_2.setBounds(456, 54, 100, 19);
		panel.add(txtNp_2);
		txtNp_2.setColumns(10);
		
		btnRysuj = new JButton("Rysuj");
		btnRysuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.setWspWiel(txtNp.getText());
				panel_1.setZakres(txtNp_1.getText());
				panel_1.setProbkowanie(txtNp_2.getText());
				if (panel_1.createPoints() == true)
				repaint();
			}
		});
		btnRysuj.setBounds(574, 35, 116, 26);
		panel.add(btnRysuj);
		
		panel_1 = new PolynomialPlotterPanel();
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Wykres wielomianu",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(12, 119, 500, 501);
		panel_1.setBackground(Color.white);
		panel_1.setLayout(null);
		contentPane.add(panel_1);
	}
}
