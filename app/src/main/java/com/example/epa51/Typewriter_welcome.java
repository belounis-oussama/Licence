package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Typewriter_welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typewriter_welcome);

 Timer timer;

        String name=getIntent().getStringExtra("namekey");



        //String username=getStringExtra("idkey")
        Typewriter textview=findViewById(R.id.typechar);
        textview.setText("");
        textview.setCharDelay(150);
        textview.animatedText("Welcome "+name);

        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {



                Intent intent =new Intent(Typewriter_welcome.this,debarq_embarq.class);
                startActivity(intent);
                //we start the activity that contains the pointing page -1



                finish();
            }
        },5000);





    }
}