package com.example.epa51;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class pointagePage2 extends AppCompatActivity {


    private ArrayList<Goods_Model>goods_models;
    private RecyclerView recyclerView;
    private Button add,delete;
    private EditText nature,nombre,poid;
    ImageView p2Top1;
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
        p2Top1=findViewById(R.id.p2Top1);


        p2Top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(pointagePage2.this,pointagePage1.class);


                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });



        goods_models=new ArrayList<>();

        //change this after the test







        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods_models.remove(goods_models.size()-1); //delete last item in the list


                setAdapter();
            }
        });


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


        ItemTouchHelper.SimpleCallback itemtouchhelpercallback= new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT |ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                goods_models.remove(viewHolder.getAdapterPosition());

                adapter.notifyDataSetChanged();
                setAdapter();


            }
        };
        new ItemTouchHelper(itemtouchhelpercallback).attachToRecyclerView(recyclerView);

    }
}