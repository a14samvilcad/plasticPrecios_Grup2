package com.example.plasticprecios_grup2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    private static final int TEXT_REQUEST = 1;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.14:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Retrofits & Interfaces
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);


        //Login Button
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLoginDialog();
            }
        });

        //New User Button
        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSignupDialog();
            }
        });
    }


    private void handleLoginDialog(){
        //CREO QUE HAY QUE PONER OTRA LAYOUT
        View view = getLayoutInflater().inflate(R.layout.login_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(view).show();

        Button loginBtn = view.findViewById(R.id.login);
        EditText userEdit = view.findViewById(R.id.nameEdit);
        EditText passwordEdit = view.findViewById(R.id.passwordEdit);

        //On click LOGIN
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> map = new HashMap<>();

                map.put("user", userEdit.getText().toString());
                map.put("password", passwordEdit.getText().toString());

                Call<LoginResult> call = retrofitInterface.executeLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        System.err.println(response.toString());

                        //probar isSuccesful == true si no va
                        if (response.code() == 200){

                            LoginResult result = response.body();

                            //AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
                            //builder1.setTitle(userEdit.getText().toString());
                            //builder1.setMessage(passwordEdit.getText().toString());

                            //builder1.show();



                            Toast.makeText(Login.this, "Sesión iniciada",
                                    Toast.LENGTH_LONG).show();

                            launchInicio(view);
                        }
                        else if (response.code() == 400){
                            Toast.makeText(Login.this, "Credenciales equivocados",
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(Login.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();

                        t.printStackTrace();

                    }
                });

            }
        });

    }

    private void handleSignupDialog() {

        View view = getLayoutInflater().inflate(R.layout.signup_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view).show();

        Button signupBtn = view.findViewById(R.id.signup);
        EditText nameEdit = view.findViewById(R.id.nameEdit);
        EditText passwordEdit = view.findViewById(R.id.passwordEdit);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> map = new HashMap<>();

                map.put("user", nameEdit.getText().toString());
                map.put("password", passwordEdit.getText().toString());


                Call<Void> call = retrofitInterface.executeSignUp(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.isSuccessful() == true){
                            Toast.makeText(Login.this,
                                    "Registro correcto", Toast.LENGTH_LONG).show();

                            launchInicio(view);
                        }
                        else if (response.isSuccessful() == false){
                            Toast.makeText(Login.this,
                                    "Algo ha fallado", Toast.LENGTH_LONG).show();
                        }



                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Login.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }


    public void launchInicio(View view) {

        Intent intent = new Intent(this, inicio.class);
        startActivityForResult(intent, TEXT_REQUEST);

    }

}