package de.ifgi.itsp.task2;

import de.ifgi.itsp.task2.shapes.City;
import de.ifgi.itsp.task2.shapes.Point;
import de.ifgi.itsp.task2.utility.Utility;
import de.ifgi.itsp.task2.view.SimpleFrame;

import java.util.List;

public class Main {
    public static int noOfCities = 10;
    public static Point[] citiesLocation = new Point[noOfCities];

    public static void main(String[] args) {

        SimpleFrame frame = new SimpleFrame(1000,1000);

        List<City> listCities = Utility.getCityPointsFromFile();

        City[] arrayCities = new City[listCities.size()];
        /* Get BoundingBox from City data*/

        int counter = 0;
        for(Object obj: listCities) {
            City city = (City) obj;
            arrayCities[counter] = city;
            counter++;

        }
        System.out.println("Bounding box values : ");
        double[] boundingBoxValues = Utility.getBoundingBox(listCities);
        for(double bbox: boundingBoxValues){
            System.out.println(bbox);
        }



        /* Carry out Translations*/
        double xTranslation = Utility.WINDOWCENTER - Utility.bboxCenterX(boundingBoxValues);
        double yTranslation = Utility.WINDOWCENTER - Utility.bboxCenterY(boundingBoxValues);
        City[] translatedCities = new City[arrayCities.length];
        for(int i=0; i< arrayCities.length;i++){
            translatedCities[i] =arrayCities[i];
            System.out.println("---------Before translation----------------");
            System.out.println(translatedCities[i].getLocation());
            translatedCities[i].getLocation().setX(arrayCities[i].getLocation().getX()+xTranslation);
            translatedCities[i].getLocation().setY(arrayCities[i].getLocation().getY()+yTranslation);
            System.out.println("---------After translation----------------");
            System.out.println(translatedCities[i].getLocation());
        }
        System.out.println("----------------------------------------------------------------");

        /* Carry out Flipping using Y value*/
        City[] flippedCities = new City[translatedCities.length];
        double flippingValue = 1000.00;
        for(int i=0; i< arrayCities.length;i++){
            flippedCities[i] =arrayCities[i];
            System.out.println("---------Before Flipping----------------");
            System.out.println(flippedCities[i].getLocation());
            flippedCities[i].getLocation().setX(translatedCities[i].getLocation().getX());
            flippedCities[i].getLocation().setY(flippingValue - translatedCities[i].getLocation().getY());
            System.out.println("---------After Flipping----------------");
            System.out.println(flippedCities[i].getLocation());
        }




        /* Add cities to Plot*/
        for(City city: flippedCities){
            frame.addToPlot(city.getLocation());
        }


        frame.drawAllFeature();


    }
}
