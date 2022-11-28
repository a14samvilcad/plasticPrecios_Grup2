package com.example.plasticprecios_grup2;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FetchProduct extends AsyncTask<String, Void, String> {

    FetchProduct(){

    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items"); //no se que hay que poner aqui

            int i = 0;



        }catch (JSONException e){
            e.printStackTrace();
        }


    }


}
