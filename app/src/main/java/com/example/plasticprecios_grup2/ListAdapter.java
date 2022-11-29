package com.example.plasticprecios_grup2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Products> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<Products> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Products> items){
        mData = items;
    }

    //Clase ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, desc, userName, preu;

        ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.nombreProd);
            desc = itemView.findViewById(R.id.desc);
            userName = itemView.findViewById(R.id.idUserName);
            preu = itemView.findViewById(R.id.idPrecio);
        }

        void bindData(final Products item){
            name.setText(item.getNombre());
            desc.setText(item.getDescripcion());
            userName.setText("By: " + item.getUserName());
            preu.setText(item.getPrecio() + "€");
        }
    }



}
