package com.example.plasticprecios_grup2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewUser extends AppCompatActivity {

    private static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }

    public void saveNewUser(View view) {

        Intent intent = new Intent(this, inicio.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }
}