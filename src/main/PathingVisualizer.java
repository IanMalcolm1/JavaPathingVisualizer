package main;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JFrame;
import LookAway.NodeGraphics;
import LookAway.NodeManager;
import LookAway.PathingNode;


public class PathingVisualizer {
	public static void main(String[] args) {
		PathingVisualizer visualizer = new PathingVisualizer();
	}
	
	
	private JFrame window;
	private NodeGraphics graphics;
	private NodeManager nodeMan;
	
	public PathingVisualizer() {
		nodeMan = new NodeManager("test.txt");
		graphics = new NodeGraphics(nodeMan.getNodes());
		
		
		window = new JFrame("Pathfinding Visualizer");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		window.add(graphics);
		
		breadthFirstSearch();
	}
	
	public void breadthFirstSearch() {
		boolean doPause = true;
		
		LinkedList<PathingNode> frontier = new LinkedList<PathingNode>();
		nodeMan.getNode(7).visited = true;
		frontier.add(nodeMan.getNode(7));
		
		while (!frontier.isEmpty()) {
			if (doPause) {
				try {
					Thread.sleep(1000);
					window.repaint();
					doPause = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			PathingNode curr = frontier.remove();
			
			for (int nId : curr.getNeighbors()) {
				PathingNode neighbor = nodeMan.getNode(nId);
				if (neighbor.visited) {
					continue;
				}
				neighbor.cameFrom = curr.id;
				neighbor.visited = true;
				frontier.add(neighbor);
				doPause = true;
			}
		}
	}
}
