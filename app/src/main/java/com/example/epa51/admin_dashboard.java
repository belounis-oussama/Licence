package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class admin_dashboard extends AppCompatActivity {

    CardView pointage,sortir,engins,utilisateur,shift_dash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);


        pointage =findViewById(R.id.pointage);
        sortir =findViewById(R.id.sortir);
        engins =findViewById(R.id.engin);
        utilisateur =findViewById(R.id.utilisateur);
        shift_dash=findViewById(R.id.shift_dash);



        shift_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(admin_dashboard.this,Shiftlist.class);
                startActivity(intent);
            }
        });


        pointage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        engins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



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

