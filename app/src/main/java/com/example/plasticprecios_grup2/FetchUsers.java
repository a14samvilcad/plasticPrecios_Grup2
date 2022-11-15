package com.example.plasticprecios_grup2;

import android.os.AsyncTask;

public class FetchUsers extends AsyncTask<String, Void, String> {




    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getUserInfo(strings[0]);
    }
}
