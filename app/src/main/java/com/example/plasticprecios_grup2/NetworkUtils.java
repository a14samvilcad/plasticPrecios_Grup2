package com.example.plasticprecios_grup2;

import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    //LOG TAG
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    // AQUI HABRA QUE PONER EL LINK DEL LABS Y ALGUN PARAMETRO EN ÉL DE LOGINUSUARIO (METODO DEL NODE)
    private static final String BOOK_BASE_URL =  "localhost:3000/loginUser";
    // Parameter for the search string.
    private static final String QUERY_PARAM = "q";
    // Parameter to filter by print type.
    //private static final String PRINT_TYPE = "printType";


    static String getUserInfo(JSONObject jsonObject) throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String userJSONString = null;

        //Necesario cambiar la api url
        try {
            //

            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, "sdss")
                    .build();

            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();

            // Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Use a StringBuilder to hold the incoming response.
            StringBuilder builder = new StringBuilder();

            //Leer el input linea por linea
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);

                builder.append("\n");
            }

            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }

            userJSONString = builder.toString();


        }

        catch (Exception e){
            System.err.println(e);
        }

        finally {
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

        Log.d(LOG_TAG, userJSONString);
        return userJSONString;
    }




}
