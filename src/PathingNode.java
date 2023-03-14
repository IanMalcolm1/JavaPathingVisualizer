import java.util.ArrayList;


public class PathingNode {
	private ArrayList<PathingNode> neighbors;
	public Coords location;
	public boolean visited;
	
	public PathingNode(int x, int y) {
		location.x = (double)x;
		location.y = (double)y;
	}
	
	public double getDistance(PathingNode neighboor) {
		return Math.sqrt(Math.pow(location.x-neighboor.location.x,2)+Math.pow(location.y-neighboor.location.y,2));
	}
}
