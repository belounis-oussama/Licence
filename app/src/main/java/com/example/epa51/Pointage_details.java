package com.example.epa51;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Pointage_details extends AppCompatActivity {

    public BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointage_details);
        bottomNav=findViewById(R.id.BottomNavigationP);
        bottomNav.setOnNavigationItemSelectedListener(navlistener);
        //display basics in start
        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_Pointage,new PointageBaics()).commit();


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

            getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_Pointage,selectedFragment).commit();
            return true;
        }
    };
}