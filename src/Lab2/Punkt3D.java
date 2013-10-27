package Lab2;

public class Punkt3D extends Punkt2D {
	protected double z;

	public void setZ(double z) {
		this.z = z;
	}

	public double getZ() {
		return z;

	}

	public Punkt3D(double x, double y, double z) {
		super(x, y);
		this.z = z;
	}

	public double distance(Punkt3D other) {
		return Math.sqrt(Math.pow(getX() - other.getX(), 2)
				+ Math.pow(getY() - other.getY(), 2)
				+ Math.pow(getZ() - other.getZ(), 2));
	}
	
	public String toString(){
		return "Wspó³rzêdne punktu (x,y,z) to (" + x +"," + y + "," + z + ")"; 
	}
}
