package de.ifgi.itsp.task4.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadJSONFile {

    ObjectMapper om = new ObjectMapper();

    public List<Object> mapToObject(String geojsonPath) throws Exception {

        Map<String, Object> resultMap = om.readValue(Paths.get(geojsonPath).toFile(), new TypeReference<Map<String, Object>>() {});
        List<Feature> features = om.convertValue(resultMap.get("features"), new TypeReference<List<Feature>>() {});
        List<Object> coordinates = new ArrayList<>();
        for(Feature f : features) {
            // Write the feature to the console to see how it looks like
            //System.out.println(om.writeValueAsString(f));
            // Extract properties
            Map<String,Object> properties = f.getProperties();
            // ....


            // Extract geometry
            GeoJsonObject geometry = f.getGeometry();
            if(geometry instanceof LineString) {
                LineString mls = (LineString) geometry;
                //System.out.println("-------------LineString------------");
                //System.out.println(mls.getCoordinates());
                coordinates.add(mls.getCoordinates());
            } else if(geometry != null) {
                throw new RuntimeException("Unhandled geometry type: " + geometry.getClass().getName());
            }
        }
        return coordinates;
    }


}
