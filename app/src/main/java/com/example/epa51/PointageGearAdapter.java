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

public class PointageGearAdapter extends ArrayAdapter<Gear_Model> {

    public PointageGearAdapter(Context context, List<Gear_Model> gears)
    {
        super(context,0,gears);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Gear_Model gear =getItem(position);
        if (convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.p_gear_cell, parent, false);
        TextView geartype=convertView.findViewById(R.id.p_typeGear_cell);
        TextView gearnumber=convertView.findViewById(R.id.p_number_cell);

        geartype.setText(gear.getGear_type());
        gearnumber.setText(String.valueOf(gear.getGear_number()));

        return convertView;
    }
}
