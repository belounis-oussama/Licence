package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class modify_delete_shift extends AppCompatActivity {
    MaterialButton saveEditShift;
    TextInputEditText editShiftName,editStartshift,editEndShift;

    TextView deletetextshift;
    ImageView deleteimgshift;
    ImageView Backbtn;
    int hour,minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_delete_shift);


        editShiftName=findViewById(R.id.editShiftName);
        editStartshift=findViewById(R.id.editStartshift);
        editEndShift=findViewById(R.id.editEndShift);
        saveEditShift=findViewById(R.id.saveEditShift);
        Backbtn=findViewById(R.id.backbtn_shift_modifydelete);




        int id=Integer.parseInt(getIntent().getStringExtra("idkeyshift"));

        Shift_db db=new Shift_db(modify_delete_shift.this);


        String shiftname=db.getShiftName(id);
        String shiftstart=db.getShiftStrat(id);
        String shiftend=db.getShiftEnd(id);

        editShiftName.setText(shiftname);
        editStartshift.setText(shiftstart);
        editEndShift.setText(shiftend);

        editStartshift.setKeyListener(null);
        editEndShift.setKeyListener(null);



        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(modify_delete_shift.this,Shiftlist.class);
                startActivity(intent);
            }
        });

        saveEditShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String newName=editShiftName.getText().toString();
                String newStart=editStartshift.getText().toString();
                String newEnd=editEndShift.getText().toString();


                Shift_Modele shiftInfo=new Shift_Modele(id,newName,newStart,newEnd);


                boolean success = db.UpdateShift(shiftInfo);
                Intent intent =new Intent(modify_delete_shift.this,Shiftlist.class);
                startActivity(intent);
            }
        });
    }

    public void DeleteShift(View view) {

        int id=Integer.parseInt(getIntent().getStringExtra("idkeyshift"));

        Shift_db db=new Shift_db(modify_delete_shift.this);
        String Name=editShiftName.getText().toString();
        String Start=editStartshift.getText().toString();
        String End=editEndShift.getText().toString();



        Shift_Modele shiftInfo=new Shift_Modele(id,Name,Start,End);

        boolean success=db.DeleteShift(shiftInfo);



        Intent intent =new Intent(modify_delete_shift.this,Shiftlist.class);
        startActivity(intent);
    }

    public void TimerStart(View view) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedhour, int selectedminute) {

                hour=selectedhour;
                minute=selectedminute;
                editStartshift.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
            }
        };

        int style= AlertDialog.THEME_HOLO_LIGHT;

        TimePickerDialog timePickerDialog=new TimePickerDialog(this,AlertDialog.THEME_HOLO_LIGHT,onTimeSetListener,hour,minute,true);
        timePickerDialog.setTitle("choisissez l'heure de debut");
        timePickerDialog.show();
    }

    public void TimerEnd(View view) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedhour, int selectedminute) {

                hour=selectedhour;
                minute=selectedminute;
                editEndShift.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
            }
        };

        int style= AlertDialog.THEME_HOLO_LIGHT;

        TimePickerDialog timePickerDialog=new TimePickerDialog(this,AlertDialog.THEME_HOLO_LIGHT,onTimeSetListener,hour,minute,true);
        timePickerDialog.setTitle("choisissez l'heure de debut");
        timePickerDialog.show();
    }
}