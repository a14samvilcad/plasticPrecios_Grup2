package com.example.plasticprecios_grup2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    private static final int TEXT_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        String userNameText = intent.getStringExtra(inicio.EXTRA_MESSAGE);
        TextView textViewUserName = findViewById(R.id.textViewUser);
        textViewUserName.setText(userNameText);

        Button mapa = (Button)findViewById(R.id.mapa);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void goLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }


}