package Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nimitharamesh on 10/1/15.
 * Stores API information
 */
public class Utils {
    public static final String BASE_URL1 = "http://api.foodessentials.com/ingredientsearch?q=";
    public static final String BASE_URL2 = "&sid=41c4bb6d-a190-4c23-9dfe-45644c7df4a3&n=100&s=0&f=json&api_key=4qapdewhvu8xmgnu883f6vqa";

    public static JSONObject getObject(String tagName, JSONObject jsonObject) throws JSONException {
        JSONObject jObj = jsonObject.getJSONObject(tagName);
        return jObj;
    }

    public static String getString(String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(tagName);
    }

    public static int getInt(String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getInt(tagName);
    }
}
