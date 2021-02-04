package de.ifgi.itsp.task4.shapes;

public class Label extends Shape{
	
	private Point position;
	private String text;
	

	public Label() {
		super();
		this.position = position;
		this.text = text;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	

}
