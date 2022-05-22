package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public class Pointeur_profile extends AppCompatActivity {
    ImageButton add;
    ImageButton logout;
    ListView pointages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointeur_profile);
        initWidget();
        setAdapter();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Pointeur_profile.this,debarq_embarq.class);
                startActivity(intent);
                finish();
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Pointeur_profile.this,login.class);
                startActivity(intent);
                finish();
            }
        });






    }

    private void setAdapter() {



        Pointage_db db=new Pointage_db(Pointeur_profile.this);


        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();

        String pointeur_name = sharedPreferences.getString("pointeur_name", "");
        PointeurProfileAdapter adapter=new PointeurProfileAdapter(Pointeur_profile.this,db.getPointagesOfUser(pointeur_name));
        pointages.setAdapter(adapter);

    }

    private void initWidget() {
        add=findViewById(R.id.addnewpointageprofile);
        logout=findViewById(R.id.logoutprofile);
        pointages=findViewById(R.id.listViewPoiteurwork);
    }
}