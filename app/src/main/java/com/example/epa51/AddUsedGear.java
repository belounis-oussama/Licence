package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class AddUsedGear extends AppCompatActivity {


    ListView ListGears;
    ArrayList<Gear_Model>Gears;
    TextView gobackbnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_used_gear);


        //init wigdets
        ListGears=findViewById(R.id.chooseGearList);
        gobackbnt=findViewById(R.id.gobackbnt);


        setListAdapter();  //adapter for listView
        LoadListData();   //load data saved ....list of gears used


        gobackbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(AddUsedGear.this,pointagePage1.class);
                startActivity(intent);
            }
        });



        ListGears.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String type= ListGears.getAdapter().getItem(i).toString();
                String id = type.substring(type.indexOf("id=") + "id=".length(), type.indexOf(","));
                Gears_db db=new Gears_db(AddUsedGear.this);
                String Type=db.getType(Integer.parseInt(id));
                int Number=db.getNumber(Integer.parseInt(id));
                Gears.add(new Gear_Model(Integer.parseInt(id),Type,Number));

                UpdateListData();

                Intent intent =new Intent(AddUsedGear.this,pointagePage1.class);


                startActivity(intent);
            }
        });
    }



    private void UpdateListData() {
        //add new gear to previous data of gears
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(Gears);
        editor.putString("ListOfGears",json);
        editor.apply();

    }

    private void LoadListData() {
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=sharedPreferences.getString("ListOfGears",null);
        Type type= new TypeToken<ArrayList<Gear_Model>>() {}.getType();
        Gears =gson.fromJson(json,type);

        if (Gears==null)
        {
            Gears=new ArrayList<>();
        }
    }

    private void setListAdapter() {

        Gears_db db=new Gears_db(AddUsedGear.this);
        GearAdapter gearAdapter=new GearAdapter(getApplicationContext(),db.getallgears());
        ListGears.setAdapter(gearAdapter);
    }
}