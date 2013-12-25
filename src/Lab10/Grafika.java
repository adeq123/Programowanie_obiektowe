package Lab10;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JApplet;

/**
 *
 * @author krzysztof
 */
public class Grafika extends JApplet {
    
	private static final long serialVersionUID = 1L;
	Lokomotywa L;

    @Override
    public void init() {
        setBackground(Color.orange); // ustawienie koloru tła okna apletu
        setSize(300,500); // zmiana rozmiaru okna apletu
        L = new Lokomotywa();
    }
    
    @Override
    public void paint(Graphics g){
        int[] x = {0, getWidth()/2, getWidth()}; 
        // nowa tablica jest tworzona na podstawie opisu jej elementów,
        int[] y = {0, 60, 0}; 
        // oddzielonych przecinkami i zawartych w nawiasach klamrowych
        Wielobok T = new Wielobok(x, y); 
        // wielobok T jest trójkątem równoramiennym o wysokości 60 punktów
        //klasy Graphics i Font trzeba importować
        g.clearRect(0, 0, getWidth(), getHeight()); 
        //wypełnienie obszaru apletu kolorem tła
        Font f = new Font("SanSerif", Font.ITALIC, 18);
        //utworzenie czcionki o podanej nazwie, stylu i rozmiarze
        g.setFont(f);
        g.setColor(Color.blue);
        String s="RYSOWANIE WIELOBOKÓW";
        T.rysuj(g, Color.WHITE);
        g.drawString(s, (getWidth()-g.getFontMetrics().stringWidth(s))/2, 20); 
        L.rysuj(g, Color.black);
    }
}
