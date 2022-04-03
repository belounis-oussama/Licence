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

public class UserAdapter extends ArrayAdapter <User_Model>{
    public UserAdapter(Context context, List<User_Model> users)
    {
super(context,0,users);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        User_Model user =getItem(position);
        if (convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.user_cell, parent, false);
        TextView username=convertView.findViewById(R.id.cell_name);
        TextView password=convertView.findViewById(R.id.cell_pass);

        username.setText(user.getComplete_name());
        password.setText(user.getPassword());

        return convertView;
    }
}
