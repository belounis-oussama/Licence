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

public class PointageArretAdapter extends ArrayAdapter<EtasArret_Model> {

    public PointageArretAdapter(Context context, List<EtasArret_Model> gears)
    {
        super(context,0,gears);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        EtasArret_Model arret =getItem(position);
        if (convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.p_arret_cell, parent, false);
        TextView startend_arret_cell=convertView.findViewById(R.id.startend_arret_cell);
        TextView motif_arret_cell=convertView.findViewById(R.id.motif_arret_cell);

        startend_arret_cell.setText("Du "+arret.getStartDate()+" "+arret.getStartHour()+" A "+arret.getEndDate()+" "+arret.getEndHour());
        motif_arret_cell.setText(arret.getReason());

        return convertView;
    }


}
