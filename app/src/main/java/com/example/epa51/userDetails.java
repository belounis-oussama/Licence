package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class userDetails extends AppCompatActivity {

    private EditText nameedittext, passwordedittext;
    Button adduser;
    ImageButton backbtn_details;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        nameedittext=findViewById(R.id.editname);
        passwordedittext=findViewById(R.id.editpassword);
        adduser=findViewById(R.id.adduser);
        backbtn_details=findViewById(R.id.backbtn_details);




        backbtn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });





        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String User=nameedittext.getText().toString();
                String Pass=passwordedittext.getText().toString();
                User_Model user;


                //if user dosent fill all textfields
                if (TextUtils.isEmpty(User)||TextUtils.isEmpty(Pass))
                {
                    Toast.makeText(userDetails.this,"Veuillez remplir tous les champs",Toast.LENGTH_SHORT).show();


                }

                else
                {
                    User_db data=new User_db(userDetails.this);

                    if (data.checkusername(User)==true)
                    {
                        Toast.makeText(userDetails.this,"User already exists",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        int id= User_Model.user_modelArrayList.size();
                        User_Model new_user=new User_Model(-1,User,Pass);


                        boolean success = data.addUser(new_user);


                        Intent intent =new Intent(userDetails.this,listusers.class);
                        startActivity(intent);
                        finish();
                    }


                }


            }
        });
    }





    public void saveUser(View view) {

        String User=nameedittext.getText().toString().trim();
        String Pass=passwordedittext.getText().toString().trim();
        User_Model user;


        //if user dosent fill all textfields
        if (TextUtils.isEmpty(User)||TextUtils.isEmpty(Pass))
        {
            Toast.makeText(userDetails.this,"Veuillez remplir tous les champs",Toast.LENGTH_SHORT).show();

        }

        else
        {
            User_db db=new User_db(userDetails.this);
        }

        Intent intent =new Intent(userDetails.this,listusers.class);
        startActivity(intent);
        finish();

    }
}