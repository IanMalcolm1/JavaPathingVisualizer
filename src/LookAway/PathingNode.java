package LookAway;
import java.util.ArrayList;


public class PathingNode {
	public int id;
	public Coords location;
	private ArrayList<Integer> neighbors;
	public int cameFrom;
	public boolean visited;
	
	public PathingNode(int x, int y) {
		neighbors = new ArrayList<Integer>();
		location = new Coords(x,y);
		visited = false;
		cameFrom = -1;
		id = -1;
	}
	
	public PathingNode(int x, int y, ArrayList<Integer> neighborIds) {
		neighbors = neighborIds;
		location = new Coords(x,y);
		visited = false;
		cameFrom = -1;
	}
	
	public PathingNode(int x, int y, ArrayList<Integer> neighborIds, int cameFrom, boolean visited) {
		neighbors = neighborIds;
		location = new Coords(x,y);
		this.visited = visited;
		this.cameFrom = cameFrom;
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
	
	public ArrayList<Integer> getNeighbors() {
		return neighbors;
	}
	
	@Override
	public String toString() {
		String str = "";
		if (visited)
			str += "1";
		else
			str += "0";
		
		str += ":"+location.toString()+":";
		for (int neighbor : neighbors) {
			str += neighbor+",";
		}
		
		str += ":"+cameFrom;
		
		str += ";";
		
		return str;
	}
}
