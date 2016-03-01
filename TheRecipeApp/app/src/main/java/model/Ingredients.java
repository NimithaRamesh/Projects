package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Util.Utils;

/**
 * Created by nimitharamesh on 10/1/15.
 *
 */

public class Ingredients {

    private ArrayList<Ingredient> myIngredients;

    public Ingredients(JSONArray jsonArray) {
        myIngredients = new ArrayList<>(jsonArray.length());

        try{
            for(int i=0; i<jsonArray.length(); i++){
                Ingredient newIngredient = new Ingredient();
                JSONObject jsonIngredient = jsonArray.getJSONObject(i);

                newIngredient.setIngredientId(Utils.getInt("ingredient_id", jsonIngredient));
                newIngredient.setIngredientName(Utils.getString("ingredient_name", jsonIngredient));
                myIngredients.add(newIngredient);
            }
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Ingredient> getIngredients() {
        return myIngredients;
    }

    public String ingredientsAsText(){
        String displayText = "";
        int count = 1;
        for(Ingredient ingredient : myIngredients) {
            displayText += count + ". " + ingredient.getIngredientName() + "\n\n";
            count++;
        }
        return displayText;
    }
}
