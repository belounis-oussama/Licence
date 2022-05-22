package com.example.epa51;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.epa51.databinding.ActivityAddUsedGearBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
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
    ActivityResultLauncher<Intent> activityResultLauncher;

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


        activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                if (result.getResultCode()== Activity.RESULT_OK)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
                    {
                        if (Environment.isExternalStorageManager())
                        {
                            Toast.makeText(getApplicationContext(),"permission Granted" , Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"permission Denied" , Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
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

                 SavePointageData(); //save pointage to database

                if (CheckPermission())
                {
                    ExportTextFile();
                    openFinishedDialog();
                    Toast.makeText(getApplicationContext(),"Exported",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    AskPermissionStorage();
                }

                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.clear();
                editor.apply();

                //save user logged in




                String currentUserLoggeed=db.getPointageData(db.getCurrentPointage()).getNom_pointeur();
                editor.putString("pointeur_name",currentUserLoggeed);
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

    private void openFinishedDialog() {

        FinishedDialog finishedDialog=new FinishedDialog();
        finishedDialog.show(getSupportFragmentManager(),"FinishedDialog");
    }

    private boolean CheckPermission() {

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.R)
        {
            return Environment.isExternalStorageManager();
        }
        else
        {
            int writeCheck=ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return writeCheck==PackageManager.PERMISSION_GRANTED ;
        }

    }

    private void ExportTextFile()  {

        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        HSSFSheet hssfSheet=hssfWorkbook.createSheet();



        Pointage_db db=new Pointage_db(pointagePage2.this);
        int currentPointageId = db.getCurrentPointage();

        Pointage_Model pointageData = db.getPointageData(currentPointageId);


        /*--------------------------ROW 0----------------------------------------*/

        HSSFRow row0=hssfSheet.createRow(0);
        HSSFCell cell0=row0.createCell(0);
        cell0.setCellValue("Pointeur name= "+pointageData.getNom_pointeur()); //cell0


        HSSFCell cell2=row0.createCell(2);
        cell2.setCellValue("Date = "+pointageData.getDate()); //cell2


        HSSFCell cell4 =row0.createCell(4);
        cell4.setCellValue("Nature de marchandise = "+pointageData.getNature_marchandise()); //cell4

        HSSFCell cell6= row0.createCell(6);
        cell6.setCellValue("Shift = "+pointageData.getShift()); //cell6


        /*--------------------------ROW 1----------------------------------------*/


        HSSFRow row1=hssfSheet.createRow(1);
        HSSFCell cell10=row1.createCell(0);
        cell10.setCellValue("Nom de navire "+pointageData.getNom_navire()); //cell0

        HSSFCell cell12=row1.createCell(2);
        cell12.setCellValue("Mode de conditionement = "+pointageData.getMode_conditionnement()); //cell2

        HSSFCell cell14 =row1.createCell(4);
        cell14.setCellValue("Brigade = "+pointageData.getBrigade()); //cell4

        HSSFCell cell16= row1.createCell(6);
        cell16.setCellValue("Quai = "+pointageData.getQuai()); //cell6


        /*--------------------------ROW 2----------------------------------------*/

        HSSFRow row2=hssfSheet.createRow(2);
        HSSFCell cell20=row2.createCell(3);
        cell20.setCellValue("---------les marchandisee--------");



        /*--------------------------ROW 3----------------------------------------*/

        GoodsPointage_db goodsdb=new GoodsPointage_db(pointagePage2.this);
        List<Goods_Model> goodsData = goodsdb.getGood(currentPointageId);

        HSSFRow row3=hssfSheet.createRow(3);

        if (!goodsData.isEmpty())
        {

            for (int i=0;i<goodsData.size();i++)
            {
                HSSFCell cellGood=row3.createCell(i);
                cellGood.setCellValue(goodsData.get(i).toString());
            }

        }
        else
        {
            HSSFCell cell30=row3.createCell(0);
            cell30.setCellValue("pas de marchandise");

        }




        /*--------------------------ROW 4----------------------------------------*/


        HSSFRow row4=hssfSheet.createRow(4);
        HSSFCell cell23=row4.createCell(3);

        cell23.setCellValue("---------Les engins utilise--------");


        /*--------------------------ROW 5----------------------------------------*/

        GearPointage_db geardb=new GearPointage_db(pointagePage2.this);
        List<Integer> gearUsedIds = geardb.getGearUsed(currentPointageId);


        Gears_db gearData=new Gears_db(pointagePage2.this);


        HSSFRow row5=hssfSheet.createRow(5);
        if (!gearUsedIds.isEmpty())
        {

        for (int i=0 ; i<gearUsedIds.size() ;i++)
        {
            Gear_Model gearInfo = gearData.getGearInfo(gearUsedIds.get(i));

            HSSFCell cellGear=row5.createCell(i);
            cellGear.setCellValue(gearInfo.getGear_type());
        }

        }
        else
        {
            HSSFCell cellGear=row5.createCell(0);
            cellGear.setCellValue("pas d'engins utiliser");
        }



        /*--------------------------ROW 6----------------------------------------*/


        HSSFRow row6=hssfSheet.createRow(6);
        HSSFCell cell63=row6.createCell(3);

        cell63.setCellValue("---------Les arrets de travail--------");


        /*--------------------------ROW 7----------------------------------------*/


        HSSFRow row7=hssfSheet.createRow(7);

        ArretPointage_db arretdb=new ArretPointage_db(pointagePage2.this);
        List<EtasArret_Model> arrets = arretdb.getArret(currentPointageId);


        if (!arret.isEmpty())
        {
            for (int i=0;i<arrets.size();i++)
            {
                HSSFCell cellArret=row7.createCell(i);
                cellArret.setCellValue(arrets.get(i).toString());
            }
        }
        else
        {
            HSSFCell cellArret=row7.createCell(3);
            cellArret.setCellValue("pas d'arret");
        }


        String navire = pointageData.getNom_navire();
        String date = pointageData.getDate();

        File filePath=new File(Environment.getExternalStoragePublicDirectory("Download"),navire+" ("+date+").xls");
        try {
        if (!filePath.exists())
        {

                filePath.createNewFile();
        }

        FileOutputStream fileOutputStream=new FileOutputStream(filePath);
        hssfWorkbook.write(fileOutputStream);

        if (fileOutputStream!=null)
        {
            fileOutputStream.flush();
            fileOutputStream.close();
        }


        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void AskPermissionStorage()
    {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.R)
        {
            try {
                Intent intent=new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s",new Object[]{getApplicationContext().getPackageName()})));
                activityResultLauncher.launch(intent);
            }
            catch (Exception e)
            {
                Intent intent=new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                activityResultLauncher.launch(intent);
            }
        }

        else {
            ActivityCompat.requestPermissions(pointagePage2.this,permission,30);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 30:
                if (grantResults.length>0)
                {
                    boolean write=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    if (write)
                    {
                        Toast.makeText(this,"Permission Granded..",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(this,"Permission manquante..",Toast.LENGTH_LONG).show();
                    }

                }

                else {
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_LONG).show();
                }
                break;
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