package de.ifgi.itsp.task3.shapes;

public class Polyline extends Shape{
    private Point[] points;
    private int numPoints;


    public Polyline(Point[] points, int numPoints) {
        this.points = points;
        this.numPoints = numPoints;
    }

    public Polyline() {
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public int getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }
}
