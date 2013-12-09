package crossword.program;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.UIManager;

import crossword.gui.MyJFrame;

/**
 * 
 * @author krzysztof
 *
 */
public class Main {
	
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
					myJFrame.setResizable(false);
					Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (screenDimensions.width - myJFrame.getWidth()) / 2;
					int y = (screenDimensions.height - myJFrame.getHeight()) / 2;
					myJFrame.setLocation(x, y);
					myJFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}