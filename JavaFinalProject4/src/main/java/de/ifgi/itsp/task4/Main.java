package de.ifgi.itsp.task4;

import de.ifgi.itsp.task4.shapes.Label;
import de.ifgi.itsp.task4.shapes.Point;
import de.ifgi.itsp.task4.shapes.Rectangle;
import de.ifgi.itsp.task4.shapes.*;
import de.ifgi.itsp.task4.utility.ParseDynamicJson;
import de.ifgi.itsp.task4.utility.Utility;
import de.ifgi.itsp.task4.view.SimpleFrame;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

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
        /* Define Rectangle for Local population  Graph*/
        for (int i = 0; i < arrayCities.length; i++) {
            /* Define Rectangle for Local Population Graph */
            Rectangle localPopulation = new Rectangle();
            Point localStartPoint = new Point();
            localStartPoint.setX(arrayCities[i].getLocation().getX() - 100.0);
            localStartPoint.setY(arrayCities[i].getLocation().getY());
            localPopulation.setTopLeftPoint(localStartPoint);
            localPopulation.setWidth(20.0 );
            localPopulation.setHeight(100 - arrayCities[i].getForeignResidentsPercentage() );
            localPopulation.setFillColor(Color.BLUE);
            Label localPopulationLabel = new Label();
            localStartPoint.setY(localStartPoint.getY() );
            localPopulationLabel.setText(String.valueOf(100 - arrayCities[i].getForeignResidentsPercentage()));
            localPopulationLabel.setPosition(localStartPoint);

            /* plotting the local population bargraph*/
            frame.addToPlot(localPopulation);
            /* plotting the local population Label*/
            frame.addToPlot(localPopulationLabel);


        }
        /* Define Rectangle for Foreignpopulation Graph */
        for (int i = 0; i < arrayCities.length; i++) {
            /* Define Rectangle for Foreign Population Graph */
            Rectangle foreignPopulation = new Rectangle();
            Point foreignersStartPoint = new Point();
            foreignersStartPoint.setX(arrayCities[i].getLocation().getX() - 70.0);
            foreignersStartPoint.setY(arrayCities[i].getLocation().getY());
            foreignPopulation.setTopLeftPoint(foreignersStartPoint);
            foreignPopulation.setWidth(20.0);
            foreignPopulation.setHeight(arrayCities[i].getForeignResidentsPercentage() );
            foreignPopulation.setFillColor(Color.darkGray);
            Label foreignPopulationLabel = new Label();
            foreignPopulationLabel.setText(String.valueOf(arrayCities[i].getForeignResidentsPercentage()));
            foreignPopulationLabel.setPosition(foreignersStartPoint);

            /* plotting the foreign population bargraph*/
            frame.addToPlot(foreignPopulation);
            /* plotting the foreign population Label*/
            frame.addToPlot(foreignPopulationLabel);
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

        /*
        * European Coastline Section
        *@Param
        */
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
           //System.out.println("count " + index + " " +c);
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
                //System.out.println("Out");
            }else{
                linestringGapsElim.add(linestringGap[i].trim());
            }
        }

        //Final European coasltine points into an array of points
        Point[] lineStringPointArray = new Point[linestringGapsElim.size()];
        for(int i= 0; i< linestringGapsElim.size() -2 ; i+=2){
            //System.out.println("count "+ i  +" "+ linestringGapsElim.get(i));
              Point coordinates = new Point();
              System.out.println(i + " Longitude is "+ linestringGapsElim.get(i));
              System.out.println( i + " Latitude is "+ linestringGapsElim.get(i+1));

              Double y = Double.valueOf(linestringGapsElim.get(i));
              Double x = Double.valueOf(linestringGapsElim.get(i+1));
              coordinates.setPosition(x,y);

              lineStringPointArray[i] = coordinates;
        }
        System.out.println(Arrays.toString(lineStringPointArray));

        /* Carry out Scaling and add city label to Gui*/
        for (int i = 0; i < lineStringPointArray.length-10; i++) {
            if (lineStringPointArray[i]!= null){
                System.out.println("-------------------Scaling; Translation ; Flipping----------------------------");
                System.out.println(lineStringPointArray[i]);
                Double x = ((lineStringPointArray[i].getX()*scale)+xTranslation) ;
                Double y = (1_000 -((lineStringPointArray[i].getY()*scale) +yTranslation)) ;
                lineStringPointArray[i].setX(x);
                lineStringPointArray[i].setY(y);
                System.out.println(lineStringPointArray[i]);
            }

        }

        /* Remove nulls*/
        List<Point> removeNulls = new ArrayList<>();

        for(int i= 0;i< lineStringPointArray.length; i++ ){
            if (lineStringPointArray[i]!= null){
                removeNulls.add(lineStringPointArray[i]);
            }
        }

        Point [] finalLineStringPointArray = new Point[removeNulls.size()];
        for(int i= 0;i< removeNulls.size(); i++ ){
            finalLineStringPointArray[i] = removeNulls.get(i);
        }




            /* Define Polyline and set color */
            Polyline polyline = new Polyline(finalLineStringPointArray);

            /* Plot Ocean coastline */
            frame.addToPlot(polyline);





        frame.drawAllFeature();

    }


}
