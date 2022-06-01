package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

public class listGears extends AppCompatActivity {
ListView gearlist;
ImageButton add_listgears,backbtn_listgears;


    public void setGearAdapter()
    {

        Gears_db db=new Gears_db(listGears.this);

        GearAdapter gearAdapter=new GearAdapter(getApplicationContext(),db.getallgears());

        //UserAdapter userAdapter=new UserAdapter(getApplicationContext(),User_Model.user_modelArrayList);
        gearlist.setAdapter(gearAdapter);


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gears);

        gearlist=findViewById(R.id.listView_gears);
        add_listgears=findViewById(R.id.add_listgears);
        backbtn_listgears=findViewById(R.id.backbtn_listgears);


        setGearAdapter();
        gearlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(listGears.this,modify_delete_gear.class);
                String type= gearlist.getAdapter().getItem(i).toString();

                String id = type.substring(type.indexOf("id=") + "id=".length(), type.indexOf(","));


                intent.putExtra("idkeygear",id);


                startActivity(intent);
            }
        });

        backbtn_listgears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(listGears.this,admin_dashboard.class);
                startActivity(intent);
                finish();
            }
        });



        add_listgears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(listGears.this,Gear_details.class);
                startActivity(intent);

            }
        });
    }
}