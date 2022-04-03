package com.example.epa51;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class User_db extends SQLiteOpenHelper {

    //introducing colums to constant so we don't repeat typing them
    public static final String USER_TABLE = "user_table";
    public static final String COL_FULLNAME = "FULLNAME";
    public static final String COL_PASSWORD = "PASSWORD";
    public static final String COL_ID = "ID";



    public User_db(@Nullable Context context) {
        super(context, "User.db", null, 1);//data name,factory can be null,1 is for version 1 of our data
    }







    //this one is for first time creating a db
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String tableStatement= "CREATE TABLE " + USER_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_FULLNAME + " TEXT, " + COL_PASSWORD + " TEXT)"; //statement to create tables in sql
        sqLiteDatabase.execSQL(tableStatement);

    }

    //this one is for new updates in db
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    //this function is temporary
    public boolean addUser(User_Model user_model)
    {
        //in this function im gonna add users manually we need to change it later to csv or other way to add users...
        SQLiteDatabase db=this.getWritableDatabase(); //getWritableDatabase is for writing inside the database
        ContentValues info_holder=new ContentValues();  //ContentValues is like hashmap store data in pairs


        //no need to add ID it is autoincrement

        info_holder.put(COL_FULLNAME,user_model.getComplete_name());
        info_holder.put(COL_PASSWORD,user_model.getPassword());


        //add data to ContentValues then add ContentValues to db

        long insert = db.insert(USER_TABLE, null, info_holder);

        if (insert == -1){
            return false;
        }
        else{
            return true;
        }





    }




    //this is for getting the data from the database
    public List<User_Model> getall()
    {
        List<User_Model> returnedList=new ArrayList<>();
        String query="SELECT * FROM "+USER_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();  //getReadablDatabase is for reading inside the database
        Cursor cursor = db.rawQuery(query,null); //result of the query in a cursur

        if (cursor.moveToFirst())
        {
            //loop through the result (the cursor)  and create new list objet for each result
            do {


                //0 for first column ,1 for sec colum .......
                int UserID=cursor.getInt(0);
                String UserName=cursor.getString(1);
                String UserPassword=cursor.getString(2);
                User_Model newuser=new User_Model(UserID,UserName,UserPassword);//send to the constructor
                returnedList.add(newuser);

            }
            while (cursor.moveToNext());
        }
        else
        {
            //this is a failure so we do nothin
        }



        return returnedList;
    }


//this methode is for deleting user from the data base

    public boolean deleteUser(User_Model user_model)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        String queryString="DELETE FROM "+USER_TABLE+" WHERE "+ COL_ID+" = "+user_model.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst())
        {
            return true;
        }
        else{
            return false;
        }



    }



    public String getName(int id)
    {

String name="";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM "+USER_TABLE+" WHERE "+COL_ID+" = ?",new String[]{String.valueOf(id)});



        if (cursor.moveToFirst())
        {
            //loop through the result (the cursor)  and create new list objet for each result
            do {


                String UserName=cursor.getString(1);

                return UserName;

            }
            while (cursor.moveToNext());
        }

    return null;

    }




    public String getPassword(int id)
    {

        String name="";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM "+USER_TABLE+" WHERE "+COL_ID+" = ?",new String[]{String.valueOf(id)});




        if (cursor.moveToFirst())
        {
            //loop through the result (the cursor)  and create new list objet for each result
            do {


                String UserPassword=cursor.getString(2);
                return UserPassword;

            }
            while (cursor.moveToNext());
        }

        return null;

    }





    public boolean updatedata(User_Model user_model)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values =new ContentValues();


        ContentValues info_holder=new ContentValues();  //ContentValues is like hashmap store data in pairs


        //no need to add ID it is autoincrement
        info_holder.put(COL_ID,user_model.getId());
        info_holder.put(COL_FULLNAME,user_model.getComplete_name());
        info_holder.put(COL_PASSWORD,user_model.getPassword());


        //add data to ContentValues then add ContentValues to db

        Cursor cursor= db.rawQuery("SELECT * FROM "+USER_TABLE+" WHERE "+COL_ID+" = ?",new String[]{String.valueOf(user_model.getId())});


        if (cursor.getCount()>0)
        {

            long update = db.update(USER_TABLE, info_holder, COL_ID + "=?", new String[]{String.valueOf(user_model.getId())});
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




    public boolean checkusername(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM "+USER_TABLE+" WHERE "+COL_FULLNAME+" = ?",new String[]{name});
        if (cursor.getCount() >0)
        {
            return true;  //if cursor is not empty so al least one user found
        }
        else {
            return false;
        }
    }

    public boolean checknamepassword(String name,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM "+USER_TABLE+" WHERE "+COL_FULLNAME+" = ? AND "+COL_PASSWORD+" =?",new String[]{name,password});
    if (cursor.getCount()>0)
    {
        return true;
    }
    else
    {
        return false;
    }
    }



}
