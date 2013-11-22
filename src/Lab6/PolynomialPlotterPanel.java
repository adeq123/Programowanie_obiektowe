package Lab6;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class PolynomialPlotterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private String wspWiel = null;
	private String zakres = null;
	private Double poczatekX;
	private Double koniecX;
	private Double minimumY;
	private Double maksimumY;
	private String probkowanie = null;
	private Double prob;
	private LinkedList<Double> punktX;
	private LinkedList<Double> punktY;
	
	public PolynomialPlotterPanel(){
		setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Wykres wielomianu",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setBounds(22, 120, 500, 501);
		setBackground(Color.white);
		setLayout(null);
	}

	public boolean createPoints() {
		if (wspWiel == null || zakres == null || probkowanie == null)
			return false;
		else {
			punktX = new LinkedList<Double>();
			punktY = new LinkedList<Double>();
			String[] a = zakres.split(";");
			poczatekX = Double.parseDouble(a[0]);
			koniecX = Double.parseDouble(a[1]);
			prob = Double.valueOf(probkowanie);

			String[] w = wspWiel.split(" ");
			for (Double i = poczatekX; i < koniecX; i += prob) {
				punktX.add(i);
				Double wartosc = 0.;
				for (int j = 0; j < w.length; j++) {
					wartosc += Double.parseDouble(w[w.length - j - 1])
							* ((Double) Math.pow(i, j));
				}
				punktY.add(wartosc);
			}

			minimumY = 0.;
			maksimumY = 0.;
			Iterator<Double> it = punktY.iterator();
			double d;
			while (it.hasNext() == true) {
				d = it.next();
				if (d > maksimumY)
					maksimumY = d;
				if (d < minimumY)
					minimumY = d;
			}

			if (Math.abs(maksimumY) > Math.abs(minimumY))
				minimumY = -maksimumY;
			if (Math.abs(minimumY) > Math.abs(maksimumY))
				maksimumY = -minimumY;
		}
		return true;
	}

	public void drawPoints(Graphics g) {
		ListIterator<Double> itY = punktY.listIterator(0);
		for (int i = 0; i < punktX.size() - 1;) {
			int iks = (int) (i / (((koniecX - poczatekX) / prob) / 500));
			int igrek = (int) ((((double) (itY.next())) / (double) (maksimumY - minimumY)) * 500);
			i++;
			int iks2 = (int) (i / (((koniecX - poczatekX) / prob) / 500));
			int igrek2 = (int) ((((double) (itY.next())) / (double) (maksimumY - minimumY)) * 500);
			g.drawLine(iks, 250 - igrek, iks2, 250 - igrek2);
			itY.previous();
		}

		Double x = poczatekX;
		Double y = minimumY;
		for (int j = 0; j < 10; j++) {
			String xx = Double.toString(x);
			if (xx.length() > 5)
				if (x < 0.000001 && x > -0.000001) {
					g.drawString("0.0", j * this.getWidth() / 10, 270);
				} else {
					g.drawString(Double.toString(x).substring(0, 4),
							j * this.getWidth() / 10, 270);
				}
			else {
				g.drawString(Double.toString(x), j * this.getWidth() / 10, 270);
			}
			x += (koniecX - poczatekX) / 10;
			String yy = Double.toString(y);
			if (yy.length() > 5) {
				if (y < 0.000001 && y > -0.000001) {
					g.drawString("0.0", 215, 500 - (j * this.getHeight() / 10));
				} else {
					g.drawString(Double.toString(y).substring(0, 4), 215,
							500 - (j * this.getHeight() / 10));
				}
			} else {
				g.drawString(Double.toString(y), 215,
						500 - (j * this.getHeight() / 10));
			}
			y += (maksimumY - minimumY) / 10;
		}
	}

	public String getWspWiel() {
		return wspWiel;
	}

	public void setWspWiel(String wspWiel) {
		this.wspWiel = wspWiel;
	}

	public String getZakres() {
		return zakres;
	}

	public void setZakres(String zakres) {
		this.zakres = zakres;
	}

	public String getProbkowanie() {
		return probkowanie;
	}

	public void setProbkowanie(String probkowanie) {
		this.probkowanie = probkowanie;
	}

	public void paint(Graphics g) {
		super.paint(g); //zawsze to pisz!!
		g.drawLine(0, this.getHeight() / 2, this.getWidth(),
				this.getHeight() / 2);
		g.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2,
				this.getHeight());
		g.drawLine(this.getWidth() - 5, this.getHeight() / 2 - 5,
				this.getWidth(), this.getHeight() / 2);
		g.drawLine(this.getWidth() - 5, this.getHeight() / 2 + 5,
				this.getWidth(), this.getHeight() / 2);
		g.drawLine(this.getWidth() / 2 - 5, 5, this.getWidth() / 2, 0);
		g.drawLine(this.getWidth() / 2 + 5, 5, this.getWidth() / 2, 0);
		for (int i = 0; i < 10; i++) {
			g.drawLine(i * this.getWidth() / 10, this.getHeight() / 2 - 5, i
					* this.getWidth() / 10, this.getHeight() / 2 + 5);
			g.drawLine(this.getWidth() / 2 - 5,
					500 - (i * this.getHeight() / 10), this.getWidth() / 2 + 5,
					500 - (i * this.getHeight() / 10));
		}
		if (createPoints() != false) {
			drawPoints(g);
		}
	}
}
