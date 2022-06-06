package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListPointages extends AppCompatActivity {

    ListView pointage_list;
    List<Pointage_Model> pointage;
    ImageButton goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pointages);
        pointage_list=findViewById(R.id.listofPointages);
        goback=findViewById(R.id.back_listofpointages);


        setAdapter();
        pointage_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(ListPointages.this,Pointage_details.class);


                String pointage=pointage_list.getAdapter().getItem(i).toString();
                String id = pointage.substring(pointage.indexOf("id=") + "id=".length(), pointage.indexOf(","));


                intent.putExtra("idPointage",id);
                startActivity(intent);

            }
        });


        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ListPointages.this,admin_dashboard.class);

                finish();
            }
        });
    }

    private void setAdapter() {
        Pointage_db db=new Pointage_db(ListPointages.this);

        PointageListAdapter adapter=new PointageListAdapter(getApplicationContext(),db.getAllPointages());
        pointage_list.setAdapter(adapter);


    }
}