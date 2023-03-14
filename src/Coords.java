public class Coords {
	public double x, y;
	
	Coords(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Coords other) {
		return (x==other.x && y==other.y);
	}
	
	@Override
	public String toString() {
		return x+","+y+":";
	}
}