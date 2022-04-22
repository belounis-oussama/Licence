package com.example.epa51;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GearPointage_db extends SQLiteOpenHelper {
    public static final String GEAR_POINTAGE_TABLE= "GEAR_POINTAGE_TABLE";
    public static final String COL_ID= "ID";
    public static final String COL_ID_POINTAGE= "ID_POINTAGE";
    public static final String COL_ID_GEAR= "ID_GEAR";




    public GearPointage_db(@Nullable Context context) {
        super(context, "GearPointage.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String tableStatement= "CREATE TABLE " + GEAR_POINTAGE_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ID_POINTAGE + " INTEGER, " + COL_ID_GEAR + " INTEGER)"; //statement to create tables in sql
        sqLiteDatabase.execSQL(tableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



    public boolean AddnewPointageGear(int idGear,int idPointage)
    {


        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues info_holder=new ContentValues();


        info_holder.put(COL_ID_GEAR,idGear);
        info_holder.put(COL_ID_POINTAGE,idPointage);



        long insert = db.insert(GEAR_POINTAGE_TABLE, null, info_holder);

        if (insert == -1){
            return false;
        }
        else{
            return true;
        }


    }


    public List<Integer> getGearUsed(int IdPointage)
    {


        List<Integer> returnedList=new ArrayList<>();
        //Cursor cursor= db.rawQuery("SELECT * FROM "+GEARS_TABLE+" WHERE "+COL_ID+" = ?",new String[]{String.valueOf(id)});
        String query="SELECT * FROM "+GEAR_POINTAGE_TABLE+" WHERE "+COL_ID_POINTAGE+" = "+IdPointage;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst())
        {

            do {



                int PointageID=cursor.getInt(0);
                int GearID=cursor.getInt(2);


                returnedList.add(GearID);

            }
            while (cursor.moveToNext());
        }
        else
        {
            //this is a failure so we do nothin
        }



        return returnedList;
    }

    public List<Integer> all()
    {
        List<Integer> returnedList=new ArrayList<>();
        //Cursor cursor= db.rawQuery("SELECT * FROM "+GEARS_TABLE+" WHERE "+COL_ID+" = ?",new String[]{String.valueOf(id)});
        String query="SELECT * FROM "+GEAR_POINTAGE_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst())
        {

            do {



                int PointageID=cursor.getInt(0);
                int GearID=cursor.getInt(2);


                returnedList.add(GearID);
                returnedList.add(PointageID);

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
