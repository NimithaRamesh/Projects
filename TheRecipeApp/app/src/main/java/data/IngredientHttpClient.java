package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Util.Utils;

/**
 * Created by nimitharamesh on 10/1/15.
 * Sends a GET request
 * Reads the response line by line and returns a string
 */

public class IngredientHttpClient {

    public String getIngredientData (String ingredient){
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) (new URL(Utils.BASE_URL1 + ingredient + Utils.BASE_URL2)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            //Read Response
            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            while((line = bufferedReader.readLine()) !=null) {
                stringBuffer.append(line + "\r\n");
            }

            inputStream.close();
            connection.disconnect();

            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
