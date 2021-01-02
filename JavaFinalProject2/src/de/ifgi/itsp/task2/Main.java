package de.ifgi.itsp.task2;

import de.ifgi.itsp.task2.shapes.City;
import de.ifgi.itsp.task2.utility.Utility;
import de.ifgi.itsp.task2.view.SimpleFrame;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        SimpleFrame frame = new SimpleFrame(3000,3000);

        /* Get File data*/
        List<City> listCities = Utility.getCityPointsFromFile();


        /* Get BoundingBox from City data*/
        double[] boundingBoxValues = Utility.getBoundingBox(listCities);

        /* Convert Objects to City Array**/
        City[] arrayCities = new City[listCities.size()];
        int counter = 0;
        for(Object obj: listCities) {
            City city = (City) obj;
            arrayCities[counter] = city;
            counter++;

        }


        /* Carry out Scaling*/
        City[] scaledCities = new City[arrayCities.length];
        double scale = 900 / Math.max(Utility.bboxHeight(boundingBoxValues),Utility.bboxWidth(boundingBoxValues));
        for(int i=0; i< arrayCities.length; i++){
            scaledCities[i]=arrayCities[i];
            System.out.println("---------Before Scaling----------------");
            System.out.println(scaledCities[i].getLocation());
            scaledCities[i].getLocation().setX(arrayCities[i].getLocation().getX() * scale);
            scaledCities[i].getLocation().setY(arrayCities[i].getLocation().getY() * scale);
            System.out.println("---------After Scaling----------------");
            System.out.println(scaledCities[i].getLocation());
        }


        /* Carry out Translations*/
        double xTranslation = Utility.WINDOWCENTER - Utility.bboxCenterX(boundingBoxValues);
        double yTranslation = Utility.WINDOWCENTER - Utility.bboxCenterY(boundingBoxValues);
        City[] translatedCities = new City[scaledCities.length];
        for(int i=0; i< scaledCities.length;i++){
            translatedCities[i] =arrayCities[i];
            System.out.println("---------Before translation----------------");
            System.out.println(translatedCities[i].getLocation());
            translatedCities[i].getLocation().setX(scaledCities[i].getLocation().getX()+xTranslation);
            translatedCities[i].getLocation().setY(scaledCities[i].getLocation().getY()+yTranslation);
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
