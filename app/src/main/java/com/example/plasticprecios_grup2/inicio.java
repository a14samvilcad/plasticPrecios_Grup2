package com.example.plasticprecios_grup2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.plasticprecios_grup2.databinding.ActivityInicioBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class inicio extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.android.examen1vilsam.extra.MESSAGE";
    private static final int TEXT_REQUEST = 1;
    private String BASE_URL = "http://192.168.1.14:3000";
    private static ArrayList<Products> productsArrayList = new ArrayList<>();

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

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

        getProducts();

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_inicio);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


    //Metodo para coger los productos del metodo del node
    private void getProducts(){

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<List<Products>> call = retrofitInterface.getProducts();

        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                //Controla errores de respuesta HTTP
                if (!response.isSuccessful()){

                    Toast.makeText(inicio.this, "Codigo HTTP" + response.code(),
                            Toast.LENGTH_LONG).show();
                    System.err.println("Codigo HTTP: " + response.code());
                    return;
                }

                List<Products> productsList = response.body();

                for (Products products: productsList){
                    String userName = products.getUserName();
                    int preu = products.getPrecio();
                    String nombre = products.getNombre();
                    String desc = products.getDescripcion();

                    productsArrayList = new ArrayList<>();
                    Products newProduct = new Products(userName, preu, nombre, desc);
                    productsArrayList.add(newProduct);


                    Toast.makeText(inicio.this, newProduct.toString(),
                            Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

                Toast.makeText(inicio.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();

                t.printStackTrace();
            }
        });

    }

    public static ArrayList<Products> getProductsArrayList(){
        return productsArrayList;
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