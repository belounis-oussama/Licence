package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class pointagePage1 extends AppCompatActivity {

    Calendar calendar;
    TextInputEditText dateinpute;
    ImageView addgearpointgae,addarret,p1Top2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointage_page1);

        dateinpute=findViewById(R.id.dateinpute);
        addgearpointgae=findViewById(R.id.addgearpointgae);
        addarret=findViewById(R.id.addarret);
        p1Top2=findViewById(R.id.p1Top2);







        calendar=Calendar.getInstance();
        String cureentdate= DateFormat.getDateInstance().format(calendar.getTime());


        dateinpute.setText(cureentdate);
        dateinpute.setKeyListener(null);  //so user can change date


        p1Top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(pointagePage1.this,pointagePage2.class);


                Intent newintent=getIntent();
                //save data history
                if (newintent.hasExtra("listofgoods"))
                {

                    ArrayList<Goods_Model>goods_models=new ArrayList<>();
                    ArrayList<Goods_Model> backuplist = (ArrayList<Goods_Model>) getIntent().getSerializableExtra("listofgoods");
                    intent.putExtra("backuplist",backuplist);

                }



                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });

        addarret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(pointagePage1.this,choose_stoppages.class);


                startActivity(intent);
            }
        });


        addgearpointgae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(pointagePage1.this,choose_gear.class);


                startActivity(intent);

            }
        });






    }
}