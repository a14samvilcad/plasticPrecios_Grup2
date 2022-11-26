package com.example.plasticprecios_grup2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    private static final int TEXT_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

    }

    public void goLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void setUserName(String userName){
        //String s = getString(R.string.userName);
        //R.string.userName
    }
}