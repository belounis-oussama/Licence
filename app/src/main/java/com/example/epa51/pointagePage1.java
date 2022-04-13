package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateHandle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class pointagePage1 extends AppCompatActivity {

    Calendar calendar;
    TextInputEditText dateinpute,navire,nature;
    AutoCompleteTextView shift,quai,brigade;
    ImageView addgearpointgae,addarret,p1Top2,p1to_emdeb;
    ListView listofgears,listofarrets;
    public ArrayList<EtasArret_Model> etasArret_models;
    public ArrayList<Gear_Model> gearstest;
    public static final String SHARED_PREFS ="sharedPrefs";






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


        String[] shifts=new String[]{"shift 1","shift 2","shift 3","shift 4"};
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,R.layout.dropdownmenu_shift,shifts);
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




        getData();
        loadListdata();
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
        dateinpute.setKeyListener(null);  //so user can change date



        p1to_emdeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(pointagePage1.this,debarq_embarq.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });



        p1Top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                saveListdata();







                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });

        addarret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(pointagePage1.this,choose_stoppages.class);

                intent.putExtra("currentarret",etasArret_models);

                startActivity(intent);
            }
        });


        addgearpointgae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(pointagePage1.this,choose_gear.class);
                intent.putExtra("currentgear",gearstest);

                startActivity(intent);

            }
        });






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
        editor.apply();

    }




    private void saveListdata() {
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(gearstest);
        editor.putString("listofgears",json);
        editor.apply();
    }



    private void loadListdata()
    {
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=sharedPreferences.getString("listofgears",null);
        Type type= new TypeToken<ArrayList<Gear_Model>>() {}.getType();
        gearstest =gson.fromJson(json,type);

        if (gearstest==null)
        {
            gearstest=new ArrayList<>();
        }

    }



    private void setArretAdapter() {







        etasArret_models=new ArrayList<>();

        PointageArretAdapter pointageArretAdapter=new PointageArretAdapter(getApplicationContext(),etasArret_models);
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
        PointageGearAdapter pointageGearAdapter=new PointageGearAdapter(getApplicationContext(),gearstest);
        listofgears.setAdapter(pointageGearAdapter);

        listofgears.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {


                gearstest.remove(i);
                pointageGearAdapter.notifyDataSetChanged();




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