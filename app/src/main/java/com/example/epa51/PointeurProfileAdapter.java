package com.example.epa51;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PointeurProfileAdapter extends ArrayAdapter<Pointage_Model> {

    public PointeurProfileAdapter(Context context, List<Pointage_Model> pointages)
    {
        super(context,0,pointages);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        Pointage_Model pointage=getItem(position);

        if (convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.pointeur_profile_cell, parent, false);

        TextView pointageDate=convertView.findViewById(R.id.cell_pointagep);

        pointageDate.setText(pointage.getDate());



        return convertView;
    }
}
