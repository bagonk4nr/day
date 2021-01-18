package com.web.day.libs;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.*;

public class ModelHTML {

    public Map<String, Object> convertData(String Json, String forData) throws JSONException {
        String dataJson = Json.replace("\\", "")
                .replace("[\"", "{\"" + forData + "\": [")
                .replace("\"]", "]}")
                .replace("}\",\"{", "}, {")
                .replace("[{", "{\"" + forData + "\": [{")
                .replace("}]", "}]}");

//        System.out.println(dataJson);
        JSONObject object = new JSONObject(dataJson);
        System.out.println(object);
        return getModelFromJson(object);
    }

    private Map<String, Object> getModelFromJson(JSONObject json) throws JSONException {
        Map<String,Object> out = new HashMap<String,Object>();

        Iterator it = json.keys();
        while (it.hasNext()) {
            String key = (String)it.next();

            if (json.get(key) instanceof JSONArray) {

                // Copy an array
                JSONArray arrayIn = json.getJSONArray(key);
                List<Object> arrayOut = new ArrayList<Object>();
                for (int i = 0; i < arrayIn.length(); i++) {
                    JSONObject item = (JSONObject)arrayIn.get(i);
                    Map<String, Object> items = getModelFromJson(item);
                    arrayOut.add(items);
                }
                out.put(key, arrayOut);
            }
            else {

                // Copy a primitive string
                out.put(key, json.getString(key));
            }
        }

        return out;
    }

}
