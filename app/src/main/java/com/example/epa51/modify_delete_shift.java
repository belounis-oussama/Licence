package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class modify_delete_shift extends AppCompatActivity {
    MaterialButton saveEditShift;
    TextInputEditText editShiftName,editStartshift,editEndShift;

    TextView deletetextshift;
    ImageView deleteimgshift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_delete_shift);


        editShiftName=findViewById(R.id.editShiftName);
        editStartshift=findViewById(R.id.editStartshift);
        editEndShift=findViewById(R.id.editEndShift);
        saveEditShift=findViewById(R.id.saveEditShift);




        int id=Integer.parseInt(getIntent().getStringExtra("idkeyshift"));

        Shift_db db=new Shift_db(modify_delete_shift.this);


        String shiftname=db.getShiftName(id);
        String shiftstart=db.getShiftStrat(id);
        String shiftend=db.getShiftEnd(id);

        editShiftName.setText(shiftname);
        editStartshift.setText(shiftstart);
        editEndShift.setText(shiftend);




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
}