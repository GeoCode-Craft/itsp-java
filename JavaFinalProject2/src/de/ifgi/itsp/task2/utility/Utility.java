package de.ifgi.itsp.task2.utility;

import de.ifgi.itsp.task2.shapes.Point;

import java.util.Arrays;

public class Utility {

    public static double minX(Point[] locationData){
        Double[] arrayX = new Double[locationData.length];

        for(int i=0;i< locationData.length; i++){
            arrayX[i]=locationData[i].getX();
        }
        Arrays.sort(arrayX);

        return  arrayX[0];
    }


    public static double minY(Point[] locationData){
        Double[] arrayY = new Double[locationData.length];

        for(int i=0;i< locationData.length; i++){
            arrayY[i]=locationData[i].getY();
        }
        Arrays.sort(arrayY);

        return  arrayY[0];
    }

    public static double maxX(Point[] locationData){
        Double[] arrayX = new Double[locationData.length];

        for(int i=0;i< locationData.length; i++){
            arrayX[i]=locationData[i].getX();
        }
        Arrays.sort(arrayX);

        return  arrayX[arrayX.length-1];
    }

    public static double maxY(Point[] locationData){
        Double[] arrayY = new Double[locationData.length];

        for(int i=0;i< locationData.length; i++){
            arrayY[i]=locationData[i].getY();
        }
        Arrays.sort(arrayY);

        return  arrayY[arrayY.length-1];
    }

    public static double bboxHeight(Point[] locationData){
        return maxY(locationData) - minY(locationData);
    }

    public static double bboxWidth(Point[] locationData){
        return maxX(locationData) - minX(locationData);
    }

    public static  double[] paddings(Point[] locationData){
        double[] paddingsArrray = new double[4];
        double paddingValue = 50.0;
        double x1 = minX(locationData) -paddingValue;
        paddingsArrray[0] = x1;
        double y1 = minY(locationData) -paddingValue;
        paddingsArrray[1] = y1;
        double x2=maxX(locationData)+paddingValue;
        paddingsArrray[2] = x2;
        double y2 = maxY(locationData)+paddingValue;
        paddingsArrray[3] = y2;

        return  paddingsArrray;
    }

}
