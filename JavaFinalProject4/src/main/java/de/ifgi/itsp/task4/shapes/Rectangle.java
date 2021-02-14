package de.ifgi.itsp.task4.shapes;

public class Rectangle extends Shape{
    private Point topLeftPoint;
    private double width;
    private double height;

    public Rectangle(Point topLeftPoint, double width, double height){
        this.topLeftPoint = topLeftPoint;// new Point();
        this.width = width;
        this.height = height;
    }

    public Rectangle(){ }

    public double area() {
        return this.width * this.height;
    }

    public double perimeter() {
        double perimeterValue;
        perimeterValue = 2 * (this.width + this.height);
        return perimeterValue;
    }

    public boolean contains(Point value) {
        if(value.getX() > this.topLeftPoint.getX() && value.getX() < (this.topLeftPoint.getX() + this.width) 
           && value.getY() > this.topLeftPoint.getY() && value.getY() < (this.topLeftPoint.getY() + this.height)){
          return true;
        }
        return false;
    }

    public Point centroid() {
            //Center of rectangle
            double centerX = (this.topLeftPoint.getX() + this.width) / 2;
            double centerY = (this.topLeftPoint.getX() + this.height) / 2;
            return new Point(centerX, centerY);

    }

    //Getters and Setters

    public Point getTopLeftPoint() {
        return topLeftPoint;
    }

    public void setTopLeftPoint(Point topLeftPoint) {
        this.topLeftPoint = topLeftPoint;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    
}
