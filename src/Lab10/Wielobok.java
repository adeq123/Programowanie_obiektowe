package Lab10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 *
 * @author krzysztof
 */
public class Wielobok extends Polygon {

	private static final long serialVersionUID = 1L;
	
	Wielobok(int[] x, int[] y) {
        super(x, y, Math.min(x.length, y.length));
        //wywołanie konstruktora klasy Polygon
    } 
    //trzeci parametr to liczba wierzchołków – 
    //wyliczana jako minimum z długości obu tablic współrzędnych
    
    void rysuj(Graphics g, Color k) {
        Color b = g.getColor(); g.setColor(k);
        //zapamiętanie i zmiana bieżącego koloru dla kanwy graficznej
        g.fillPolygon(this); 
        // narysowanie wieloboku o kształcie obiektu klasy Wielobok (Polygon)
        g.setColor(b); // przywrócenie bieżącego koloru kanwy
    }
}