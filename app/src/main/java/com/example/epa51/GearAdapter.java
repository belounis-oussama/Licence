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

public class GearAdapter extends ArrayAdapter<Gear_Model> {public GearAdapter(Context context, List<Gear_Model> gears) {
    super(context,0,gears);


}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Gear_Model gear_model=getItem(position);
        if (convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.gear_cell, parent, false);
        TextView type=convertView.findViewById(R.id.cell_type);
        TextView number=convertView.findViewById(R.id.cell_number);

        type.setText(gear_model.getGear_type());
        number.setText(String.valueOf(gear_model.getGear_number()));



        return convertView;

    }








}
