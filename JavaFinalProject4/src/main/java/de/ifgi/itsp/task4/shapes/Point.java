package de.ifgi.itsp.task4.shapes;

public class Point extends Shape{
    private double x;
    private double y;
    private double scaledX;
    private double scaledY;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
	}

	public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }

    public String toString(){

        return  "Point (" + this.x + "," + this.y + ")";
       
    }

    public double distance(Point point){
        double distanceValue;
        distanceValue = Math.sqrt((this.x - point.getX()) *(this.x - point.getX()) + (this.y - point.getY()) * (this.y - point.getY()));
        return distanceValue; 
    }

    public double getX(){
        return this.x;
    }

    public void setX(double x){
        this.x = x;
    }

    public double getY(){

        return this.y;
    }

    public void setY(double y){
        this.y = y;
    }

}
