package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.util.List;

public class choose_gear extends AppCompatActivity {


    public Button annuler;
    public MaterialAutoCompleteTextView autoCompleteType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_gear);
        autoCompleteType=findViewById(R.id.autoCompletetype);
        annuler=findViewById(R.id.annuler_chooseG);


        Gears_db db=new Gears_db(choose_gear.this);
        List<String> getalltypes = db.getalltypes();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getalltypes);
        autoCompleteType.setAdapter(adapter);

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(choose_gear.this,pointagePage1.class);


                startActivity(intent);
                finish();
            }
        });





    }
}