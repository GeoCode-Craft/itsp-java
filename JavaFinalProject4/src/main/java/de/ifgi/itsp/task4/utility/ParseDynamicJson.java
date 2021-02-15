package de.ifgi.itsp.task4.utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class ParseDynamicJson {


    public static void parseObject(JSONObject json, String key) {
        System.out.println(json.get(key));
    }

    public static void getKey(JSONObject json, String key) {

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
                            getKey(json.getJSONObject(nextKeys), key);
                        }

                    } else if (json.get(nextKeys) instanceof JSONArray) {
                        JSONArray jsonarray = json.getJSONArray(nextKeys);
                        for (int i = 0; i < jsonarray.length(); i++) {
                            String jsonarrayString = jsonarray.get(i).toString();
                            JSONObject innerJSOn = new JSONObject(jsonarrayString);

                            if (exists == false) {
                                getKey(innerJSOn, key);
                            }

                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } else {
            parseObject(json, key);
        }

    }



}
