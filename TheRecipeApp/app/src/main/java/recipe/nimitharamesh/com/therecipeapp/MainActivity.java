package recipe.nimitharamesh.com.therecipeapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import data.IngredientHttpClient;
import data.IngredientPreference;
import data.JSONIngredientParser;
import model.Ingredient;
import model.Ingredients;

/** This page ties everything together
 *
 * */

public class MainActivity extends ActionBarActivity {

    private TextView ingredientName;

    Ingredients ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingredientName = (TextView) findViewById(R.id.ingredientText);

        IngredientPreference ingredientPreference = new IngredientPreference(MainActivity.this);

        renderIngredientData(ingredientPreference.getIngredient());
    }

    public void renderIngredientData( String ingredient ) {

        IngredientTask ingredientTask = new IngredientTask();
        ingredientTask.execute(new String[]{ingredient + "&units=metric"});

    }


    private class IngredientTask extends AsyncTask<String, Void, Ingredients> {

        @Override
        protected Ingredients doInBackground(String... params) {

            String data = ((new IngredientHttpClient()).getIngredientData(params[0]));

            ingredients = JSONIngredientParser.getIngredients(data);

            for(Ingredient ingredient : ingredients.getIngredients()) {
                Log.v("Data: ", ingredient.getIngredientName());
            }

            return ingredients;
        }

        @Override
        protected void onPostExecute(Ingredients ingredients) {

            super.onPostExecute(ingredients);

            ingredientName.setText(ingredients.ingredientsAsText());

        }

    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Change Ingredient");

        final EditText ingredientInput = new EditText(MainActivity.this);
        ingredientInput.setInputType(InputType.TYPE_CLASS_TEXT);
        ingredientInput.setHint("tomato");
        builder.setView(ingredientInput);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                IngredientPreference ingredientPreference = new IngredientPreference(MainActivity.this);
                ingredientPreference.setIngredient(ingredientInput.getText().toString());

                String newIngredient = ingredientPreference.getIngredient();

                renderIngredientData(newIngredient);
            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.change_ingredientId) {
            showInputDialog();
        }

        return super.onOptionsItemSelected(item);
    }
}
