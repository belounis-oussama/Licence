package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class ShiftDetails extends AppCompatActivity {

    TextInputEditText startshift,endshift,nameshift;
    MaterialButton addShift;
    ImageButton backbtn_shift_details;
    int hour,minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_details);

        nameshift=findViewById(R.id.newshiftname);
        startshift=findViewById(R.id.startshift);
        endshift=findViewById(R.id.endshift);
        addShift=findViewById(R.id.addShift);
        backbtn_shift_details=findViewById(R.id.backbtn_shift_details);


        startshift.setKeyListener(null);
        endshift.setKeyListener(null);





        backbtn_shift_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Shiftname=nameshift.getText().toString();
                String Start=startshift.getText().toString();
                String end=endshift.getText().toString();

                Shift_Modele shift_modele;


                if (TextUtils.isEmpty(Shiftname)||TextUtils.isEmpty(Start)||TextUtils.isEmpty(end))
                {
                    Toast.makeText(ShiftDetails.this,"Veuillez remplir tous les champs",Toast.LENGTH_SHORT).show();

                }


                else
                {

                    //add check if shift already exists

                    Shift_db data=new Shift_db(ShiftDetails.this);
                    Shift_Modele newShift=new Shift_Modele(-1,Shiftname,Start,end);
                    data.addShift(newShift);
                    Toast.makeText(ShiftDetails.this,"Shift ajout?? avec succ??s ",Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });
    }

    public void timerstart(View view) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedhour, int selectedminute) {

                hour=selectedhour;
                minute=selectedminute;
                startshift.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
            }
        };

        int style= AlertDialog.THEME_HOLO_LIGHT;

        TimePickerDialog timePickerDialog=new TimePickerDialog(this,AlertDialog.THEME_HOLO_LIGHT,onTimeSetListener,hour,minute,true);
        timePickerDialog.setTitle("choisissez l'heure de debut");
        timePickerDialog.show();
    }

    public void timerend(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedhour, int selectedminute) {

                hour=selectedhour;
                minute=selectedminute;
                endshift.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
            }
        };

        int style= AlertDialog.THEME_HOLO_LIGHT;

        TimePickerDialog timePickerDialog=new TimePickerDialog(this,AlertDialog.THEME_HOLO_LIGHT,onTimeSetListener,hour,minute,true);
        timePickerDialog.setTitle("choisissez l'heure de debut");
        timePickerDialog.show();
    }
}