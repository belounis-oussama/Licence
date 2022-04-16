package com.example.epa51;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GoodsPointage_db extends SQLiteOpenHelper {
    public static final String GOODS_POINTAGE_TABLE= "GOODS_POINTAGE_TABLE";
    public static final String COL_ID= "ID";
    public static final String COL_ID_POINTAGE= "ID_POINTAGE";
    public static final String COL_REFERENCE= "REFERENCE";
    public static final String COL_QUANTITY= "QUANTITY";
    public static final String COL_WEIGHT= "WEIGHT";





    public GoodsPointage_db(@Nullable Context context) {
        super(context, "GoodsPointage.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String tableStatement= "CREATE TABLE " + GOODS_POINTAGE_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ID_POINTAGE + " INTEGER, " + COL_REFERENCE + " TEXT, "+COL_QUANTITY+" INTEGER, "+COL_WEIGHT+" INTEGER)"; //statement to create tables in sql
        sqLiteDatabase.execSQL(tableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
