package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class choose_stoppages extends AppCompatActivity {
    public MaterialAutoCompleteTextView autoCompleteMotif;

    public TextInputEditText startdate,enddate,starttime,endtime;
    private DatePickerDialog datePickerDialog,datePickerDialog2;
    int hour,minute;
    Button confirm_button_arret;
    ArrayList<EtasArret_Model> arret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_stoppages);
        autoCompleteMotif=findViewById(R.id.autoCompletemotif);
        startdate=findViewById(R.id.startdate);
        enddate=findViewById(R.id.enddate);
        starttime=findViewById(R.id.starttime);
        endtime=findViewById(R.id.endtime);
        confirm_button_arret=findViewById(R.id.confirm_button_arret);


        startdate.setText(getTodayDate());
        enddate.setText(getTodayDate());
        startdate.setKeyListener(null);
        enddate.setKeyListener(null);
        starttime.setKeyListener(null);
        endtime.setKeyListener(null);
        initDatePicker();
        initDatePicker2();












        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"motifes", "s"});
        autoCompleteMotif.setAdapter(adapter);


        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog2.show();
            }
        });



        confirm_button_arret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add copier to save
                arret=new ArrayList<>();
                Intent intent =new Intent(choose_stoppages.this,pointagePage1.class);

                Intent newintent=getIntent();
                if (newintent.hasExtra("currentarret")) {

                    ArrayList<EtasArret_Model> newaaret = (ArrayList<EtasArret_Model>) getIntent().getSerializableExtra("currentarret");

                    //Toast.makeText(pointagePage1.this,newgear.toString(),Toast.LENGTH_SHORT).show();

                    for (int i = 0; i < newaaret.size(); i++) {
                        arret.add(newaaret.get(i));

                    }

                }






                arret.add(new EtasArret_Model(startdate.getText().toString(),starttime.getText().toString(),enddate.getText().toString(),endtime.getText().toString(),autoCompleteMotif.getText().toString()));
                intent.putExtra("arretList",arret);
                startActivity(intent);

            }
        });

    }

    private void initDatePicker2() {

        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month=month+1;
                String date=makeDateString(day,month,year);
                enddate.setText(date);

            }
        };
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        month=month+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog2=new DatePickerDialog(this,AlertDialog.THEME_HOLO_LIGHT,dateSetListener,year,month,day);

    }

    private String getTodayDate() {


        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        month=month+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        return makeDateString(day,month,year);
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {


                month=month+1;
                String date = makeDateString(day,month,year);
                startdate.setText(date);
            }
        };







        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);


        datePickerDialog=new DatePickerDialog(this,AlertDialog.THEME_HOLO_LIGHT,dateSetListener,year,month,day);
    }

    private String makeDateString(int day, int month, int year) {

        return day+"/"+ month+"/" +year;
    }


    public void popupTimer(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedhour, int selectedminute) {

                hour=selectedhour;
                minute=selectedminute;
                starttime.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
            }
        };

        int style=AlertDialog.THEME_HOLO_LIGHT;

        TimePickerDialog timePickerDialog=new TimePickerDialog(this,AlertDialog.THEME_HOLO_LIGHT,onTimeSetListener,hour,minute,true);
        timePickerDialog.setTitle("choisissez l'heure de debut");
        timePickerDialog.show();

    }

    public void popupTimerend(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedhour, int selectedminute) {

                hour=selectedhour;
                minute=selectedminute;
                endtime.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
            }
        };

        int style=AlertDialog.THEME_HOLO_LIGHT;

        TimePickerDialog timePickerDialog=new TimePickerDialog(this,AlertDialog.THEME_HOLO_LIGHT,onTimeSetListener,hour,minute,true);
        timePickerDialog.setTitle("choisissez l'heure de debut");
        timePickerDialog.show();
    }
}