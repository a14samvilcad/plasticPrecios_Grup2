package com.example.plasticprecios_grup2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private static final int TEXT_REQUEST = 1;

    //Elements login
    private EditText userName;
    private EditText passwordUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText)findViewById(R.id.editText_User);
        passwordUser = (EditText)findViewById(R.id.editText_Password);
    }


    public void launchInicio(View view) {

        Intent intent = new Intent(this, inicio.class);
        startActivityForResult(intent, TEXT_REQUEST);

    }



    public void searchUser(View view){
        String queryStringUser = userName.getText().toString();
        String queryStringPsw = passwordUser.getText().toString();

        //mirar como hay que pasar el userName y la PWD en el mismo string
        new FetchUsers(userName, passwordUser).execute(queryStringUser + "," + queryStringPsw);

    }
}