package program;

import gui.myJFrame;

import java.awt.EventQueue;

import javax.swing.UIManager;

/**
 * 
 * @author krzysztof
 *
 */
public class ApplicationLauncher {
	
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
					frame.setTitle("Krzysztof Spytkowski - Crossword Application");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
