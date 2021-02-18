package de.ifgi.itsp.task4.utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParseDynamicJson {


    public static Object parseObject(JSONObject json, String key) {
        //System.out.println(json.get(key));
        return json.get(key);
    }

    public static List<Object> getKey(JSONObject json, String key) {
        List<Object> dataList = new ArrayList<>();

        boolean exists = json.has(key);
        Iterator<?> keys;
        String nextKeys;

        if (!exists) {
            keys = json.keys();
            while (keys.hasNext()) {
                nextKeys = (String) keys.next();
                try {

                    if (json.get(nextKeys) instanceof JSONObject) {

                        if (exists == false) {
                            dataList.add(getKey(json.getJSONObject(nextKeys), key));
                        }

                    } else if (json.get(nextKeys) instanceof JSONArray) {
                        JSONArray jsonarray = json.getJSONArray(nextKeys);
                        for (int i = 0; i < jsonarray.length(); i++) {
                            String jsonarrayString = jsonarray.get(i).toString();
                            JSONObject innerJSOn = new JSONObject(jsonarrayString);

                            if (exists == false) {
                                dataList.add(getKey(innerJSOn, key));
                            }

                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } else {
            dataList.add(parseObject(json, key));
        }
        return  dataList;

    }



}
