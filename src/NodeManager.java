import java.util.ArrayList;

public class NodeManager {
	private ArrayList<PathingNode> nodes;
	
	public NodeManager() {
		nodes = new ArrayList<PathingNode>();
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
	
	public void saveSnapshot() {
		String snap = "";
		
		for (int i=0; i<nodes.size(); i++) {
			snap += i + nodes.get(i).toString();
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
	
	
	public PathingNode parseNodeString(String str) {
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
	
	public double calcDistance(PathingNode node1, PathingNode node2) {
		return Math.sqrt(Math.pow(node1.location.x-node2.location.x,2)+Math.pow(node1.location.y-node2.location.y,2));
	}
}


