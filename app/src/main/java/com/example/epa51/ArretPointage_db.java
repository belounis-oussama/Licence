package com.example.epa51;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
}
