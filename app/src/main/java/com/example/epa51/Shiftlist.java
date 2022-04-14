package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

public class Shiftlist extends AppCompatActivity {

    ListView shiftList;
    ImageButton backbtn_listofshifts,add_listofshifts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiftlist);


        shiftList=findViewById(R.id.listview_shifts);
        backbtn_listofshifts=findViewById(R.id.backbtn_listofshifts);
        add_listofshifts=findViewById(R.id.add_listofshifts);

        setShiftsAdapter();
    }

    private void setShiftsAdapter() {



        Shift_db db=new Shift_db(Shiftlist.this);





        ShiftAdapter shiftAdapter=new ShiftAdapter(getApplicationContext(),db.getAllShift());
        shiftList.setAdapter(shiftAdapter);

    }
}