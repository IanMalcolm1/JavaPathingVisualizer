package main;
import java.util.LinkedList;

import ignoreThese.NodeManager;
import ignoreThese.PathingNode;
import ignoreThese.VisualizerWindow;


public class Main {
	private VisualizerWindow window;
	private NodeManager nodeMan;
	
	
	public static void main(String[] args) {
		Main main = new Main();
	}
	
	
	public Main() {
		nodeMan = new NodeManager();
		nodeMan.createSquareGrid();
		
		window = new VisualizerWindow(nodeMan);
		
		breadthFirstSearch();
	}
	
	
	public void breadthFirstSearch() {
		LinkedList<PathingNode> frontier = new LinkedList<PathingNode>();
		
		int targetNode = 24;
		
		nodeMan.labelStartNode(targetNode);
		nodeMan.getNode(targetNode).visited = true;
		frontier.add(nodeMan.getNode(targetNode));
		
		while (!frontier.isEmpty()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			window.update();
			
			
			PathingNode curr = frontier.remove();
			
			for (int neighborId : curr.getNeighbors()) {
				PathingNode neighbor = nodeMan.getNode(neighborId);
				if (neighbor.visited) {
					continue;
				}
				neighbor.cameFrom = curr.id;
				neighbor.visited = true;
				frontier.add(neighbor);
			}
		}
		
		window.update();
	}
}
