package Lab6;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.ListIterator;

import java.awt.Panel;

public class MyAWTPanelLab extends Panel {

	private static final long serialVersionUID = 1L;
	public LinkedList<Shape> shapes = new LinkedList<Shape>();

	public MyAWTPanelLab() {
	}
	
	public void paint(Graphics g){
		for (ListIterator<Shape> it = shapes.listIterator(); it.hasNext();)
			it.next().draw(g);
	}

}
