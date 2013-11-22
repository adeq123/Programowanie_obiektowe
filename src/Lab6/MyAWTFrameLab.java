package Lab6;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MyAWTFrameLab extends Frame {

	private static final long serialVersionUID = 1L;
	private Panel contentPane;
	private MyAWTPanelLab myPanelLab;
	private Panel myPanelControl;
	private Button btnDrawLine;
	private Button btnNewButton;
	private Button btnDrawOval;
	private Random rand = new Random();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyAWTFrameLab frame = new MyAWTFrameLab();
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
	public MyAWTFrameLab() {
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
               System.exit(0);
            }
        });
		
		setBounds(100, 100, 450, 330);
		contentPane = new Panel();
		contentPane.setLayout(null);
		this.add(contentPane);

		myPanelLab = new MyAWTPanelLab();
		myPanelLab.setBounds(12, 47, 426, 241);
		contentPane.add(myPanelLab);

		myPanelControl = new Panel();
		myPanelControl.setBounds(12, 0, 426, 35);
		contentPane.add(myPanelControl);

		btnDrawLine = new Button("Draw Line");
		btnDrawLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myPanelLab.shapes.add(new Line(rand.nextInt(myPanelLab
						.getWidth()), rand.nextInt(myPanelLab.getHeight()),
						rand.nextInt(myPanelLab.getWidth()), rand
								.nextInt(myPanelLab.getHeight())));
				myPanelLab.repaint();
			}
		});
		myPanelControl.add(btnDrawLine);

		btnDrawOval = new Button("Draw Oval");
		btnDrawOval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = rand.nextInt(myPanelLab.getWidth());
				int y = rand.nextInt(myPanelLab.getHeight());
				myPanelLab.shapes.add(new Oval(x, y, rand
						.nextInt(myPanelLab.getWidth()-x), rand
						.nextInt(myPanelLab.getHeight()-y)));
				myPanelLab.repaint();
			}
		});
		myPanelControl.add(btnDrawOval);

		btnNewButton = new Button("Draw Rectangle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = rand.nextInt(myPanelLab.getWidth());
				int y = rand.nextInt(myPanelLab.getHeight());
				myPanelLab.shapes.add(new Rectangle(x, y, rand
						.nextInt(myPanelLab.getWidth()-x), rand
						.nextInt(myPanelLab.getHeight()-y)));
				myPanelLab.repaint();
			}
		});
		myPanelControl.add(btnNewButton);

	}
}
