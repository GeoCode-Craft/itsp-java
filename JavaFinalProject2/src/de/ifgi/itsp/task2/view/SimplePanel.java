package de.ifgi.itsp.task2.view;



import de.ifgi.itsp.task2.shapes.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SimplePanel extends JPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private DrawingContent content;
	public SimplePanel() {
		super();
		content = new DrawingContent();

	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);


		this.setBackground(Color.WHITE);
		/* Change the drawer and their attributes */
		Graphics2D g2d = ( Graphics2D )g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);




		/* DRAW POINTS */
		for(Point p:content.getPoints()){
			g2d.setColor( p.getStrokeColor());

			g2d.setStroke(new BasicStroke((float)p.getStrokeWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
			/* Scaled X and Scaled Y from Point*/
			Ellipse2D.Double shape = new Ellipse2D.Double(p.getX() ,p.getY(), 5.0, 5.0 );
			g2d.draw(shape);

			g2d.setColor( p.getFillColor());
			g2d.fill(shape);

		}



	}


	public DrawingContent getContent() {
		return content;
	}


	public void setContent(DrawingContent content) {
		this.content = content;
	}



}