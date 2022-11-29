package com.example.plasticprecios_grup2;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plasticprecios_grup2.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private String BASE_URL = "http://192.168.1.14:3000";
    ArrayList<Products> productsArrayList = new ArrayList<>();

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);



        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getProducts();

        //Llamada al metodo init para meter los valores en la RecyclerView
        init(getView());



        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        /*
        binding.popularProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void init(View view){
        ListAdapter listAdapter = new ListAdapter(productsArrayList, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
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

                    Toast.makeText(getActivity(), "Codigo HTTP" + response.code(),
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

                    Products newProduct = new Products(userName, preu, nombre, desc);
                    productsArrayList.add(newProduct);

                }

                //Comprobacion de que se meten en el ArrayList
                for (Products products: productsArrayList){
                    //Toast.makeText(getActivity(), products.toString(), Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

                Toast.makeText(getActivity(), t.getMessage(),
                        Toast.LENGTH_LONG).show();

                t.printStackTrace();
            }
        });

    }



}