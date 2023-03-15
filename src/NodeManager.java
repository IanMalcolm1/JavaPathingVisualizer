import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NodeManager {
	private ArrayList<PathingNode> nodes;
	
	public NodeManager() {
		File nodesFile = new File("nodes.txt");
		try {
			Scanner nodesScanner = new Scanner(nodesFile);
			nodes = parseNodeStringzuh(nodesScanner.nextLine());
			
		} catch (FileNotFoundException e) {
			System.out.println("My tears run red");
			e.printStackTrace();
		}
	}
	
	
	public PathingNode getNode(Coords location) {
		for (PathingNode node : nodes) {
			if (node.location.equals(location)) {
				return node;
			}
		}
		return null;
	}
	public PathingNode getNode(int index) {
		return nodes.get(index);
	}
	
	private String makeSnapshot() {
		String snap = "";
		
		for (int i=0; i<nodes.size(); i++) {
			snap += i + nodes.get(i).toString();
		}
		snap += "\n";
		return snap;
	}
	
	/*This one overwrites your nodes.txt file!!!*/
	public void saveNodes() {
		File buffer = new File("buffer.txt");
		File nodes = new File("nodes.txt");
		
		String snap = makeSnapshot();
		
		try {
			FileWriter writer = new FileWriter(buffer);
			writer.write(snap);
			writer.close();
			buffer.renameTo(nodes);			
		}
		catch(IOException e) {
			System.out.println("You're fucked");
			e.printStackTrace();
		}
	}
	
	public void saveSnapshot() {
		String snap = makeSnapshot();
		File history = new File("history.txt");
		
		try {
			FileWriter writer = new FileWriter(history);
			writer.write(snap);
			writer.close();
		}
		catch(IOException e) {
			System.out.println("You're fucked");
			e.printStackTrace();
		}
		
	}
	
	public void addNode(PathingNode node) {
		for (PathingNode existingNode : nodes) {
			if (existingNode.location.equals(node.location)) {
				System.out.println("This node already exists, dum-dum");
				return;
			}
		}
		
		nodes.add(node);
	}
	
	
	private PathingNode parseNodeString(String str) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		Double x, y;
		boolean visited = false;
		
		if (str.charAt(0)=='1') {
			visited = true;
		}
		
		String[] split1 = str.split(" ");
		
		String[] coords = split1[1].split(",");
		x = Double.parseDouble(coords[0]);
		y = Double.parseDouble(coords[1].substring(0, coords[1].length()-1));
		
		String[] neighborsSplit = split1[2].split(",");
		for (String neighbor : neighborsSplit) {
			if ( neighbor.charAt(neighbor.length()-1) == ';' ) {
				neighbor = neighbor.substring(0, neighbor.length()-1);
			}
			if (neighbor.length()<1) {
				continue;
			}
			neighbors.add(Integer.parseInt(neighbor));
		}
		
		return new PathingNode(x,y,visited,neighbors);
	}
	
	private ArrayList<PathingNode> parseNodeStringzuh(String str) {
		ArrayList<PathingNode> nodezuh = new ArrayList<PathingNode>();
		
		String[] splitty = str.split(";");
		
		for (String nodeStr : splitty) {
			nodezuh.add(parseNodeString(nodeStr));
		}
		
		return nodezuh;
	}
	
	public double calcDistance(PathingNode node1, PathingNode node2) {
		return Math.sqrt(Math.pow(node1.location.x-node2.location.x,2)+Math.pow(node1.location.y-node2.location.y,2));
	}
}


