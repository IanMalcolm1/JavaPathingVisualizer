package LookAway;
public class Coords {
	public int x, y;
	
	Coords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Coords other) {
		return (x==other.x && y==other.y);
	}
	
	@Override
	public String toString() {
		return x+","+y;
	}
}