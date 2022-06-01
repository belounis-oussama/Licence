package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateHandle;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class pointagePage1 extends AppCompatActivity {

    Calendar calendar;
    TextInputEditText dateinpute,navire,nature;
    AutoCompleteTextView shift,quai,brigade;
    ImageView addgearpointgae,addarret,p1Top2,p1to_emdeb;
    ListView listofgears,listofarrets;
    public ArrayList<EtasArret_Model> etasArret_models;
    public ArrayList<Gear_Model> gearstest;
    public static final String SHARED_PREFS ="sharedPrefs";
    public ArrayList<Gear_Model>Gears;
    public ArrayList<EtasArret_Model>arret;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointage_page1);



        dateinpute=findViewById(R.id.date);
        addgearpointgae=findViewById(R.id.addgearpointgae);
        addarret=findViewById(R.id.addarret);
        p1Top2=findViewById(R.id.p1Top2);
        listofgears=findViewById(R.id.listofgearsp1);
        listofarrets=findViewById(R.id.listofarrets);
        p1to_emdeb=findViewById(R.id.p1to_emdeb);
        navire=findViewById(R.id.navire);
        nature=findViewById(R.id.nature);
        brigade=findViewById(R.id.brigade);
        quai=findViewById(R.id.quai);
        shift=findViewById(R.id.shift);




        //change this , this is a test add data from db after im using list for now


        Shift_db shift_db=new Shift_db(pointagePage1.this);




        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,R.layout.dropdownmenu_shift,shift_db.getAllShiftNames());
        shift.setAdapter(adapter);
        shift.setKeyListener(null);


        String[] quais=new String[]{"quai 1","quai 2","quai 3","quai 4"};
        ArrayAdapter<String>adapter_quai=new ArrayAdapter<>(this,R.layout.dropdownmenu_shift,quais);
        quai.setAdapter(adapter_quai);
        quai.setKeyListener(null);


        String[] brigades=new String[]{"brigade 1","brigade 2","brigade 3","brigade 4"};
        ArrayAdapter<String>adapter_brigade=new ArrayAdapter<>(this,R.layout.dropdownmenu_shift,brigades);
        brigade.setAdapter(adapter_brigade);
        brigade.setKeyListener(null);



        LoadListofGears();
        LoadListofArrets();
        getData();
        setGearsAdapter();
        setArretAdapter();










        Intent newintent=getIntent();

        if (newintent.hasExtra("gearused"))
        {

            ArrayList<Gear_Model>newgear=(ArrayList<Gear_Model>) getIntent().getSerializableExtra("gearused");

            Toast.makeText(pointagePage1.this,newgear.toString(),Toast.LENGTH_SHORT).show();

            for (int i=0;i<newgear.size();i++)
            {
                gearstest.add(newgear.get(i));

            }

        }


        Intent newintentt=getIntent();

        if (newintentt.hasExtra("arretList"))
        {

            ArrayList<EtasArret_Model>newarret=(ArrayList<EtasArret_Model>) getIntent().getSerializableExtra("arretList");

            Toast.makeText(pointagePage1.this,newarret.toString(),Toast.LENGTH_SHORT).show();

            for (int i=0;i<newarret.size();i++)
            {
                etasArret_models.add(newarret.get(i));

            }

        }







        calendar=Calendar.getInstance();
        String cureentdate= DateFormat.getDateInstance().format(calendar.getTime());


        dateinpute.setText(cureentdate);
        //dateinpute.setKeyListener(null);  //so user can change date



        p1to_emdeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                MaterialAlertDialogBuilder dialog=new MaterialAlertDialogBuilder(pointagePage1.this);
                dialog.setTitle("Avertissement");
                dialog.setMessage("tout les données saisies seront supprimer \n voulez-vous continuer ?");
                dialog.setIcon(R.drawable.ic_baseline_warning_24);
                dialog.setBackground(getResources().getDrawable(R.drawable.alertdialogbg,null));

                dialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent =new Intent(pointagePage1.this,debarq_embarq.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                    }
                });

                dialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
            }
        });



        p1Top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (TextUtils.isEmpty(navire.getText().toString())||TextUtils.isEmpty(nature.getText().toString())||TextUtils.isEmpty(brigade.getText().toString())||TextUtils.isEmpty(shift.getText().toString())||TextUtils.isEmpty(quai.getText().toString()))
                {
                    Toast.makeText(pointagePage1.this,"Veuillez remplir tous les champs",Toast.LENGTH_SHORT).show();

                }

                else
                {
                    Intent intent =new Intent(pointagePage1.this,pointagePage2.class);
                    Intent newintent=getIntent();
                    //save data history from pointage page 2
                    if (newintent.hasExtra("listofgoods"))
                    {

                        ArrayList<Goods_Model>goods_models=new ArrayList<>();
                        ArrayList<Goods_Model> backuplist = (ArrayList<Goods_Model>) getIntent().getSerializableExtra("listofgoods");
                        intent.putExtra("backuplist",backuplist);


                    }

                    saveRecord();
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }



            }
        });

        addarret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(pointagePage1.this,choose_stoppages.class);
                saveListArrets();
                saveRecord();
                startActivity(intent);
            }
        });


        addgearpointgae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveListGears();
                Intent intent =new Intent(pointagePage1.this,AddUsedGear.class);

                saveRecord();
                startActivity(intent);


            }
        });






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

    private void saveListArrets() {
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(arret);
        editor.putString("ListOfArrets",json);
        editor.apply();

    }

    private void LoadListofGears() {

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

    private void saveListGears() {
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(Gears);
        editor.putString("ListOfGears",json);
        editor.apply();
    }

    private void getData() {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();

        if (sharedPreferences.contains("navire")) navire.setText(sharedPreferences.getString("navire",""));
        if (sharedPreferences.contains("nature")) nature.setText(sharedPreferences.getString("nature",""));
        if (sharedPreferences.contains("brigade")) brigade.setText(sharedPreferences.getString("brigade",""));
        if (sharedPreferences.contains("shift")) shift.setText(sharedPreferences.getString("shift",""));
        if (sharedPreferences.contains("quai")) quai.setText(sharedPreferences.getString("quai",""));

    }

    private void saveRecord() {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();


        editor.putString("navire",navire.getText().toString());
        editor.putString("nature",nature.getText().toString());
        editor.putString("brigade",brigade.getText().toString());
        editor.putString("shift",shift.getText().toString());
        editor.putString("quai",quai.getText().toString());
        editor.putString("date_pointage",dateinpute.getText().toString());
        editor.apply();

    }









    private void setArretAdapter() {
        etasArret_models=new ArrayList<>();
        PointageArretAdapter pointageArretAdapter=new PointageArretAdapter(getApplicationContext(),arret);
        listofarrets.setAdapter(pointageArretAdapter);
        listofarrets.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                etasArret_models.remove(i);
                pointageArretAdapter.notifyDataSetChanged();
                return false;
            }
        });


    }



    private void setGearsAdapter() {


        //gearstest=new ArrayList<>();
        PointageGearAdapter pointageGearAdapter=new PointageGearAdapter(getApplicationContext(),Gears);
        listofgears.setAdapter(pointageGearAdapter);

        listofgears.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {






                return false;
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //you need to add this on last button
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }
}