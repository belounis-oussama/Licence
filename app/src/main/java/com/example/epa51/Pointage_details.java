package com.example.epa51;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Pointage_details extends AppCompatActivity {

    public BottomNavigationView bottomNav;
    public ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointage_details);
        bottomNav=findViewById(R.id.BottomNavigationP);
        bottomNav.setOnNavigationItemSelectedListener(navlistener);
        backbtn=findViewById(R.id.backbtn_pointageDetails);




        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Bundle ID=new Bundle();
        //                ID.putString("idPointage",id);


        String idPointage = getIntent().getStringExtra("idPointage");


        PointageBaics pointageBaics =new PointageBaics();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Bundle id=new Bundle();
        id.putString("idPointage",idPointage);
        pointageBaics.setArguments(id);
        fragmentTransaction.replace(R.id.Fragment_Pointage,pointageBaics).commit();



        //display basics in start
        //getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_Pointage,new PointageBaics()).commit();




    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment=null;

            switch (item.getItemId())
            {
                case R.id.pointageBasicsNav:
                    selectedFragment = new PointageBaics();
                    break;

                case R.id.GearsNav:
                    selectedFragment = new gearsFragment();
                    break;

                case R.id.ArretNav:
                    selectedFragment = new ArretFragment();
                    break;

                case R.id.GoodsNav:
                    selectedFragment = new GoodsFragment();
                    break;

            }




            String idPointage = getIntent().getStringExtra("idPointage");
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Bundle id=new Bundle();
            id.putString("idPointage",idPointage);
            selectedFragment.setArguments(id);
            fragmentTransaction.replace(R.id.Fragment_Pointage,selectedFragment).commit();

            //getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_Pointage,selectedFragment).commit();
            return true;
        }
    };
}