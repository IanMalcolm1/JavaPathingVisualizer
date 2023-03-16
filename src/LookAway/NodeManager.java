package LookAway;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NodeManager {
	private ArrayList<PathingNode> nodes;
	
	public NodeManager() {
		nodes = new ArrayList<PathingNode>();
	}
	
	public NodeManager(String fileName) {
		File nodesFile = new File(fileName);
		nodes = new ArrayList<PathingNode>();
		
		if (!nodesFile.isFile()) {
			return;
		}
		
		try {
			
			Scanner nodesScanner = new Scanner(nodesFile);
			if (nodesScanner.hasNextLine()) {
				nodes = parseNodeStringzuh(nodesScanner.nextLine());
			}
			nodesScanner.close();
			
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
	
	public ArrayList<PathingNode> getNodes() {
		return nodes;
	}
	
	private String makeSnapshot() {
		String snap = "";
		
		for (int i=0; i<nodes.size(); i++) {
			snap += nodes.get(i).toString();
		}
		snap += "\n";
		return snap;
	}
	
	
	public void saveNodes(String filename) {
		File saveFile = new File(filename);
		if (saveFile.isFile()) {
			saveFile.delete();
		}
		
		String snap = makeSnapshot();
		
		try {
			FileWriter writer = new FileWriter(saveFile);
			writer.write(snap);
			writer.close();	
		}
		catch(IOException e) {
			System.out.println("You're fucked");
			e.printStackTrace();
		}
	}
	
	public void saveSnapshot(String filename) {
		String snap = makeSnapshot();
		File history = new File("history.txt");
		
		try {
			FileWriter writer = new FileWriter(history);
			writer.write(snap+"\n");
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

		node.id = nodes.size();
		nodes.add(node);
	}
	
	
	private PathingNode parseNodeString(String str) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		int x, y;
		boolean visited = false;
		
		if (str.charAt(0)=='1') {
			visited = true;
		}
		
		String[] split1 = str.split(":");
		
		String[] coords = split1[1].split(",");
		x = Integer.parseInt(coords[0]);
		y = Integer.parseInt(coords[1]);
		
		if (split1.length<3) {
			return new PathingNode(x,y);
		}
		
		String[] neighborsSplit = split1[2].split(",");
		for (String neighbor : neighborsSplit) {
			if (neighbor.length()<1) {
				continue;
			}
			neighbors.add(Integer.parseInt(neighbor));
		}

		int cameFrom = Integer.parseInt(split1[3]);
		
		return new PathingNode(x,y,neighbors,cameFrom, visited);
	}
	
	private ArrayList<PathingNode> parseNodeStringzuh(String str) {
		ArrayList<PathingNode> nodezuh = new ArrayList<PathingNode>();
		
		String[] splitty = str.split(";");
		
		for (String nodeStr : splitty) {
			PathingNode node = parseNodeString(nodeStr);
			node.id = nodes.size();
			nodezuh.add(node);
		}
		
		return nodezuh;
	}
	
	public double calcDistance(PathingNode node1, PathingNode node2) {
		return Math.sqrt(Math.pow(node1.location.x-node2.location.x,2)+Math.pow(node1.location.y-node2.location.y,2));
	}
	
	
	/*
	 * Pass null if you don't want to save this configuration.
	 */
	public void createSimpleGraph(String filename) {
		nodes.clear();
		
		PathingNode node = new PathingNode(0,0);
		int[] n0 = {1,2,3,4};
		node.addNeighbors(n0);
		addNode(node);
		
		node = new PathingNode(0,5);
		int[] n1 = {0,5};
		node.addNeighbors(n1);
		addNode(node);
		
		node = new PathingNode(5,0);
		int[] n2 = {0,5};
		node.addNeighbors(n2);
		addNode(node);
		
		node = new PathingNode(0,-10);
		int[] n3 = {0,6};
		node.addNeighbors(n3);
		addNode(node);
		
		node = new PathingNode(-7,0);
		int[] n4 = {0};
		node.addNeighbors(n4);
		addNode(node);
		
		node = new PathingNode(5,5);
		int[] n5 = {1,2};
		node.addNeighbors(n5);
		addNode(node);
		
		node = new PathingNode(0,-13);
		int[] n6 = {3,7};
		node.addNeighbors(n6);
		addNode(node);
		
		node = new PathingNode(-5,-13);
		int[] n7 = {6};
		node.addNeighbors(n7);
		addNode(node);
		
		node = new PathingNode(13,-13);
		addNode(node);
		
		if (filename != null) {
			saveNodes(filename);
		}
	}
}


