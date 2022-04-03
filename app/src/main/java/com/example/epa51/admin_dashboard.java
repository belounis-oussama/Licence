package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class admin_dashboard extends AppCompatActivity {

    CardView pointage,sortir,engins,utilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);


        pointage =findViewById(R.id.pointage);
        sortir =findViewById(R.id.sortir);
        engins =findViewById(R.id.engin);
        utilisateur =findViewById(R.id.utilisateur);


        pointage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        engins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//this is temporary
/*
                Gears_db gears_db=new Gears_db(admin_dashboard.this);

                //User_Model new_user=new User_Model(-1,User,Pass);
                Gear_Model one=new Gear_Model(-1,"one",1);
                Gear_Model onee=new Gear_Model(-1,"two",3);

                gears_db.addGear(one);
                gears_db.addGear(onee);

                List<Gear_Model> all= gears_db.getallgears();
                Toast.makeText(admin_dashboard.this,all.toString(),Toast.LENGTH_SHORT).show();
*/


                Intent intent =new Intent(admin_dashboard.this,listGears.class);
                startActivity(intent);







            }
        });

        sortir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(admin_dashboard.this,login.class);
                startActivity(intent);
                finish();

            }
        });


        utilisateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(admin_dashboard.this,listusers.class);
                startActivity(intent);

            }
        });


    }


    }

