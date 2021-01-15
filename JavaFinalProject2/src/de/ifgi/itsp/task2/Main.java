package de.ifgi.itsp.task2;

import de.ifgi.itsp.task2.shapes.City;
import de.ifgi.itsp.task2.shapes.Label;
import de.ifgi.itsp.task2.utility.Utility;
import de.ifgi.itsp.task2.view.SimpleFrame;

import java.util.List;

public class Main {

    public static void main(String[] args) {



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
        double boundingBoxHeight = Utility.bboxHeight(boundingBoxValues);
        double boundingBoxWidth = Utility.bboxWidth(boundingBoxValues);

        System.out.println("Bounding Box Height is " + boundingBoxHeight);
        System.out.println("Bounding Box Width is " + boundingBoxWidth);

        double scale =  900/Math.max(boundingBoxHeight,boundingBoxWidth);
        System.out.println("Scale value is " + scale);
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
            flippedCities[i].getLocation().setX(Math.abs(translatedCities[i].getLocation().getX()-2_000));
            flippedCities[i].getLocation().setY(Math.abs(flippingValue - translatedCities[i].getLocation().getY()));
            System.out.println("---------After Flipping----------------");
            System.out.println(flippedCities[i].getLocation());
        }
        /* Set drawing environment*/
        /*
        double padding = 50.0;
        double bBoxHeight = boundingBoxHeight  ; //boundingBoxHeight + padding
        double bBoxWidth = boundingBoxWidth  ;
        scale = Math.max(bBoxHeight,bBoxWidth)/900;
        int height = (int)(1000 * scale);
        int width = (int)(1000 * scale);
        System.out.println("Height is " + height);
        System.out.println("Width is " + width);
        */

        SimpleFrame frame = new SimpleFrame(1200,600);

        /* Add cities Names to Plot*/
        for (int i = 1; i < flippedCities.length; i++) {
            flippedCities[i].getLocation().getX();
            flippedCities[i].getLocation().getY();
            Label cityname = new Label();
            cityname.setText(flippedCities[i].getName());
            cityname.setPosition(flippedCities[i].getLocation());
            frame.addToPlot(cityname);
        }

        /* Add cities to Plot*/
        for(City city: flippedCities){
            frame.addToPlot(city.getLocation());
            System.out.println(city.getName() + " : " + city.getLocation() );
        }


        frame.drawAllFeature();


    }
}
