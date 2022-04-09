package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class pointagePage1 extends AppCompatActivity {

    Calendar calendar;
    TextInputEditText dateinpute;
    ImageView addgearpointgae,addarret,p1Top2;
    ListView listofgears,listofarrets;
    public ArrayList<EtasArret_Model> etasArret_models;
    public ArrayList<Gear_Model> gearstest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointage_page1);

        dateinpute=findViewById(R.id.dateinpute);
        addgearpointgae=findViewById(R.id.addgearpointgae);
        addarret=findViewById(R.id.addarret);
        p1Top2=findViewById(R.id.p1Top2);
        listofgears=findViewById(R.id.listofgearsp1);
        listofarrets=findViewById(R.id.listofarrets);
        setGearsAdapter();
        setArretAdapter();




        Intent newintent=getIntent();

        if (newintent.hasExtra("gearused"))
        {

            ArrayList<Gear_Model>newgear=(ArrayList<Gear_Model>) getIntent().getSerializableExtra("gearused");

            Toast.makeText(pointagePage1.this,newgear.toString(),Toast.LENGTH_SHORT).show();

            for (int i=0;i<newgear.size();i++)
            {
                gearstest.add(newgear.get(i));

            }






        }







        calendar=Calendar.getInstance();
        String cureentdate= DateFormat.getDateInstance().format(calendar.getTime());


        dateinpute.setText(cureentdate);
        dateinpute.setKeyListener(null);  //so user can change date


        p1Top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(pointagePage1.this,pointagePage2.class);


                Intent newintent=getIntent();
                //save data history from pointage page 2
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

                intent.putExtra("currentarret",etasArret_models);

                startActivity(intent);
            }
        });


        addgearpointgae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(pointagePage1.this,choose_gear.class);
                intent.putExtra("currentgear",gearstest);

                startActivity(intent);

            }
        });






    }

    private void setArretAdapter() {







        etasArret_models=new ArrayList<>();
        etasArret_models.add(new EtasArret_Model("1/30","1:00","3/4","6:00","mauvais temps"));
        etasArret_models.add(new EtasArret_Model("3/30","3:00","1/4","64:00","idk"));
        PointageArretAdapter pointageArretAdapter=new PointageArretAdapter(getApplicationContext(),etasArret_models);
listofarrets.setAdapter(pointageArretAdapter);
    }

    private void setGearsAdapter() {


        gearstest=new ArrayList<>();









        PointageGearAdapter pointageGearAdapter=new PointageGearAdapter(getApplicationContext(),gearstest);
        listofgears.setAdapter(pointageGearAdapter);

    }
}