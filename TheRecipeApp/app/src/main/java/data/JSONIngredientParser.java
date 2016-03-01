package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import model.Ingredients;

/**
 * Created by nimitharamesh on 10/1/15.
 *Parses data and returns an array of
 *
 */
public class JSONIngredientParser {
    public static Ingredients getIngredients(String data){

        try {
            //Parsing
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("arrayIngredients");

            //Creating ingredients
            return new Ingredients(jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
