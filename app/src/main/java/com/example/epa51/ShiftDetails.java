package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ShiftDetails extends AppCompatActivity {

    TextInputEditText startshift,endshift,nameshift;
    MaterialButton addShift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_details);

        nameshift=findViewById(R.id.newshiftname);
        startshift=findViewById(R.id.startshift);
        endshift=findViewById(R.id.endshift);
        addShift=findViewById(R.id.addShift);




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


                    Intent intent =new Intent(ShiftDetails.this,Shiftlist.class);
                    startActivity(intent);
                }
            }
        });
    }
}