package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class pointagePage2 extends AppCompatActivity {


    private ArrayList<Goods_Model>goods_models;
    private RecyclerView recyclerView;
    private Button add,delete;
    private EditText nature,nombre,poid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointage_page2);

        recyclerView=findViewById(R.id.pointage_recycle);
        add=findViewById(R.id.pointage2add);
        delete=findViewById(R.id.pointage2delete);
        nature=findViewById(R.id.natureinpute);
        poid=findViewById(R.id.poidinput);
        nombre=findViewById(R.id.nombreinpute);



        goods_models=new ArrayList<>();

        //change this after the test









        setAdapter();

        


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goods_models.add(new Goods_Model(nature.getText().toString(),Integer.parseInt(nombre.getText().toString()),Integer.parseInt(poid.getText().toString())));
                setAdapter();
            }
        });




    }

    private void setAdapter() {

        pointageRecycleAdapter adapter = new pointageRecycleAdapter(goods_models);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }
}