package com.example.plasticprecios_grup2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.plasticprecios_grup2.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    //ImageSwitcher variables
    int index = 0;
    int galeria[] = {R.drawable.avatar, R.drawable.libreta, R.drawable.llavero};
    ImageSwitcher imageSwitcher = binding.imageSwitcher.findViewById(R.id.imageSwitcher);

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


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
                ImageView imageView = new ImageView(getApplicationContext());


                return null;
            }
        });

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}