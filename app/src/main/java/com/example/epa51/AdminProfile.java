package com.example.epa51;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminProfile extends AppCompatActivity {

    CircleImageView profilePic;
    TextView changePic;
    TextInputEditText name,date,password;
    MaterialButton save,profile_retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        profilePic=findViewById(R.id.circleImageView);
        changePic=findViewById(R.id.changepic);
        name=findViewById(R.id.nom_admin);
        date=findViewById(R.id.date_admin);
        password=findViewById(R.id.mdp_admin);
        save=findViewById(R.id.saveProfilBtn);
        profile_retour=findViewById(R.id.profile_retour);


        Admin_db db=new Admin_db(AdminProfile.this);

        if (db.getAllNames().isEmpty() == false)
        {
            ContentValues lastAdmin = db.getLastAdmin();


            name.setText(lastAdmin.get("FULLNAME").toString());
            password.setText(lastAdmin.get("PASSWORD").toString());
            date.setText(lastAdmin.get("DATE").toString());

            byte [] imagebyte= (byte[]) lastAdmin.get("IMAGE");

            Bitmap Image=BitmapFactory.decodeByteArray(imagebyte,0,imagebyte.length);
            profilePic.setImageBitmap(Image);
        }





       // Toast.makeText(AdminProfile.this, lastAdmin.toString(),Toast.LENGTH_SHORT).show();






        //request permission for camera
        if (ContextCompat.checkSelfPermission(AdminProfile.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(AdminProfile.this,new String[]{Manifest.permission.CAMERA},100);
        }

        changePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open camera
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent,100);

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Admin_db db=new Admin_db(AdminProfile.this);

                Bitmap bm=((BitmapDrawable)profilePic.getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] Image=byteArrayOutputStream.toByteArray();
                boolean b = db.InsertData(name.getText().toString(), password.getText().toString(), date.getText().toString(), Image);

                if (b==true)
                {
                    Toast.makeText(AdminProfile.this,"Done",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(AdminProfile.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });



        profile_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(AdminProfile.this,admin_dashboard.class);
                startActivity(intent);
            }
        });

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode==100)
        {
            //get image
            Bitmap image=(Bitmap) data.getExtras().get("data");
            //setit in circle imageview
            profilePic.setImageBitmap(image);
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);

            byte[] Image=byteArrayOutputStream.toByteArray();


        }
    }
}