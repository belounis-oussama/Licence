package com.example.epa51;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Pointage_details extends AppCompatActivity {

    public BottomNavigationView bottomNav;
    public ImageButton backbtn;
    Button export;
    ActivityResultLauncher<Intent> activityResultLauncher;
    String[]permission;
    String idPointage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointage_details);
        bottomNav=findViewById(R.id.BottomNavigationP);
        bottomNav.setOnNavigationItemSelectedListener(navlistener);
        export=findViewById(R.id.exportpointage);






        //Bundle ID=new Bundle();
        //                ID.putString("idPointage",id);


        idPointage = getIntent().getStringExtra("idPointage");






        PointageBaics pointageBaics =new PointageBaics();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Bundle id=new Bundle();
        id.putString("idPointage",idPointage);
        pointageBaics.setArguments(id);
        fragmentTransaction.replace(R.id.Fragment_Pointage,pointageBaics).commit();


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


        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckPermission())
                {
                    ExportTextFile();

                    Toast.makeText(view.getContext(),"Fichier exporté avec succès dans \n"+(new File(String.valueOf(Environment.getExternalStoragePublicDirectory("Download"))).getAbsolutePath()),Toast.LENGTH_SHORT).show();
                }

                else
                {
                    AskPermissionStorage();
                }
            }
        });


        //display basics in start
        //getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_Pointage,new PointageBaics()).commit();




    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment=null;

            switch (item.getItemId())
            {
                case R.id.pointageBasicsNav:
                    selectedFragment = new PointageBaics();
                    break;

                case R.id.GearsNav:
                    selectedFragment = new gearsFragment();
                    break;

                case R.id.ArretNav:
                    selectedFragment = new ArretFragment();
                    break;

                case R.id.GoodsNav:
                    selectedFragment = new GoodsFragment();
                    break;

            }




            String idPointage = getIntent().getStringExtra("idPointage");
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Bundle id=new Bundle();
            id.putString("idPointage",idPointage);
            selectedFragment.setArguments(id);
            fragmentTransaction.replace(R.id.Fragment_Pointage,selectedFragment).commit();

            //getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_Pointage,selectedFragment).commit();
            return true;
        }
    };









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
            ActivityCompat.requestPermissions(Pointage_details.this,permission,30);
        }
    }




    private boolean CheckPermission() {

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.R)
        {
            return Environment.isExternalStorageManager();
        }
        else
        {
            int writeCheck= ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return writeCheck== PackageManager.PERMISSION_GRANTED ;
        }

    }



    private void ExportTextFile()  {

        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        HSSFSheet hssfSheet=hssfWorkbook.createSheet();



        Pointage_db db=new Pointage_db(getApplicationContext());
        int currentPointageId =Integer.parseInt(idPointage);

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



        /*--------------------------ROW 3 4 5----------------------------------------*/

        GoodsPointage_db goodsdb=new GoodsPointage_db(getApplicationContext());
        List<Goods_Model> goodsData = goodsdb.getGood(currentPointageId);

        HSSFRow row3=hssfSheet.createRow(3);
        HSSFRow row4=hssfSheet.createRow(4);
        HSSFRow row5=hssfSheet.createRow(5);

        row3.createCell(0).setCellValue("nature: ");
        row4.createCell(0).setCellValue("poid: ");
        row5.createCell(0).setCellValue("quantite: ");

        if (!goodsData.isEmpty())
        {



            for (int i=0;i<goodsData.size();i++)
            {
                for (int j=0;j<3 ;j++)
                {
                    HSSFCell cellGood=row3.createCell(i+1);
                    HSSFCell cellGood2=row4.createCell(i+1);
                    HSSFCell cellGood3=row5.createCell(i+1);

                    cellGood.setCellValue(goodsData.get(i).getReference());
                    cellGood2.setCellValue(goodsData.get(i).getPoid());
                    cellGood3.setCellValue(goodsData.get(i).getQuantite());

                }


            }

        }
        else
        {
            HSSFCell cell30=row3.createCell(0);
            cell30.setCellValue("pas de marchandise");

        }



        /*--------------------------ROW 6----------------------------------------*/


        HSSFRow row6=hssfSheet.createRow(6);
        HSSFCell cell63=row6.createCell(3);

        cell63.setCellValue("---------Les engins utilise--------");


        /*--------------------------ROW 7----------------------------------------*/

        GearPointage_db geardb=new GearPointage_db(getApplicationContext());
        List<Integer> gearUsedIds = geardb.getGearUsed(currentPointageId);


        Gears_db gearData=new Gears_db(getApplicationContext());


        HSSFRow row7=hssfSheet.createRow(7);
        if (!gearUsedIds.isEmpty())
        {

            row7.createCell(0).setCellValue("engins ");
            for (int i=0 ; i<gearUsedIds.size() ;i++)
            {
                Gear_Model gearInfo = gearData.getGearInfo(gearUsedIds.get(i));

                HSSFCell cellGear=row7.createCell(i+1);
                cellGear.setCellValue(gearInfo.getGear_type());
            }

        }
        else
        {
            HSSFCell cellGear=row7.createCell(0);
            cellGear.setCellValue("pas d'engins utiliser");
        }



        /*--------------------------ROW 8----------------------------------------*/


        HSSFRow row8=hssfSheet.createRow(8);
        HSSFCell cell83=row8.createCell(3);

        cell83.setCellValue("---------Les arrets de travail--------");


        /*--------------------------ROW 7----------------------------------------*/



        HSSFRow row9=hssfSheet.createRow(9);

        ArretPointage_db arretdb=new ArretPointage_db(getApplicationContext());
        List<EtasArret_Model> arrets = arretdb.getArret(currentPointageId);


        if (!arrets.isEmpty())
        {
            for (int i=0;i<arrets.size();i++)
            {
                HSSFCell cellArret=row9.createCell(i);
                cellArret.setCellValue("Debut: ( date: "+arrets.get(i).getStartDate()+"),(heure: "+arrets.get(i).getStartHour()+")\n "+"Fin: ( date: "+arrets.get(i).getEndDate()+"),(heure: "+arrets.get(i).getEndHour()+") \n Motif: '"+arrets.get(i).getReason()+"'");
            }
        }
        else
        {
            HSSFCell cellArret=row9.createCell(3);
            cellArret.setCellValue("pas d'arret ");
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
}