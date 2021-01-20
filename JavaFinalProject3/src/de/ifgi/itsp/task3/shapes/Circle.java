package de.ifgi.itsp.task3.shapes;


public class Circle extends Shape {
    private Point topLeftPoint;
    private double diameter;

    

    public Circle(Point topLeftPoint, double diameter) {
        this.topLeftPoint = topLeftPoint;
        this.diameter = diameter;
    }

    public Circle() { }



    public double area() {
        double radius = this.diameter /2;
        return Math.PI  * Math.pow(radius,2);
    }
    
    public double perimeter() {
        double radius = this.diameter /2;
        return 2* Math.PI  * radius;
    }
    
    public boolean contains(Point value) {
        double radius = this.diameter /2;
        // Compare radius of circle with distance of its center from given point
        Point center = centroid();
        if((value.getX() - center.getX()) * (value.getX() - center.getX()) + 
            (value.getY() - center.getY()) * (value.getY() -  center.getY()) <=radius * radius) 
            return true; 
        else
            return false;
    }
    
    public Point centroid() {
        double x1 = topLeftPoint.getX() + this.diameter;
        double y1 = topLeftPoint.getY() + this.diameter;

        double centroidX =( topLeftPoint.getX() + x1) / 2;  
        double centroidY =(topLeftPoint.getY() + y1) / 2; 

        return new Point(centroidX, centroidY);
    
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public Point getTopLeftPoint() {
        return topLeftPoint;
    }

    public void setTopLeftPoint(Point topLeftPoint) {
        this.topLeftPoint = topLeftPoint;
    }
}

