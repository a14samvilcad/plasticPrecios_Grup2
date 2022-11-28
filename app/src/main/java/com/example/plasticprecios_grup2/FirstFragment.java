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

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private ArrayList<Products> productsArrayList;
    private ArrayList<String> listDatos;
    private RecyclerView recycler;


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

        recycler = (RecyclerView) view.findViewById(R.id.recyclerId);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.VERTICAL,
                false));
        productsArrayList = inicio.getProductsArrayList();

        listDatos = new ArrayList<String>();

        for (Products product: productsArrayList){
            System.out.println(product.getNombre());
            listDatos.add(product.getNombre());
        }

        AdapterDatos adapter = new AdapterDatos(listDatos);
        recycler.setAdapter(adapter);


        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
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



}