package Lab2;

public class Punkt2D {
	protected double x;
	protected double y;

	Punkt2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double distance(Punkt2D other) {
		return Math.sqrt(Math.pow(getX() - other.getX(), 2)
				+ Math.pow(getY() - other.getY(), 2));
	}

}
