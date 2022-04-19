package com.example.epa51;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class pointagePage2 extends AppCompatActivity {


    private ArrayList<Goods_Model>goods_models;
    ArrayList<Gear_Model>gear;
    private RecyclerView recyclerView;
    private Button add,delete;
    private EditText nature,nombre,poid;
    ImageView p2Top1,finishpointing;
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
        finishpointing=findViewById(R.id.finishpointing);


        LoadListData();







        p2Top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(pointagePage2.this,pointagePage1.class);


                saveListdata();


                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

            }
        });





        //change this after the test






        //this code section is for backing up data saved from parent activity
        Intent newintent=getIntent();
        if (newintent.hasExtra("backuplist"))
        {


           ArrayList<Goods_Model> backedupList  = (ArrayList<Goods_Model>) getIntent().getSerializableExtra("backuplist");

            int i ;
            for (i=0; i < backedupList.size(); i++) {

                goods_models.add(backedupList.get(i));setAdapter();
        }






        }






        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goods_models.remove(goods_models.size()-1); //delete last item in the list


                goods_models.clear();
                setAdapter();
            }
        });


        setAdapter();



        finishpointing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Pointage_db db=new Pointage_db(pointagePage2.this);
                //Pointage_Model newPointage=new Pointage_Model(20,"oussa","oussa","oussa","oussa","oussa","oussa","oussa","oussa");
                //boolean b = db.AddPointage(newPointage);










                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.clear();
                editor.apply();
            }
        });





        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goods_models.add(new Goods_Model(nature.getText().toString(),Integer.parseInt(nombre.getText().toString()),Integer.parseInt(poid.getText().toString())));
                setAdapter();
            }
        });




    }

    private void LoadListData() {
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=sharedPreferences.getString("listofgoods",null);
        Type type= new TypeToken<ArrayList<Goods_Model>>() {}.getType();
        goods_models =gson.fromJson(json,type);

        if (goods_models==null)
        {
            goods_models=new ArrayList<>();
        }

    }

    private void saveListdata() {
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(goods_models);
        editor.putString("listofgoods",json);
        editor.apply();
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