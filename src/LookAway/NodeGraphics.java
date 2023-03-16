package LookAway;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;

public class NodeGraphics extends Component {
	ArrayList<PathingNode> currentNodes;
	
	public NodeGraphics() {
		currentNodes = null;
	}
	
	public NodeGraphics(ArrayList<PathingNode> nodes) {
		currentNodes = nodes;
	}
	
	public void setNodes(int lineNumber) {
		//TODO: load node list from history file
	}
	
	public void setNodes(ArrayList<PathingNode> nodes) {
		currentNodes = nodes;
	}
	
	public void paint(Graphics g) {
		Graphics2D renderer = (Graphics2D)g;
		
		for (int i=0; i<currentNodes.size(); i++) {
			PathingNode node = currentNodes.get(i);
			
			drawLines(renderer, node, i);
			
			if (node.visited) {
				renderer.setColor(new Color(24, 226, 0));
			}
			else {
				renderer.setColor(Color.gray);
			}
			
			Coords loc = convertCoords(node.location);
			renderer.fillOval(loc.x-25, loc.y-25, 50, 50);
		}
	}
	
	private void drawLines(Graphics2D renderer, PathingNode node, int i) {
		Coords loc1 = convertCoords(node.location);
		
		for (int nodeID : node.getNeighbors()) {
			if (nodeID < i)
				continue;
			
			PathingNode neighbor = currentNodes.get(nodeID);
			
			if (node.visited && neighbor.visited) {
				renderer.setColor(new Color(255, 183, 74));
			}
			else {
				renderer.setColor(Color.lightGray);
			}
			
			Coords loc2 = convertCoords(neighbor.location);
			
			renderer.setStroke(new BasicStroke(5));
			renderer.drawLine(loc1.x, loc1.y, loc2.x, loc2.y);
		}
	}
	
	private Coords convertCoords(Coords coords) {
		int scale = 20;
		Dimension size = getSize();
		return new Coords(coords.x*scale + size.width/2, size.height/2 - coords.y*scale);
	}
}
