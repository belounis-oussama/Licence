package com.example.epa51;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PointageGear_db extends SQLiteOpenHelper {


    public static final String PG_TABLE = "pointage_gear_table";
    public static final String COL_ID = "ID";
    public static final String COL_ID_POINTAGE = "IDp";
    public static final String COL_ID_GEAR = "IDg";


    public PointageGear_db(@Nullable Context context) {
        super(context, "PointageGear.db", null, 1);//data name,factory can be null,1 is for version 1 of our data
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String tableStatement= "CREATE TABLE " + PG_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ID_POINTAGE + " INTEGER, " + COL_ID_GEAR + " INTEGER)";


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
