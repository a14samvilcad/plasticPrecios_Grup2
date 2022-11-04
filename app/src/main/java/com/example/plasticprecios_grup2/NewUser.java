package com.example.plasticprecios_grup2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewUser extends AppCompatActivity {

    private static final int TEXT_REQUEST = 1;
    EditText newName, newPassword, newPasswordCheck;
    Button saveNewUser;
    TextView infoError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        newName = (EditText) findViewById(R.id.newName);
        newPassword = (EditText) findViewById(R.id.newPassword);
        newPasswordCheck = (EditText) findViewById(R.id.newPasswordCheck);
        saveNewUser = (Button) findViewById(R.id.saveNewUser);
        infoError = (TextView) findViewById(R.id.infoError);
        infoError.setVisibility(View.INVISIBLE);
    }

    public void saveNewUser(View view) {

        if (newPassword.equals(newPasswordCheck)) {
            Intent intent = new Intent(this, inicio.class);
            startActivityForResult(intent, TEXT_REQUEST);
        } else {
            infoError.setVisibility(View.VISIBLE);
            infoError.setText("Les contrasenyes no coincideixen");
        }
    }
}