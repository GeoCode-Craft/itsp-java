package de.ifgi.itsp.task2.view;


import de.ifgi.itsp.task2.shapes.Point;
import de.ifgi.itsp.task2.shapes.Label;

import java.util.ArrayList;


public class DrawingContent {


	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<Label> labels = new ArrayList<Label>();

	public ArrayList<Point> getPoints() {
		return points;
	}
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

	public ArrayList<Label> getLabels() {
		return labels;
	}
	public void setLabels(ArrayList<Label> labels) {
		this.labels = labels;
	}



}
