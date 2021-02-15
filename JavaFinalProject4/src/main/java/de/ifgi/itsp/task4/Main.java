package de.ifgi.itsp.task4;

import de.ifgi.itsp.task4.utility.ParseDynamicJson;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {

       String first ="C:\\Users\\ADMIN\\Desktop\\itsp-java\\JavaFinalProject4\\src\\main\\java\\de\\ifgi\\itsp\\task4\\europecoastline.geojson";
       try{
           String inputJson = new String((Files.readAllBytes(Paths.get(first))));
           JSONObject inputJSONOBject = new JSONObject(inputJson);
           ParseDynamicJson.getKey(inputJSONOBject, "coordinates");

       } catch (IOException e){
           e.printStackTrace();
       }



    }
}
