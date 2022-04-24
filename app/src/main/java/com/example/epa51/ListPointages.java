package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class ListPointages extends AppCompatActivity {

    RecyclerView pointage_list;
    List<Pointage_Model> pointage;
    ImageButton goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pointages);

        pointage_list=findViewById(R.id.listofPointages);
        goback=findViewById(R.id.back_listofpointages);

        Pointage_db db=new Pointage_db(ListPointages.this);

        pointage=db.getAllPointages();
        setAdapter();




        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setAdapter() {
        PointageListAdapter adapter = new PointageListAdapter(pointage);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        pointage_list.setLayoutManager(layoutManager);
        pointage_list.setItemAnimator(new DefaultItemAnimator());

        pointage_list.setAdapter(adapter);
    }
}