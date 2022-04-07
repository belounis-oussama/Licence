package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.util.List;

public class choose_gear extends AppCompatActivity {


    public MaterialAutoCompleteTextView autoCompleteType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_gear);
        autoCompleteType=findViewById(R.id.autoCompletetype);


        Gears_db db=new Gears_db(choose_gear.this);
        List<String> getalltypes = db.getalltypes();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getalltypes);
        autoCompleteType.setAdapter(adapter);





    }
}