package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.util.List;

public class choose_stoppages extends AppCompatActivity {
    public MaterialAutoCompleteTextView autoCompleteMotif;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_stoppages);
        autoCompleteMotif=findViewById(R.id.autoCompletemotif);


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"motifes", "s"});
        autoCompleteMotif.setAdapter(adapter);

    }
}