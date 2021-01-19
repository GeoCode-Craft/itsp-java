package de.ifgi.itsp.task3.view;

import de.ifgi.itsp.task3.shapes.Label;
import de.ifgi.itsp.task3.shapes.Point;
import de.ifgi.itsp.task3.shapes.Polyline;
import de.ifgi.itsp.task3.shapes.Polygon;
import de.ifgi.itsp.task3.shapes.Rectangle;
import de.ifgi.itsp.task3.shapes.Circle;

import javax.swing.*;

public class SimpleFrame extends JFrame{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SimplePanel panel;

	public SimpleFrame(int i, int j) {
		this.setTitle("Simple Cartesian Panel");
		
		panel = new SimplePanel();
		panel.setSize(i, j);
		this.add(panel);
		this.setResizable(true);
	    this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
	    this.setSize(i, j);
	 

	    this.setVisible(true);
	}

	public void addToPlot(Point point) {
		panel.getContent().getPoints().add(point);
	}
	
	public void addToPlot(Polyline p) {
		panel.getContent().getPolylines().add(p);
	}
	
	public void addToPlot(Polygon p) {
		panel.getContent().getPolygons().add(p);
	}
	
	public void addToPlot(Circle c) {
		panel.getContent().getCircles().add(c);
	}
	
	public void addToPlot(Rectangle r) {
		panel.getContent().getRectangles().add(r);
	}
	
	public void addToPlot(Label l) {
		panel.getContent().getLabels().add(l);
	}

	public void drawAllFeature() {
		panel.repaint();
		
	}
	
	



}
