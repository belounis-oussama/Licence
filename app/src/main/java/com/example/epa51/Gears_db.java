package com.example.epa51;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Gears_db extends SQLiteOpenHelper {

    public static final String GEARS_TABLE= "GEARS_TABLE";
    public static final String COL_TYPE = "TYPE";
    public static final String COL_NUMBER = "NUMBER";
    public static final String COL_ID = "ID";





    public Gears_db(@Nullable Context context) {
        super(context, "Gears.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tableStatement= "CREATE TABLE " + GEARS_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TYPE + " TEXT, " + COL_NUMBER + " INTEGER)"; //statement to create tables in sql
        sqLiteDatabase.execSQL(tableStatement);

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




    public boolean addGear(Gear_Model gear_model)
    {
        //in this function im gonna add users manually we need to change it later to csv or other way to add users...
        SQLiteDatabase db=this.getWritableDatabase(); //getWritableDatabase is for writing inside the database
        ContentValues info_holder=new ContentValues();  //ContentValues is like hashmap store data in pairs


        //no need to add ID it is autoincrement

        info_holder.put(COL_TYPE,gear_model.getGear_type());
        info_holder.put(COL_NUMBER,gear_model.getGear_number());



        /*


        info_holder.put(COL_TYPE,type);
        info_holder.put(COL_NUMBER,number);
         */




        //add data to ContentValues then add ContentValues to db

        long insert = db.insert(GEARS_TABLE, null, info_holder);

        if (insert == -1){
            return false;
        }
        else{
            return true;
        }





    }





    public List<Gear_Model> getallgears()
    {
        List<Gear_Model> returnedList=new ArrayList<>();
        String query="SELECT * FROM "+GEARS_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();  //getReadablDatabase is for reading inside the database
        Cursor cursor = db.rawQuery(query,null); //result of the query in a cursur

        if (cursor.moveToFirst())
        {
            //loop through the result (the cursor)  and create new list objet for each result
            do {


                //0 for first column ,1 for sec colum .......
                int GearID=cursor.getInt(0);
                String GearType=cursor.getString(1);
                int GearNumber=cursor.getInt(2);


                Gear_Model newgear=new Gear_Model(GearID,GearType,GearNumber);//send to the constructor
                returnedList.add(newgear);

            }
            while (cursor.moveToNext());
        }
        else
        {
            //this is a failure so we do nothin
        }



        return returnedList;
    }




    public boolean deleteGear(Gear_Model gear_model)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        String queryString="DELETE FROM "+GEARS_TABLE+" WHERE "+ COL_ID+" = "+gear_model.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst())
        {
            return true;
        }
        else{
            return false;
        }



    }



    public boolean UpdateGear(Gear_Model gear_model)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values =new ContentValues();


        ContentValues info_holder=new ContentValues();  //ContentValues is like hashmap store data in pairs


        //no need to add ID it is autoincrement
        info_holder.put(COL_ID,gear_model.getId());
        info_holder.put(COL_TYPE,gear_model.getGear_type());
        info_holder.put(COL_NUMBER,gear_model.getGear_number());


        //add data to ContentValues then add ContentValues to db

        Cursor cursor= db.rawQuery("SELECT * FROM "+GEARS_TABLE+" WHERE "+COL_ID+" = ?",new String[]{String.valueOf(gear_model.getId())});


        if (cursor.getCount()>0)
        {

            long update = db.update(GEARS_TABLE, info_holder, COL_ID + "=?", new String[]{String.valueOf(gear_model.getId())});
            if (update==-1)
            {
                return false;
            }
            else
            {
                return true;
            }

        }
        return false;
    }





    public String getType(int id)
    {

        String gear="";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM "+GEARS_TABLE+" WHERE "+COL_ID+" = ?",new String[]{String.valueOf(id)});



        if (cursor.moveToFirst())
        {
            //loop through the result (the cursor)  and create new list objet for each result
            do {


                String Gear=cursor.getString(1);

                return Gear;

            }
            while (cursor.moveToNext());
        }

        return null;

    }






    public int getNumber(int id)
    {


        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM "+GEARS_TABLE+" WHERE "+COL_ID+" = ?",new String[]{String.valueOf(id)});




        if (cursor.moveToFirst())
        {
            //loop through the result (the cursor)  and create new list objet for each result
            do {


                int GearNumber=cursor.getInt(2);
                return GearNumber;

            }
            while (cursor.moveToNext());
        }

        return 0;

    }




    public boolean Checkgearinfos(String gear, String number)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM "+GEARS_TABLE+" WHERE "+COL_TYPE+" = ? AND "+COL_NUMBER+" = ?",new String[]{gear,number});
        if (cursor.getCount() >0)
        {
            return true;  //if cursor is not empty so al least one user found
        }
        else {
            return false;
        }
    }


    public List<String> getalltypes()
    {
        List<String> returnedList=new ArrayList<String>();
        String query="SELECT * FROM "+GEARS_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();  //getReadablDatabase is for reading inside the database
        Cursor cursor = db.rawQuery(query,null); //result of the query in a cursur

        if (cursor.moveToFirst())
        {
            //loop through the result (the cursor)  and create new list objet for each result
            do {


                //0 for first column ,1 for sec colum .......
                int GearID=cursor.getInt(0);
                String GearType=cursor.getString(1);
                int GearNumber=cursor.getInt(2);



                returnedList.add(GearType);

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
