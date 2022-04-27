package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class modify_delete_gear extends AppCompatActivity {



    EditText modifytype,modifynumber;
    Button savegear;
    ImageButton backbtn_mdg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_delete_gear);


        modifytype=findViewById(R.id.modiftype);
        modifynumber=findViewById(R.id.modifnumber);
        savegear=findViewById(R.id.savegear);
        backbtn_mdg=findViewById(R.id.backbtn_mdg);


         int id=Integer.parseInt(getIntent().getStringExtra("idkeygear"));

         Gears_db db=new Gears_db(modify_delete_gear.this);


        String type=db.getType(id);
        String number=String.valueOf(db.getNumber(id));


        modifytype.setText(type);
        modifynumber.setText(number);

        savegear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newtype=modifytype.getText().toString();
                String newnumber=modifynumber.getText().toString();

                Gear_Model gearinfo=new Gear_Model(id,newtype,Integer.parseInt(newnumber));


                boolean success = db.UpdateGear(gearinfo);
                Intent intent =new Intent(modify_delete_gear.this,listGears.class);
                startActivity(intent);

            }
        });

        backbtn_mdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent intent =new Intent(modify_delete_gear.this,listGears.class);
                startActivity(intent);
            }
        });


    }

    public void deletegear(View view) {



        MaterialAlertDialogBuilder dialog=new MaterialAlertDialogBuilder(modify_delete_gear.this);
        dialog.setTitle("Avertissement");
        dialog.setMessage("Voulez-vous vraiment supprimer cet engin ?");
        dialog.setIcon(R.drawable.delete_icon);
        dialog.setBackground(getResources().getDrawable(R.drawable.alertdialogbg,null));
        dialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                int id=Integer.parseInt(getIntent().getStringExtra("idkeygear"));

                Gears_db db=new Gears_db(modify_delete_gear.this);
                String newtype=modifytype.getText().toString();
                String newnumber=modifynumber.getText().toString();

                Gear_Model gearinfo=new Gear_Model(id,newtype,Integer.parseInt(newnumber));


                boolean success = db.deleteGear(gearinfo);
                Intent intent =new Intent(modify_delete_gear.this,listGears.class);
                startActivity(intent);
            }
        });

        dialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog.show();

    }
}