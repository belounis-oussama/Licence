package com.example.epa51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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


        add_listofshifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Shiftlist.this,ShiftDetails.class);
                startActivity(intent);
            }
        });




        shiftList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(Shiftlist.this,modify_delete_shift.class);
                String shift= shiftList.getAdapter().getItem(i).toString();

                String id = shift.substring(shift.indexOf("id=") + "id=".length(), shift.indexOf(","));


                intent.putExtra("idkeyshift",id);


                startActivity(intent);
                return false;
            }
        });
    }

    private void setShiftsAdapter() {



        Shift_db db=new Shift_db(Shiftlist.this);
        ShiftAdapter shiftAdapter=new ShiftAdapter(getApplicationContext(),db.getAllShift());
        shiftList.setAdapter(shiftAdapter);

    }
}