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

public class PointageListAdapter extends ArrayAdapter<Pointage_Model> {

    public PointageListAdapter(Context context, List<Pointage_Model> pointages)
    {
        super(context,0,pointages);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Pointage_Model pointage =getItem(position);
        if (convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.pointage_cell, parent, false);
        TextView navire=convertView.findViewById(R.id.navire_cell);
        TextView date=convertView.findViewById(R.id.date_cell);

        navire.setText(pointage.getNom_navire());
        date.setText(pointage.getDate());

        return convertView;
    }
}
