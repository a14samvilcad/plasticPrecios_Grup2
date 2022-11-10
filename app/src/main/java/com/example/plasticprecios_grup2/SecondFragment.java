package com.example.plasticprecios_grup2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.plasticprecios_grup2.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    //Variables para ImageSwitcher
    int index = 0;
    int galeria[] = {R.drawable.avatar, R.drawable.libreta, R.drawable.llavero};

    //Variables para el numero de productos (augmentar-disminuir)
    private int contNumProductes = 1;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);

        //ImageSwitcher bindeado a el imageSwitcher del xml
        cambioImageSwitcher(binding);

        //Llamamos a la funcion que hace el cambio en el textView del numero de productos
        cambioContadorNProductos(binding);

        return binding.getRoot();

    }




    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void cambioImageSwitcher(FragmentSecondBinding binding){
        //ImageSwitcher bindeado a el imageSwitcher del xml
        ImageSwitcher imageSwitcher = binding.imageSwitcher.findViewById(R.id.imageSwitcher);

        //Boton para pasar imagen para atras bindeado al xml + OnClick
        binding.beforeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --index;
                if (index < 0){
                    index = galeria.length-1;
                }
                imageSwitcher.setImageResource(galeria[index]);
            }
        });

        //Boton para pasar imagen para atras bindeado al xml + OnClick
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                if (index == galeria.length){
                    index = 0;
                }
                imageSwitcher.setImageResource(galeria[index]);
            }
        });

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getActivity().getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setMaxWidth(250);
                imageView.setMaxHeight(250);

                return imageView;
            }
        });

        imageSwitcher.setImageResource(galeria[index]);
    }


    public void cambioContadorNProductos(FragmentSecondBinding binding){
        TextView mostrarContador = binding.nProductes.findViewById(R.id.nProductes);

        binding.btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contNumProductes--;
                if (mostrarContador != null){
                    mostrarContador.setText(Integer.toString(contNumProductes));
                }
            }
        });

        binding.btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contNumProductes++;
                if (mostrarContador != null){
                    mostrarContador.setText(Integer.toString(contNumProductes));
                }
            }
        });
    }




}