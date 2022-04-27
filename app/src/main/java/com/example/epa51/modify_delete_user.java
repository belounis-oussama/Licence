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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class modify_delete_user extends AppCompatActivity {

    EditText fullname,password;
    Button save;
    TextView deletetext;
    ImageView deleteimg;
    ImageButton backbtn_md;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_delete_user);

        fullname=findViewById(R.id.modifname);
        password=findViewById(R.id.modifpass);
        save=findViewById(R.id.save);
        deletetext=findViewById(R.id.deletetext);
        deleteimg=findViewById(R.id.deleteimg);
        backbtn_md=findViewById(R.id.backbtn_md);


        //String id=getIntent().getStringExtra("idkey");

        int id=Integer.parseInt(getIntent().getStringExtra("idkey"));

       // Toast.makeText(modify_delete_user.this,getIntent().getStringExtra("idkey"),Toast.LENGTH_SHORT).show();

        User_db db=new User_db(modify_delete_user.this);
        String username=db.getName(id);
        String pass=db.getPassword(id);
       // Toast.makeText(modify_delete_user.this,username+password,Toast.LENGTH_SHORT).show();

       fullname.setText(username);
       password.setText(pass);






        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newname=fullname.getText().toString();
                String newpass=password.getText().toString();

                User_Model userinfo=new User_Model(id,newname,newpass);


                boolean success = db.updatedata(userinfo);
                Intent intent =new Intent(modify_delete_user.this,listusers.class);
                startActivity(intent);
                finish();
            }
        });





        deletetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                MaterialAlertDialogBuilder dialog=new MaterialAlertDialogBuilder(modify_delete_user.this);
                dialog.setTitle("Avertissement");
                dialog.setMessage("Voulez-vous vraiment supprimer ce pointeur ?");
                dialog.setIcon(R.drawable.delete_icon);
                dialog.setBackground(getResources().getDrawable(R.drawable.alertdialogbg,null));
                dialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String newname=fullname.getText().toString();
                        String newpass=password.getText().toString();

                        User_Model userinfo=new User_Model(id,newname,newpass);


                        boolean success = db.deleteUser(userinfo);
                        Intent intent =new Intent(modify_delete_user.this,listusers.class);
                        startActivity(intent);
                        finish();
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



        deleteimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newname=fullname.getText().toString();
                String newpass=password.getText().toString();

                User_Model userinfo=new User_Model(id,newname,newpass);


                boolean success = db.deleteUser(userinfo);
                Intent intent =new Intent(modify_delete_user.this,listusers.class);
                startActivity(intent);
                finish();
            }
        });



        backbtn_md.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(modify_delete_user.this,listusers.class);

                finish();
            }
        });
    }
}