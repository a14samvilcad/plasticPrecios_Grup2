package com.example.plasticprecios_grup2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.plasticprecios_grup2.databinding.ActivityInicioBinding;

public class inicio extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.android.examen1vilsam.extra.MESSAGE";
    private static final int TEXT_REQUEST = 1;

    //Toolbar
    private AppBarConfiguration appBarConfiguration;

    //Binding for fragments
    private ActivityInicioBinding binding;

    //UserName
    private String userNameText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_inicio);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        Intent intent = getIntent();
        userNameText = intent.getStringExtra(Login.EXTRA_MESSAGE);


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_inicio);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //falta por implementar la cesta
    public void cestaClick(View view) {

    }

    //
    public void searchProducts(View view){
        new FetchProduct().execute();
    }


    public void goProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        intent.putExtra(EXTRA_MESSAGE, userNameText);

        startActivityForResult(intent, TEXT_REQUEST);
    }
}