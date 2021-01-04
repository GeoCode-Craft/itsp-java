package de.ifgi.itsp.task2.utility;

import de.ifgi.itsp.task2.shapes.City;
import de.ifgi.itsp.task2.shapes.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Utility {

    public static  final double  WINDOWCENTER = 500.00;

    public static List<City> getCityPointsFromFile(){
        /* Reading the text cities file*/
        File cityDataFile = new File("C:\\Users\\ADMIN\\Desktop\\itsp-java\\JavaFinalProject2\\src\\de\\ifgi\\itsp\\task2\\europeancities.txt");

        /* Instantiation of City Array holder*/
        List<City> arrayCities = new ArrayList<City>();



        try {
            Scanner cityDataFileReader = new Scanner(cityDataFile);
                while (cityDataFileReader.hasNextLine()) {
                    String data = cityDataFileReader.nextLine();
                    City  randomCity = createCity(data);

                    /* Appending each city to the arrayCities*/
                    arrayCities.add(randomCity);
                }
            cityDataFileReader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        return arrayCities;

    }

     public static  City createCity(String data){
         String[] splitData = data.split(",");
         /* Instantiation of City variable*/
         City randomCity = new City();
         /* Getting City name*/
         randomCity.setName(splitData[0]);

         //Getting location data
         String[] location;
         location = splitData[1].split(" ");
         double lat = Double.parseDouble(location[0]);
         double lon = Double.parseDouble(location[1]);
         randomCity.setLocation(new Point(lat, lon));

         /* Getting Other data variables*/
         randomCity.setPopulationDensity((int) Double.parseDouble(splitData[2]));
         randomCity.setPopulation((int) Double.parseDouble(splitData[3]));
         randomCity.setAreaKm2(Double.parseDouble(splitData[4]));
         randomCity.setGdpInBillions(Double.parseDouble(splitData[5]));
         randomCity.setForeignResidentsPercentage(Double.parseDouble(splitData[6]));



         return randomCity;

     }


    public static double[] getBoundingBox(List<City> cities){
        double[] x= new double[cities.size()];
        double[] y = new double[cities.size()];

        double[] boundingBox = new double[4];

        for(int i=0; i<cities.size(); i++){
            x[i] = cities.get(i).getLocation().getX();
            y[i] = cities.get(i).getLocation().getY();
        }
        Arrays.sort(x);
        Arrays.sort(y);

        boundingBox[0] = x[0];
        boundingBox[1] = y[0];
        boundingBox[2] = x[x.length-1];
        boundingBox[3] = y[y.length-1];

        return  boundingBox;

    }

    public static double bboxHeight(double[] bbox){

        return bbox[3] - bbox[1];
    }

    public static double bboxWidth(double[] bbox){

        return bbox[2] - bbox[0];
    }

    /*Calculate center of a bounding box*/

    public static double bboxCenterX(double[] bbox) {
        double centerX;
        centerX= bbox[0] + (bboxWidth(bbox)/2) ;
        return centerX;
    }
    public static double bboxCenterY(double[] bbox) {
        double centerY;
        centerY= bbox[1] + (bboxHeight(bbox)/2) ;
        return centerY;
    }



}
