package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Gear_details extends AppCompatActivity {


    private EditText editgear, editnumber,canceltext;
    ImageView cancelimg;
    Button addGear;
    ImageView backbtn_gear_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gear_details);


        editgear=findViewById(R.id.editgear);
        editnumber=findViewById(R.id.editnumber);
        addGear=findViewById(R.id.addGear);
        backbtn_gear_details=findViewById(R.id.backbtn_gear_details);


        addGear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Gear=editgear.getText().toString();
                String Number=editnumber.getText().toString();
                Gear_Model gear_model;


                //if Gear dosent fill all textfields
                if (TextUtils.isEmpty(Gear)||TextUtils.isEmpty(Number))
                {
                    Toast.makeText(Gear_details.this,"Veuillez remplir tous les champs",Toast.LENGTH_SHORT).show();


                }

                else
                {
                    Gears_db data=new Gears_db(Gear_details.this);

                    //dont forget to implement this plz

                    if (data.Checkgearinfos(Gear,Number)==true)
                    {
                        Toast.makeText(Gear_details.this,"Gear already exists",Toast.LENGTH_SHORT).show();
                    }
else


                    {
                        int id= Gear_Model.gear_modelArrayList.size();
                        Gear_Model new_Gear=new Gear_Model(-1,Gear,Integer.parseInt(Number));


                        boolean success = data.addGear(new_Gear);


                        Intent intent =new Intent(Gear_details.this,listGears.class);
                        startActivity(intent);

                    }

                }
            } });



        backbtn_gear_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Gear_details.this,listGears.class);
                startActivity(intent);

            }
        });
    }

    public void gotolistgear(View view) {
        Intent intent =new Intent(Gear_details.this,listGears.class);
        startActivity(intent);

    }
}