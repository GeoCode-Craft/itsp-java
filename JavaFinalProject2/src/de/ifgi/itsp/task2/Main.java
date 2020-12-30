package de.ifgi.itsp.task2;

import de.ifgi.itsp.task2.shapes.City;
import de.ifgi.itsp.task2.shapes.Point;
import de.ifgi.itsp.task2.utility.Utility;
import de.ifgi.itsp.task2.view.SimpleFrame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static int noOfCities = 10;
    public static Point[] citiesLocation = new Point[noOfCities];

    public static void main(String[] args) {

        SimpleFrame frame = new SimpleFrame(1000,1000);



        /* Reading the text cities file*/
        File cityDataFile = new File("C:\\Users\\ADMIN\\Desktop\\itsp-java\\JavaFinalProject2\\src\\de\\ifgi\\itsp\\task2\\europeancities.txt");
        Scanner cityDataFileReader = null;
        try {
            cityDataFileReader = new Scanner(cityDataFile);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        /* Instantiation of City Array holder*/
        City[] arrayCities = new City[noOfCities];
        /* Loop through the rows in the file*/
        int counter =0;
        if (cityDataFileReader != null) {
            while (cityDataFileReader.hasNextLine()) {
                String data = cityDataFileReader.nextLine();
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


                /* Appending each city to the arrayCities*/
                arrayCities[counter] = randomCity;
                counter++;

            }
        }
        cityDataFileReader.close();

        /* Get BoundingBox from City data*/

        for(int i=0;i< arrayCities.length; i++){
            citiesLocation[i]=arrayCities[i].getLocation();
        }
        System.out.println("Minimum X bounding value is "+    Utility.minX(citiesLocation));
        System.out.println("Minimum Y bounding value is "+    Utility.minY(citiesLocation));
        System.out.println("Maximum X bounding value is "+    Utility.maxX(citiesLocation));
        System.out.println("Maximum Y bounding value is "+    Utility.maxY(citiesLocation));
        System.out.println("Bounding Box Height is "+    Utility.bboxHeight(citiesLocation));
        System.out.println("Bounding Box Width is "+    Utility.bboxWidth(citiesLocation));
        /* Paddings values*/
        System.out.println("Paddings Values are :");
      for(Double value: Utility.paddings(citiesLocation)){
          System.out.println(value);
      }

        /* Add cities to Plot*/
        for(City city: arrayCities){
            frame.addToPlot(city.getLocation());
        }


        frame.drawAllFeature();


    }
}
