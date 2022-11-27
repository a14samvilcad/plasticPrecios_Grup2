package com.example.plasticprecios_grup2;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private static final String PRODUCTS_BASE_URL = "http://192.168.1.14:3000/mostrarProductosApp";
    private static final String QUERY_PARAM = "q";


    static String getBookInfo(String queryString){

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String productJSONString = null;

        try {
            Uri builtURI = Uri.parse(PRODUCTS_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .build();

            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);

                builder.append("\n");
            }

            if (builder.length() == 0){
                // Stream was empty. No point in parsing.
                return null;
            }

            productJSONString = builder.toString();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        Log.d(LOG_TAG, productJSONString);

        return productJSONString;

    }

}
