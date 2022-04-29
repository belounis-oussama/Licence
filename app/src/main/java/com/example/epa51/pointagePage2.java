package com.example.epa51;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class pointagePage2 extends AppCompatActivity {


    public ArrayList<Goods_Model>goods_models;
    ArrayList<Gear_Model>gear;
    public ArrayList<EtasArret_Model>arret;
    private RecyclerView recyclerView;
    private Button add,delete;
    private EditText nature,nombre,poid;
    ImageView p2Top1,finishpointing;
    public String navire,naturep,brigade,shift,quai,pointeur_name,date_pointage,mode_conditionnement;
    public ArrayList<Gear_Model>Gears;
    String[]permission;
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
        permission=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};


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

                SavePointageData();





                if (VerifyPermission())
                {
                    ExportPointageCsv();
                }
                else {
                    AskPermissionStorage();
                }





                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.clear();
                editor.apply();
            }
        });





        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if (TextUtils.isEmpty(nature.getText().toString())||TextUtils.isEmpty(nombre.getText().toString()) || TextUtils.isEmpty(poid.getText().toString()))
                {
                    Toast.makeText(pointagePage2.this,"Veuillez remplir tous les champs",Toast.LENGTH_SHORT).show();


                }
                else
                {
                    goods_models.add(new Goods_Model(nature.getText().toString(),Integer.parseInt(nombre.getText().toString()),Integer.parseInt(poid.getText().toString())));
                    setAdapter();
                }


            }
        });




    }

    private boolean VerifyPermission()
    {

        //this verify is the user has already accept permission
        boolean permission= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return permission;
    }


    private void AskPermissionStorage()
    {
        ActivityCompat.requestPermissions(this,permission,1);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 1:
            {
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    ExportPointageCsv();
                }
                else
                {
                    Toast.makeText(this,"Permission manquante..",Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    private void ExportPointageCsv() {
        Pointage_db db=new Pointage_db(pointagePage2.this);



        File folder=new File(Environment.getExternalStorageDirectory(),"PointageEpa");
        boolean folderCreated=false;
        if (!folder.exists())
        {
            boolean mkdir = folder.mkdir();//if created folderCreate =true
            Toast.makeText(pointagePage2.this,"succeful"+mkdir,Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(pointagePage2.this,"alredy exist",Toast.LENGTH_SHORT).show();
        }



    }

    private void SavePointageData() {

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();

        if (sharedPreferences.contains("navire")) {  navire= sharedPreferences.getString("navire","");}
        if (sharedPreferences.contains("nature")) {  naturep= sharedPreferences.getString("nature","");}
        if (sharedPreferences.contains("brigade"))  {   brigade= sharedPreferences.getString("brigade","");}
        if (sharedPreferences.contains("shift"))  {shift= sharedPreferences.getString("shift","");}
        if (sharedPreferences.contains("quai")) { quai =sharedPreferences.getString("quai", "");}
        if (sharedPreferences.contains("pointeur_name")) {  pointeur_name =sharedPreferences.getString("pointeur_name", ""); }
        if (sharedPreferences.contains("date_pointage")) {  date_pointage =sharedPreferences.getString("date_pointage", ""); }
        if (sharedPreferences.contains("mode_conditionnement")) { mode_conditionnement =sharedPreferences.getString("mode_conditionnement", ""); }
        Pointage_Model newPointageInfos=new Pointage_Model(-1,pointeur_name,navire,date_pointage,mode_conditionnement,naturep,brigade,shift,quai);

        Pointage_db DB=new Pointage_db(pointagePage2.this);
        DB.AddPointage(newPointageInfos);


        //getting pointage id
        int idPointage=DB.getCurrentPointage();

        //loading list of gears



        Gson gson=new Gson();
        String json=sharedPreferences.getString("ListOfGears",null);
        Type type= new TypeToken<ArrayList<Gear_Model>>() {}.getType();
        Gears =gson.fromJson(json,type);

        if (Gears==null)
        {
            Gears=new ArrayList<>();
        }

        //saving all gears saved in sharedpreferences in the geardatabase

        for (int i=0;i<Gears.size();i++)
        {

            GearPointage_db Geardb=new GearPointage_db(pointagePage2.this);
            Geardb.AddnewPointageGear(Gears.get(i).getId(),idPointage);
        }


       // GearPointage_db dd=new GearPointage_db(pointagePage2.this);
        //Toast.makeText(pointagePage2.this, dd.getGearUsed(idPointage).toString(),Toast.LENGTH_LONG).show();






        GoodsPointage_db Goodsdb=new GoodsPointage_db(pointagePage2.this);



        //saving to gooddatabse
        for (int i=0;i<goods_models.size();i++)
        {
            Goodsdb.AddGood(goods_models.get(i),idPointage);
        }

        //Toast.makeText(pointagePage2.this, Goodsdb.getGood(idPointage).toString(),Toast.LENGTH_LONG).show();

        LoadListofArrets();

        ArretPointage_db Arretdb=new ArretPointage_db(pointagePage2.this);


        for (int i=0;i<arret.size();i++)
        {
            Arretdb.AddArret(arret.get(0),idPointage);
        }






    }

    private void LoadListofArrets() {
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=sharedPreferences.getString("ListOfArrets",null);
        Type type= new TypeToken<ArrayList<EtasArret_Model>>() {}.getType();
        arret =gson.fromJson(json,type);


        if (arret==null)
        {
            arret=new ArrayList<>();
        }
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