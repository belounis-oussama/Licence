package com.example.epa51;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ArretPointage_db extends SQLiteOpenHelper {

    public static final String ARRET_POINTAGE_TABLE= "ARRET_POINTAGE_TABLE";
    public static final String COL_ID= "ID";
    public static final String COL_ID_POINTAGE= "ID_POINTAGE";
    public static final String COL_START_DATE= "START_DATE";
    public static final String COL_START_HOUR= "START_HOUR";
    public static final String COL_END_DATE= "END_DATE";
    public static final String COL_END_HOUR= "END_HOUR";
    public static final String COL_REASON= "END_REASON";


    public ArretPointage_db(@Nullable Context context) {
        super(context, "ArretPointage.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tableStatement= "CREATE TABLE " + ARRET_POINTAGE_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ID_POINTAGE + " INTEGER, " + COL_START_DATE + " TEXT, "+COL_START_HOUR+" TEXT, "+COL_END_DATE+" TEXT, "+COL_END_HOUR+" TEXT,"+COL_REASON+" TEXT)"; //statement to create tables in sql
        sqLiteDatabase.execSQL(tableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean AddArret(EtasArret_Model Arret,int IdPointage)
    {
        SQLiteDatabase db=this.getWritableDatabase(); //getWritableDatabase is for writing inside the database
        ContentValues info_holder=new ContentValues();  //ContentValues is like hashmap store data in pairs


        //no need to add ID it is autoincrement

        info_holder.put(COL_ID_POINTAGE,IdPointage);
        info_holder.put(COL_START_DATE,Arret.getStartDate());
        info_holder.put(COL_START_HOUR,Arret.getStartHour());
        info_holder.put(COL_END_DATE,Arret.getEndDate());
        info_holder.put(COL_END_HOUR,Arret.getEndHour());
        info_holder.put(COL_REASON,Arret.getReason());




        long insert = db.insert(ARRET_POINTAGE_TABLE, null, info_holder);

        if (insert == -1){
            return false;
        }
        else{
            return true;
        }
    }


    public List<EtasArret_Model> getArret(int IdPointage)
    {
        List<EtasArret_Model> returnedList=new ArrayList<>();

        String query="SELECT * FROM "+ARRET_POINTAGE_TABLE+" WHERE "+COL_ID_POINTAGE+" = "+IdPointage;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst())
        {

            do {



                String StartDate=cursor.getString(2);
                String EndDate=cursor.getString(3);
                String StartHour=cursor.getString(4);
                String EndHour=cursor.getString(5);
                String Reason=cursor.getString(6);



                EtasArret_Model newArret=new EtasArret_Model(StartDate,StartHour,EndDate,EndHour,Reason);





                returnedList.add(newArret);

            }
            while (cursor.moveToNext());
        }
        else
        {
            //this is a failure so we do nothin
        }



        return returnedList;
    }
}
