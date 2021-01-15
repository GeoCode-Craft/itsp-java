package de.ifgi.itsp.task2.view;

import de.ifgi.itsp.task2.shapes.Point;
import de.ifgi.itsp.task2.shapes.Label;

import javax.swing.*;

public class SimpleFrame extends JFrame{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SimplePanel panel;

	public SimpleFrame(int i, int j) {
		this.setTitle("Ten (10) Cities in Europe");
		
		panel = new SimplePanel();
		panel.setSize(i, j);
		//Padding
		panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		this.add(panel);
		this.setResizable(true);
	    this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
	    this.setSize(i, j);




		this.setVisible(true);
	}

	public void addToPlot(Point point) {
		panel.getContent().getPoints().add(point);
	}

	public void addToPlot(Label l) {
		panel.getContent().getLabels().add(l);
	}



	public void drawAllFeature() {
		panel.repaint();
		
	}
	
	



}
