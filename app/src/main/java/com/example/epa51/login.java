package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    Button cnxBtn;
    EditText fullName,password,adminpass;
    TextView admin;
    ImageView adminimg;
    Dialog dialog_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //reference for variables from xml to our java
        cnxBtn=findViewById(R.id.loginBtn);
        fullName=findViewById(R.id.fullname);
        password=findViewById(R.id.password);
        admin=findViewById(R.id.admin);
        adminimg=findViewById(R.id.adminimg);
        adminpass=findViewById(R.id.adminpass);
        dialog_admin=new Dialog(this);



        cnxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //action we want on click

                String User=fullName.getText().toString();
                String Pass=password.getText().toString();
                User_Model user;


                //if user dosent fill all textfields
                if (TextUtils.isEmpty(User)||TextUtils.isEmpty(Pass))
                {
                    Toast.makeText(login.this,"Veuillez remplir tous les champs",Toast.LENGTH_SHORT).show();


                }

                else
                {

                    User_db db=new User_db(login.this);
                    boolean checkinfo=db.checknamepassword(User,Pass);  //true if name and password are matching
                    boolean checkinfoUpper=db.checknamepassword(User.toLowerCase(),Pass.toLowerCase());
                    if (checkinfo==true || checkinfoUpper==true)
                    {


                        Intent intent =new Intent(login.this,Typewriter_welcome.class);
                        intent.putExtra("namekey",User);//send name to typewriter


                        //save username in sharedPreferences , We need it for our DB

                        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor=sharedPreferences.edit();


                        editor.putString("pointeur_name",User);
                        editor.apply();


                        startActivity(intent);
                        finish();




                    }
                    else
                    {
                        Toast.makeText(login.this,"False infos",Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // openDialog();



                Intent intent =new Intent(login.this,admin_dashboard.class);
                startActivity(intent);
                finish();



            }
        });


        adminimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDialog();

               // Intent intent =new Intent(login.this,admin_dashboard.class);
                //startActivity(intent);
                //finish();

            }
        });





    }

    private void openDialog() {

        AdminDialog adminDialog=new AdminDialog();
        adminDialog.show(getSupportFragmentManager(),"Password_Admin");

    }


}