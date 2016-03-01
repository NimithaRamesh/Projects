package data;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by nimitharamesh on 10/13/15.
 * Sets the default keyword for the API search
 * Commits to storage
 */
public class IngredientPreference {
    SharedPreferences prefs;

    public IngredientPreference(Activity activity) {
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);

    }
    public String getIngredient() {
        return prefs.getString("ingredient", "chicken");
    }

    public void setIngredient(String ingredient){
        prefs.edit().putString("ingredient", ingredient).commit();
    }
}

