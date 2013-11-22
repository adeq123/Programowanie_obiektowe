package Lab6;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.ListIterator;

import java.awt.Panel;

public class MyAWTPanelLab extends Panel {

	private static final long serialVersionUID = 1L;
	public LinkedList<Shape> shapes = new LinkedList<Shape>();

	public MyAWTPanelLab() {
		setBackground(Color.WHITE);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		for (ListIterator<Shape> it = shapes.listIterator(); it.hasNext();)
			it.next().draw(g);
	}

}
