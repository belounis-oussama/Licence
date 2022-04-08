package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

public class choose_stoppages extends AppCompatActivity {
    public MaterialAutoCompleteTextView autoCompleteMotif;

    public TextInputEditText startdate,enddate;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_stoppages);
        autoCompleteMotif=findViewById(R.id.autoCompletemotif);
        startdate=findViewById(R.id.startdate);
        enddate=findViewById(R.id.enddate);
        startdate.setText(getTodayDate());
        enddate.setText(getTodayDate());
        startdate.setKeyListener(null);
        initDatePicker();





        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"motifes", "s"});
        autoCompleteMotif.setAdapter(adapter);


        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

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




}