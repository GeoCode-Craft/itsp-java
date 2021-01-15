package de.ifgi.itsp.task2;

import de.ifgi.itsp.task2.shapes.Point;
import de.ifgi.itsp.task2.shapes.City;
import de.ifgi.itsp.task2.shapes.Label;
import de.ifgi.itsp.task2.utility.Utility;
import de.ifgi.itsp.task2.view.SimpleFrame;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        /* Define Environment*/
        SimpleFrame frame = new SimpleFrame(1000,1000);
        Point windowCenter = new Point();
        windowCenter.setX(500);
        windowCenter.setY(500);
        Point bBoxCenter = new Point();

        /* Get City Data from File */
        List<City> listCities = Utility.getCityPointsFromFile();


        /* Convert Objects to City Array**/
        City[] arrayCities = new City[listCities.size()];
        int counter = 0;
        for(Object obj: listCities) {
            City city = (City) obj;
            arrayCities[counter] = city;
            counter++;

        }

        /* Get BoundingBox from City data*/
        double[] boundingbox = Utility.getBoundingBox(listCities);
        double maxY = boundingbox[3];
        double minY = boundingbox[1];
        double maxX = boundingbox[2];
        double minX =boundingbox[0];

        minX -= 10;
        maxX += 10;
        minY -= 10;
        maxY += 10;


        /* Carry out Scaling and add city label to Gui*/
        double bboxHeight = Utility.bboxHeight(boundingbox);
        double bboxWidth = Utility.bboxHeight(boundingbox);
        double scale = 900/Math.max(bboxHeight , bboxWidth);
        Point topLeftPoint = new Point();
        topLeftPoint.setX(minX);
        topLeftPoint.setY(maxY);
        for (int i = 0; i < arrayCities.length; i++) {
            arrayCities[i].getLocation().setX(arrayCities[i].getLocation().getX()*scale);
            arrayCities[i].getLocation().setY(arrayCities[i].getLocation().getY()*scale);
            Label cityname = new Label();
            cityname.setText(arrayCities[i].getName());
            cityname.setPosition(arrayCities[i].getLocation());
            frame.addToPlot(cityname);
        }



        /* Carry out Translation and flipping on Y then plotting the data*/
        bBoxCenter.setX((topLeftPoint.getX()+(0.5*(maxX-minX)))*scale);
        bBoxCenter.setY((topLeftPoint.getY()+(0.5*(minY-maxY)))*scale);
        double xTranslation = windowCenter.getX() - bBoxCenter.getX();
        double yTranslation = windowCenter.getY() - bBoxCenter.getY();
        for (int i = 0; i < arrayCities.length; i++) {
            arrayCities[i].getLocation().setX(arrayCities[i].getLocation().getX() + xTranslation);
            arrayCities[i].getLocation().setY(arrayCities[i].getLocation().getY() + yTranslation);
            arrayCities[i].getLocation().setY(1_000 -(arrayCities[i].getLocation().getY()));
            frame.addToPlot(arrayCities[i].getLocation());
        }

        /* Printing values in Console*/
        for( City city : arrayCities){
            System.out.println(city.getName() + " : " + city.getLocation());
        }




        frame.drawAllFeature();


    }
}
