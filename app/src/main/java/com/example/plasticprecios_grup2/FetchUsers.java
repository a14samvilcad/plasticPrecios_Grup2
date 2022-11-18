package com.example.plasticprecios_grup2;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class FetchUsers extends AsyncTask<String, Void, String> {

    private WeakReference<JSONObject> jsonObjectWeakReference;


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            return NetworkUtils.getUserInfo(strings[0]);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
