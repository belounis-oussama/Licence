package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class splash extends AppCompatActivity {


    ImageView vector,logo;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        vector=findViewById(R.id.vector);

        logo=findViewById(R.id.logo);

        //animation at start

        vector.animate().translationY(-1600).setDuration(1000).setStartDelay(3000);
        logo.animate().translationY(1600).setDuration(1000).setStartDelay(2000);

        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent =new Intent(splash.this,login.class);
                startActivity(intent);
                finish();
            }
        },4000);



    }
}