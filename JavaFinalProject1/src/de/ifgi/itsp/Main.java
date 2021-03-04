package de.ifgi.itsp;

import de.ifgi.itsp.shapes.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /* Reading the text cities file*/ //pondijavafinalproject1//C:\Users\ADMIN\Desktop\itsp-java\JavaFinalProject1\src\de\ifgi\itsp\europeancities.txt
        File cityDataFile = new File("C:/Users/pondi/Desktop/itsp-java/JavaFinalProject1/src/de/ifgi/itsp/europeancities.txt");
        Scanner cityDataFileReader = null;
        try {
            cityDataFileReader = new Scanner(cityDataFile);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        int noOfCities = 10;
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

        /* Printing the Cities data to console*/
        for (City city : arrayCities){
            System.out.println( "Name='" + city.getName() + '\'' +
                    ", Location=" + city.getLocation() +
                    ", Population Density=" + city.getPopulationDensity() +
                    ", Population=" + city.getPopulation() +
                    ", Area in Km2=" + city.getAreaKm2()+
                    ", GDP in € Billions=" + city.getGdpInBillions() +
                    ", Foreign Residents Percentage=" + city.getForeignResidentsPercentage());
        }


    }
}
