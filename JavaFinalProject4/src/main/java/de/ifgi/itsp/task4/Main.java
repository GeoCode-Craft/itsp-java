package de.ifgi.itsp.task4;

import de.ifgi.itsp.task4.shapes.Point;
import de.ifgi.itsp.task4.utility.ParseDynamicJson;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

       String first ="C:\\Users\\ADMIN\\Desktop\\itsp-java\\JavaFinalProject4\\src\\main\\java\\de\\ifgi\\itsp\\task4\\europecoastline.geojson";
        List<Object> coordsList = null;
       try{
           String inputJson = new String((Files.readAllBytes(Paths.get(first))));
           JSONObject inputJSONOBject = new JSONObject(inputJson);
           coordsList = ParseDynamicJson.getKey(inputJSONOBject, "coordinates");

       } catch (IOException e){
           e.printStackTrace();
       }
       // Converting Lists ofg coordinates to Array
        Object[] coordsArray = new Object[coordsList.size()];
        coordsArray = coordsList.toArray(coordsArray);

        //System.out.println(Arrays.toString(coordsArray));
        String coastlineRawData = Arrays.toString(coordsArray);
        String[] lineStringInitial=coastlineRawData.replace("[","").replace("[ ","").replace(", "," ").replace("]",",").replace(",] } },","").replace(",] } }","").split(",");

        System.out.println(lineStringInitial.length);
        int index = 0;
        for (String c: lineStringInitial){
           System.out.println("count " + index + " " +c);
           //System.out.println("YAY");
            index ++;
       }
        //list with gaps Gaps and
        String[] linestringGap = new String[lineStringInitial.length];
        for(int i=0;i<lineStringInitial.length;i++){
            linestringGap[i] =lineStringInitial[i] ;

        }

        for(String c: linestringGap){
            System.out.println(c);
        }
        //elimanate Gaps and convert to double
        List<String> linestringGapsElim =new ArrayList<>();
        for(int i=0;i<linestringGap.length;i++){
            if(linestringGap[i].length() < 4){
                System.out.println("Out");
            }else{
                linestringGapsElim.add(linestringGap[i].trim());
            }
        }

        //Final European coasltine points into an array of points
        Point[] lineStringPointArray ;
        for(int i= 0; i< linestringGapsElim.size()-1 ; i++){
            //System.out.println("count "+ i  +" "+ linestringGapsElim.get(i));
             lineStringPointArray = new Point[linestringGapsElim.size()];
              Point coordinates = new Point();
              System.out.println(i + " Latitude is "+ linestringGapsElim.get(i));
              System.out.println( i + " Longitude is "+ linestringGapsElim.get(i+1));
              Double x = Double.valueOf(linestringGapsElim.get(i+1));
              Double y = Double.valueOf(linestringGapsElim.get(i));
              coordinates.setPosition(x,y);

              lineStringPointArray[i] = coordinates;
        }

        }


}
