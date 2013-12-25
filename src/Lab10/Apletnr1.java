package Lab10;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Apletnr1 extends java.applet.Applet {
	
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
		g.setColor(Color.blue);//ustawianie koloru czcionki
		Font f4=new Font("Courier",Font.BOLD,24);
		String s="Java jest ekstra";
		g.setFont(f4);//ustawianie bierzącej czcionki
		g.drawString(s,10,25);
    }
}