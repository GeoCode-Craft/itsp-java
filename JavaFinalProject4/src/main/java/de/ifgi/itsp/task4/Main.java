package de.ifgi.itsp.task4;

import de.ifgi.itsp.task4.utility.ReadJSONFile;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        ReadJSONFile rjf = new ReadJSONFile();
        List<Object> linestringCoordinates = rjf.mapToObject("C:\\Users\\ADMIN\\Desktop\\itsp-java\\JavaFinalProject4\\src\\main\\java\\de\\ifgi\\itsp\\task4\\europecoastline.geojson");

      Object[] coordinatesArray;
        coordinatesArray = linestringCoordinates.toArray();

        for(int i = 0; i <= coordinatesArray.toString().length(); i++) {
          System.out.println(i + " " + coordinatesArray[i]);
      }

    }
}
