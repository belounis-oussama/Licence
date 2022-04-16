package com.example.epa51;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
}
