package main;
import java.util.LinkedList;
import javax.swing.JFrame;

import ignoreThese.NodeGraphics;
import ignoreThese.NodeManager;
import ignoreThese.PathingNode;


public class PathingVisualizer {
	private JFrame window;
	private NodeGraphics graphics;
	private NodeManager nodeMan;
	
	
	public static void main(String[] args) {
		PathingVisualizer visualizer = new PathingVisualizer();
		visualizer.init();
		visualizer.run();
	}
	
	
	
	public void init() {
		nodeMan = new NodeManager();
		nodeMan.createSimpleGraph("test.txt");
		
		graphics = new NodeGraphics(nodeMan.getNodes());
		
		window = new JFrame("Pathfinding Visualizer");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	public void run() {
		window.add(graphics);
		
		breadthFirstSearch();
	}
	
	public void breadthFirstSearch() {
		boolean doPause = true;
		
		LinkedList<PathingNode> frontier = new LinkedList<PathingNode>();
		
		int goalNode = 7;
		
		nodeMan.setStartNode(goalNode);
		nodeMan.getNode(goalNode).visited = true;
		frontier.add(nodeMan.getNode(goalNode));
		
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
			
			for (int neighborId : curr.getNeighbors()) {
				PathingNode neighbor = nodeMan.getNode(neighborId);
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
