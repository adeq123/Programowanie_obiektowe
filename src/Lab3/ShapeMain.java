package Lab3;

import java.util.LinkedList;
import java.util.ListIterator;

public class ShapeMain {
	public static void main(String[] args) {
		LinkedList<Shape> Shapes = new LinkedList<Shape>();
		Shapes.add(new Circle());
		Shapes.add(new Square());
		Shapes.add(new Triangle());
		for (ListIterator<Shape> it = Shapes.listIterator(); it.hasNext();)
			it.next().draw();

	}
}
