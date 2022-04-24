package com.example.epa51;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PointageListAdapter extends RecyclerView.Adapter<PointageListAdapter.MyViewHolder> {

    private List<Pointage_Model> pointage;
    public PointageListAdapter(List<Pointage_Model> pointage)
    {
        this.pointage=pointage;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView navire_cell;
        private TextView date_cell;



        public MyViewHolder(final View view)
        {
            super(view);
            navire_cell= view.findViewById(R.id.navire_cell);
            date_cell= view.findViewById(R.id.date_cell);

        }
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pointage_cell,parent,false);

        return new PointageListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        String navire= pointage.get(position).getNom_navire();
        String date= pointage.get(position).getDate();


        holder.navire_cell.setText(navire);
        holder.date_cell.setText(date);

    }

    @Override
    public int getItemCount() {
        return pointage.size();
    }

}
