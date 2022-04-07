package com.example.epa51;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pointageRecycleAdapter extends RecyclerView.Adapter<pointageRecycleAdapter.MyViewHolder> {


    private ArrayList<Goods_Model> goods_models;
    public pointageRecycleAdapter(ArrayList<Goods_Model> goodslist)
    {
this.goods_models=goodslist;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView referencecell;
        private TextView nombrecell;
        private TextView poidcell;


        public MyViewHolder(final View view)
        {
        super(view);
        referencecell= view.findViewById(R.id.naturecell);
        nombrecell= view.findViewById(R.id.nombrecell);
        poidcell= view.findViewById(R.id.poidcell);
        }
    }


    @NonNull
    @Override
    public pointageRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pointage2_cell,parent,false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull pointageRecycleAdapter.MyViewHolder holder, int position) {

        String reference= goods_models.get(position).getReference();
        int nombre=goods_models.get(position).getQuantite();
        int poid=goods_models.get(position).getPoid();


        holder.referencecell.setText(reference);
        holder.nombrecell.setText(String.valueOf(nombre));
        holder.poidcell.setText(String.valueOf(poid));
    }

    @Override
    public int getItemCount() {
        return goods_models.size();
    }
}
