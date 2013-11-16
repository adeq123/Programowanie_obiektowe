package Lab6;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class MyFrameLab extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MyPanelLab myPanelLab;
	private JPanel myPanelControl;
	private JButton btnDrawLine;
	private JButton btnNewButton;
	private JButton btnDrawOval;
	private Random rand = new Random();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrameLab frame = new MyFrameLab();
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
	public MyFrameLab() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("ComboBox.border"));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		myPanelLab = new MyPanelLab();
		myPanelLab.setBounds(12, 47, 426, 241);
		contentPane.add(myPanelLab);
		System.out.println(rand.nextInt(5));
		// System.out.println(rand.nextInt(myPanelLab.getWidth()));

		myPanelControl = new JPanel();
		myPanelControl.setBounds(12, 0, 426, 35);
		contentPane.add(myPanelControl);

		btnDrawLine = new JButton("Draw Line");
		btnDrawLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myPanelLab.shapes.add(new Line(rand.nextInt(myPanelLab
						.getWidth()), rand.nextInt(myPanelLab.getHeight()),
						rand.nextInt(myPanelLab.getWidth()), rand
								.nextInt(myPanelLab.getHeight())));
				repaint();
			}
		});
		myPanelControl.add(btnDrawLine);

		btnDrawOval = new JButton("Draw Oval");
		btnDrawOval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = rand.nextInt(myPanelLab.getWidth());
				int y = rand.nextInt(myPanelLab.getHeight());
				myPanelLab.shapes.add(new Oval(x, y, rand
						.nextInt(myPanelLab.getWidth()-x), rand
						.nextInt(myPanelLab.getHeight()-y)));
				repaint();
			}
		});
		myPanelControl.add(btnDrawOval);

		btnNewButton = new JButton("Draw Rectangle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = rand.nextInt(myPanelLab.getWidth());
				int y = rand.nextInt(myPanelLab.getHeight());
				myPanelLab.shapes.add(new Rectangle(x, y, rand
						.nextInt(myPanelLab.getWidth()-x), rand
						.nextInt(myPanelLab.getHeight()-y)));
				repaint();
			}
		});
		myPanelControl.add(btnNewButton);

	}
}
