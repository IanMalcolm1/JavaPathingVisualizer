package ignoreThese;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JFrame;

public class NodeGraphics extends Component {
	ArrayList<PathingNode> currentNodes;
	int circleRadius = 25;
	
	public NodeGraphics() {
		currentNodes = null;
	}
	
	public NodeGraphics(ArrayList<PathingNode> nodes) {
		currentNodes = nodes;
		System.out.println();
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
			
			if (node.cameFrom == -2) {
				renderer.setColor(Color.blue);
			}
			else if (node.visited) {
				renderer.setColor(new Color(24, 226, 0));
			}
			else {
				renderer.setColor(Color.gray);
			}
			
			Coords loc = convertCoords(node.location);
			renderer.fillOval(loc.x-circleRadius, loc.y-circleRadius, circleRadius*2, circleRadius*2);
			
			drawCameFromArrow(renderer, node);
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
	
	private void drawCameFromArrow(Graphics2D renderer, PathingNode node) {
		if (node.cameFrom < 0) {
			return;
		}
		
		Coords start = convertCoords(node.location);
		Coords dest = convertCoords(currentNodes.get(node.cameFrom).location);
		
		double angle = Math.atan2(dest.x-start.x, dest.y-start.y);
		
		Coords tip = new Coords((int)(dest.x - Math.sin(angle)*circleRadius), (int)(dest.y - Math.cos(angle)*circleRadius));
		
		double angle2 = angle + Math.PI/8;
		Coords side1 = new Coords((int)(tip.x-Math.sin(angle2)*circleRadius), (int)(tip.y-Math.cos(angle2)*circleRadius));
		
		double angle3 = angle - Math.PI/8;
		Coords side2 = new Coords((int)(tip.x-Math.sin(angle3)*circleRadius), (int)(tip.y-Math.cos(angle3)*circleRadius));
		
		
		
		Polygon arrow = new Polygon();
		arrow.addPoint(tip.x, tip.y);
		arrow.addPoint(side1.x, side1.y);
		arrow.addPoint(side2.x, side2.y);

		renderer.setColor(Color.orange);
		renderer.fillPolygon(arrow);
	}
	
	private Coords convertCoords(Coords coords) {
		int scale = 30;
		Dimension size = getSize();
		return new Coords(coords.x*scale + size.width/2, size.height/2 - coords.y*scale);
	}
}
