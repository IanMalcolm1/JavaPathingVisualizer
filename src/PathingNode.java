import java.util.ArrayList;


public class PathingNode {
	private ArrayList<Integer> neighbors;
	public Coords location;
	public boolean visited;
	
	public PathingNode(int x, int y) {
		neighbors = new ArrayList<Integer>();
		location = new Coords(x,y);
		visited = false;
	}
	
	public PathingNode(double x, double y, boolean visited, ArrayList<Integer> neighborIds) {
		neighbors = neighborIds;
		location = new Coords(x,y);
		this.visited = visited;
	}
	
	
	public void addNeighbor(int neighbor) {
		for (int vecino : neighbors) {
			if (vecino == neighbor) {
				System.out.println("This neighboor already exists, dum-dum");
				return;
			}
		}
		
		neighbors.add(neighbor);
	}
	
	public void addNeighbors(int[] vecinos) {
		for (int vecino : vecinos) {
			addNeighbor(vecino);
		}
	}
	
	@Override
	public String toString() {
		String str = "";
		if (visited)
			str += "1";
		else
			str += "0";
		
		str += " "+location.toString()+" ";
		for (int neighbor : neighbors) {
			str += neighbor+",";
		}
		
		str += ";";
		
		return str;
	}
}
