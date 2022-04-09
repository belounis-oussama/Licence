package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class choose_gear extends AppCompatActivity {


    public Button annuler;
    public MaterialAutoCompleteTextView autoCompleteType;
    public TextInputEditText number;
    public Button save_gearform;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_gear);
        autoCompleteType=findViewById(R.id.autoCompletetype);
        annuler=findViewById(R.id.annuler_chooseG);
        number=findViewById(R.id.gearnumero);
        save_gearform=findViewById(R.id.save_gearform);


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


        save_gearform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent =new Intent(choose_gear.this,pointagePage1.class);

                ArrayList<Gear_Model> gear=new ArrayList<>();



                Intent newintent=getIntent();

                if (newintent.hasExtra("currentgear")) {

                    ArrayList<Gear_Model> newgear = (ArrayList<Gear_Model>) getIntent().getSerializableExtra("currentgear");

                    //Toast.makeText(pointagePage1.this,newgear.toString(),Toast.LENGTH_SHORT).show();

                    for (int i = 0; i < newgear.size(); i++) {
                        gear.add(newgear.get(i));

                    }

                }



                    Random ran =new Random();
                int x=ran.nextInt(100);




                gear.add(new Gear_Model(x,autoCompleteType.getText().toString(),Integer.parseInt(number.getText().toString())));

                intent.putExtra("gearused",gear);
                startActivity(intent);
            }
        });




    }
}