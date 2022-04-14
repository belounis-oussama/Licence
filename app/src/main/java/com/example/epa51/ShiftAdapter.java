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

public class ShiftAdapter extends ArrayAdapter<Shift_Modele> {


    public ShiftAdapter(Context context, List<Shift_Modele> shifts) {
        super(context,0,shifts);}




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Shift_Modele shift_modele=getItem(position);

        if (convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.shiftcell, parent, false);
        TextView nom_shift=convertView.findViewById(R.id.cell_nomshift);
        TextView debut=convertView.findViewById(R.id.cell_debut);
        TextView fin=convertView.findViewById(R.id.cell_fin);

        nom_shift.setText(shift_modele.getNom_shift());
        debut.setText(shift_modele.getDebut());
        fin.setText(shift_modele.getFin());




        return convertView;

    }

}
