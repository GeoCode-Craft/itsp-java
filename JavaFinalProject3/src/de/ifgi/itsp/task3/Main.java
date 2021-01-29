package de.ifgi.itsp.task3;

import de.ifgi.itsp.task3.shapes.Circle;
import de.ifgi.itsp.task3.shapes.City;
import de.ifgi.itsp.task3.shapes.Label;
import de.ifgi.itsp.task3.shapes.Rectangle;
import de.ifgi.itsp.task3.shapes.Point;
import de.ifgi.itsp.task3.utility.Utility;
import de.ifgi.itsp.task3.view.SimpleFrame;

import java.awt.*;
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

        minX -= 50;
        maxX += 50;
        minY -= 50;
        maxY += 50;


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
            Label cityName = new Label();
            cityName.setText(arrayCities[i].getName());
            cityName.setPosition(arrayCities[i].getLocation());
            frame.addToPlot(cityName);
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
            /* Define Circle for Population Buffer */
            Circle circle = new Circle();
            circle.setTopLeftPoint(arrayCities[i].getLocation());
            circle.setDiameter( arrayCities[i].getPopulation()/40_000); // Dividing to get a good visual represetantion
            circle.setFillColor(Color.getHSBColor((float)0.5, (float)arrayCities[i].getGdpInBillions()/(float)100,(float)0.5));


            /* Plot Buffer */
            frame.addToPlot(circle);

            /* plotting the data*/
            frame.addToPlot(arrayCities[i].getLocation());


        }
        /* Define Rectangle for GDP data */
        for (int i = 0; i < arrayCities.length; i++) {
            /* Define Rectangle for Local Population Graph */
            Rectangle localPopulation = new Rectangle();
            Point localStartPoint = new Point();
            localStartPoint.setX(arrayCities[i].getLocation().getX() - 100.0);
            localStartPoint.setY(arrayCities[i].getLocation().getY());
            localPopulation.setTopLeftPoint(localStartPoint);
            localPopulation.setWidth(20.0 );
            localPopulation.setHeight(100.0 - arrayCities[i].getForeignResidentsPercentage() );
            localPopulation.setFillColor(Color.BLUE);

            /* plotting the local population bargraph*/
            frame.addToPlot(localPopulation);

            /* Define Rectangle for Local Population Graph */
            Rectangle foreignPopulation = new Rectangle();
            Point foreignersStartPoint = new Point();
            foreignersStartPoint.setX(arrayCities[i].getLocation().getX() - 80.0);
            foreignersStartPoint.setY(arrayCities[i].getLocation().getY());
            foreignPopulation.setTopLeftPoint(localStartPoint);
            foreignPopulation.setWidth(20.0);
            foreignPopulation.setHeight(arrayCities[i].getForeignResidentsPercentage() );
            foreignPopulation.setFillColor(Color.MAGENTA);

            /* plotting the foreign population bargraph*/
            frame.addToPlot(foreignPopulation);
        }


        /*Description of the visualization*/

        Point legendLocation = new Point();
        legendLocation.setX(30);
        legendLocation.setY(30);

        Label legendLabel = new Label();
        legendLabel.setText("The circle size represents population size. The GDP is higher in Greener cities and lesser in Browner cities");
        legendLabel.setPosition(legendLocation);
        frame.addToPlot(legendLabel);



        /* Printing values in Console*/
        for( City city : arrayCities){
            System.out.println(city.getName() + " : " + city.getLocation() +" : " + city.getPopulation());
        }




        frame.drawAllFeature();


    }
}
