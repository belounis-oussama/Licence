package com.example.epa51;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Admin_db extends SQLiteOpenHelper {

    public static final String ADMIN_TABLE = "admin_table";
    public static final String COL_ID = "ID";
    public static final String COL_FULLNAME = "FULLNAME";
    public static final String COL_PASSWORD = "PASSWORD";
    public static final String COL_DATE = "DATE";
    public static final String COL_PROFILE_PIC = "PROFILE_PIC";



    public Admin_db(@Nullable Context context) {
        super(context, "Admin.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String tableStatement= "CREATE TABLE " + ADMIN_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_FULLNAME + " TEXT, " + COL_PASSWORD + " TEXT ,"+COL_DATE+" TEXT,"+COL_PROFILE_PIC+" BLOB)"; //statement to create tables in sql
        sqLiteDatabase.execSQL(tableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean InsertData(String userName,String password, String date, byte[] img)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_FULLNAME,userName);
        contentValues.put(COL_PASSWORD,password);
        contentValues.put(COL_DATE,date);
        contentValues.put(COL_PROFILE_PIC,img);


        long insert =db.insert(ADMIN_TABLE,null,contentValues);

        if (insert==1)return false;
        else return true;
    }



    public List<String> getAllNames()
    {
        List<String> returnedList=new ArrayList<String>();
        String query="SELECT * FROM "+ADMIN_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();  //getReadablDatabase is for reading inside the database
        Cursor cursor = db.rawQuery(query,null); //result of the query in a cursur

        if (cursor.moveToFirst())
        {
            //loop through the result (the cursor)  and create new list objet for each result
            do {

                String Name=cursor.getString(1);
                returnedList.add(Name);

            }
            while (cursor.moveToNext());
        }
        else
        {
            //this is a failure so we do nothin
        }



        return returnedList;
    }




    public ContentValues getLastAdmin()
    {
        ContentValues contentValues=new ContentValues();





        String query="SELECT * FROM "+ADMIN_TABLE+" ORDER BY "+COL_ID+" DESC LIMIT 1";
        SQLiteDatabase db=this.getReadableDatabase();  //getReadablDatabase is for reading inside the database
        Cursor cursor = db.rawQuery(query,null); //result of the query in a cursur

        if (cursor.moveToFirst())
        {
            //loop through the result (the cursor)  and create new list objet for each result
            do {


                //0 for first column ,1 for sec colum .......
                int id=cursor.getInt(0);
                String name=cursor.getString(1);
                String password=cursor.getString(2);
                String date=cursor.getString(3);
                byte[] image=cursor.getBlob(4);


                contentValues.put("ID",id);
                contentValues.put("FULLNAME",name);
                contentValues.put("PASSWORD",password);
                contentValues.put("DATE",date);
                contentValues.put("IMAGE",image);

            }
            while (cursor.moveToNext());
        }
        else
        {
            //this is a failure so we do nothin
        }

        return contentValues;
    }



    public String getLastAdminPassword()
    {





        String password = null;

        String query="SELECT * FROM "+ADMIN_TABLE+" ORDER BY "+COL_ID+" DESC LIMIT 1";
        SQLiteDatabase db=this.getReadableDatabase();  //getReadablDatabase is for reading inside the database
        Cursor cursor = db.rawQuery(query,null); //result of the query in a cursur

        if (cursor.moveToFirst())
        {
            //loop through the result (the cursor)  and create new list objet for each result
            do {


                //0 for first column ,1 for sec colum .......
                int id=cursor.getInt(0);
                String name=cursor.getString(1);
                 password=cursor.getString(2);
                String date=cursor.getString(3);
                byte[] image=cursor.getBlob(4);




            }
            while (cursor.moveToNext());
        }
        else
        {
            //this is a failure so we do nothin
        }

        return password;
    }



}
