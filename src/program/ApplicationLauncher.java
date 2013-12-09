package program;

import gui.MyJFrame;

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
					MyJFrame myJFrame = new MyJFrame();
					myJFrame.setTitle("Krzysztof Spytkowski - Crossword Application");
					myJFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}