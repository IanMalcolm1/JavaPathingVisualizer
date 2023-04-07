package ignoreThese;

import javax.swing.JFrame;

public class VisualizerWindow extends JFrame {
	private NodeGraphics nodeGraphics;
	private NodeManager nodeMan;
	
	public VisualizerWindow(NodeManager nodeMan) {
		super("Pathing Visualizer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.nodeMan = nodeMan;
		
		nodeGraphics = new NodeGraphics(nodeMan.getNodes());
		add(nodeGraphics);
		
		this.setVisible(true);
	}
	
	
	//Target interval is number of milliseconds to pause for between updates
	public void update() {
		nodeGraphics.setNodes(nodeMan.getNodes());
		repaint();
	}

}
