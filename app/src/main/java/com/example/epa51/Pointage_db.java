package com.example.epa51;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Pointage_db extends SQLiteOpenHelper {


    public static final String PG_TABLE = "pointageTable";
    public static final String COL_ID = "ID";
    public static final String COL_NOM_POINTEUR = "nom_pointeur";
    public static final String COL_NOM_NAVIRE = "nom_navire";
    public static final String COL_DATE = "date";
    public static final String COL_MODE_CONDITIONNEMENT = "mode_conditionnement";
    public static final String COL_NATURE_MARCHANDISE = "nature_marchandise";
    public static final String COL_BRIGADE = "brigade";
    public static final String COL_SHIFT = "shift";
    public static final String COL_QUAI = "quai";

    public Pointage_db(@Nullable Context context) {
        super(context, "PointageDb.db", null, 1);//data name,factory can be null,1 is for version 1 of our data
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tableStatement = "CREATE TABLE " + PG_TABLE + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOM_POINTEUR +
                " TEXT, " + COL_NOM_NAVIRE + " TEXT, " + COL_DATE + " TEXT, "
                + COL_MODE_CONDITIONNEMENT + " TEXT, " + COL_NATURE_MARCHANDISE + " TEXT, " +
                COL_BRIGADE + " TEXT ,"+ COL_SHIFT +" TEXT, "+ COL_QUAI+" TEXT)";

        sqLiteDatabase.execSQL(tableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean AddPointage(Pointage_Model pointage_model)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues info_holder=new ContentValues();


        info_holder.put(COL_NOM_POINTEUR,pointage_model.getNom_pointeur());
        info_holder.put(COL_NOM_NAVIRE,pointage_model.getNom_navire());
        info_holder.put(COL_DATE,pointage_model.getDate());
        info_holder.put(COL_MODE_CONDITIONNEMENT,pointage_model.getMode_conditionnement());
        info_holder.put(COL_NATURE_MARCHANDISE,pointage_model.getNature_marchandise());
        info_holder.put(COL_BRIGADE,pointage_model.getBrigade());
        info_holder.put(COL_SHIFT,pointage_model.getShift());
        info_holder.put(COL_QUAI,pointage_model.getQuai());


        //add data to ContentValues then add ContentValues to db

        long insert = db.insert(PG_TABLE, null, info_holder);

        if (insert == -1){
            return false;
        }
        else{
            return true;
        }

    }



    public List<Pointage_Model> getAllPointages()
    {
        List<Pointage_Model> returnedList=new ArrayList<>();
        String query="SELECT * FROM "+PG_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst())
        {
            //loop through the result (the cursor)  and create new list objet for each result
            do {



                int PId=cursor.getInt(0);
                String poiteurName=cursor.getString(1);
                String nomNavire=cursor.getString(2);
                String date=cursor.getString(3);
                String Mode_conditionnement=cursor.getString(4);
                String nature=cursor.getString(5);
                String brigade=cursor.getString(6);
                String shift=cursor.getString(7);
                String quai=cursor.getString(8);

                Pointage_Model newPoinage=new Pointage_Model(PId,poiteurName,nomNavire,date,Mode_conditionnement,nature,brigade,shift,quai);


                returnedList.add(newPoinage);

            }
            while (cursor.moveToNext());
        }
        else
        {

        }



        return returnedList;
    }


    public int getCurrentPointage()
    {
        int id=0;



        String query="SELECT * FROM "+PG_TABLE+" ORDER BY "+COL_ID+" DESC LIMIT 1";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst())
        {
            //loop through the result (the cursor)  and create new list objet for each result
            do {



                id=cursor.getInt(0);




                return id;





            }
            while (cursor.moveToNext());
        }
        else
        {
            //this is a failure so we do nothin
        }




        return id;
    }
}
