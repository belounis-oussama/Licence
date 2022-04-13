package com.example.epa51;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Pointage_db extends SQLiteOpenHelper {


    public static final String PG_TABLE = "pointage_table";
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
        super(context, "Pointage.db", null, 1);//data name,factory can be null,1 is for version 1 of our data
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tableStatement = "CREATE TABLE " + PG_TABLE + "("
                + COL_ID + "INTEGER PRIMARY KEY," + COL_NOM_POINTEUR +
                "TEXT," + COL_NOM_NAVIRE + "TEXT," + COL_DATE + "TEXT,"
                + COL_MODE_CONDITIONNEMENT + "TEXT," + COL_NATURE_MARCHANDISE + "TEXT," +
                COL_BRIGADE + "INTEGER,"+ COL_SHIFT +"TEXT,"+ COL_QUAI+"TEXT )";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
