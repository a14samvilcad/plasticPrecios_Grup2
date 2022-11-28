package com.example.plasticprecios_grup2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    ArrayList<String> productsArrayList;

    public AdapterDatos(ArrayList<String> productsArrayList){
        this.productsArrayList = productsArrayList;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, null, false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(productsArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView nombreProducto;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombreProducto = itemView.findViewById(R.id.nombreProd);
        }

        public void asignarDatos(String product) {
            nombreProducto.setText(product);
        }
    }
}
