package Lab6;

import java.awt.Graphics;

public class Rectangle extends Shape {

	private int x;
	private int y;
	private int width;
	private int height;
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}

}
