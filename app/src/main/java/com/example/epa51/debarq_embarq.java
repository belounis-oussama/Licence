package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class debarq_embarq extends AppCompatActivity {
    ImageView embarqu_btn;
    ImageView debarqu_btn,vector;
    Button retour;

    TextView titledebemb,titledebemb2,embtext,debtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debarq_embarq);


        embarqu_btn=findViewById(R.id.embarquement_btn);
        debarqu_btn=findViewById(R.id.derquemnt_btn);
        retour=findViewById(R.id.retour);
        vector=findViewById(R.id.vectorembdeb);
        titledebemb=findViewById(R.id.titledebemb);
        titledebemb2=findViewById(R.id.titledebemb2);
        embtext=findViewById(R.id.embtext);
        debtext=findViewById(R.id.debtext);





        embarqu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(debarq_embarq.this,"embarquement",Toast.LENGTH_SHORT).show();

                Intent intent =new Intent(debarq_embarq.this,pointagePage1.class);


                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor=sharedPreferences.edit();


                editor.putString("mode_conditionnement","embarquement");
                editor.apply();
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
            }
        });

        debarqu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(debarq_embarq.this,"debarquement",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(debarq_embarq.this,pointagePage1.class);


                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor=sharedPreferences.edit();


                editor.putString("mode_conditionnement","debarquement");
                editor.apply();


               startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
               finish();
            }
        });


        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(debarq_embarq.this,Pointeur_profile.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
            }
        });







    }
}